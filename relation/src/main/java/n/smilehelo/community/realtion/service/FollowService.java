package n.smilehelo.community.realtion.service;

import cn.hutool.core.util.StrUtil;
import cn.smilehelo.redisUtil.Redis;
import n.smilehelo.community.realtion.common.FollowStatusEnum;
import n.smilehelo.community.realtion.common.RedisKey;
import n.smilehelo.community.realtion.exception.BizException;
import n.smilehelo.community.realtion.po.req.FollowParams;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <pre>
 *
 * project: community
 * className：FollowService
 * description:
 * @author: HeLO
 * version: V1.0
 * createDate: 2022-11-25 14:19
 *
 * </pre>
 **/
@Service
public class FollowService {

    @Autowired
    private Redis redis;


    /**
     * 关注
     *
     * @param params
     * @return
     */
    public String follow(FollowParams params) {
        checkParams(params);
        long currentTime = System.currentTimeMillis();
        Integer appTypeId = params.getAppTypeId();
        String userId = params.getUserId();
        String targetUserId = params.getTargetUserId();
        // redisKey
        String userKey = RedisKey.followStatusKey(appTypeId, userId);
        String targetUserKey = RedisKey.followStatusKey(appTypeId, targetUserId);

        RLock lock = redis.getLock(lockStr(params));
        try {
            if (lock.tryLock()) {
                // 开始处理
                String userStatus = redis.hget(userKey, targetUserId);
                if (FollowStatusEnum.FOLLOW.getStatus().equals(userStatus) || FollowStatusEnum.FRIEND.getStatus().equals(userStatus)) {
                    throw new BizException("已关注");
                }

                // 关注zset
                redis.zadd(RedisKey.relationListKey(appTypeId, FollowStatusEnum.FOLLOW.getStatus(), userId), currentTime, targetUserId);
                // 粉丝zset
                redis.zadd(RedisKey.relationListKey(appTypeId, FollowStatusEnum.FANS.getStatus(), targetUserId), currentTime, userId);
                FollowStatusEnum finalFollowStatus;
                if (StrUtil.isBlank(userStatus)) {
                    // 未关注状态 -> 关注状态
                    finalFollowStatus = FollowStatusEnum.FOLLOW;
                    // 当前用户
                    redis.hset(userKey, targetUserId, FollowStatusEnum.FOLLOW.getStatus());
                    // 关注用户
                    redis.hset(targetUserKey, userId, FollowStatusEnum.FANS.getStatus());
                } else {
                    // 粉丝状态 -> 好友状态
                    finalFollowStatus = FollowStatusEnum.FRIEND;
                    // 当前用户
                    redis.hset(userKey, targetUserId, finalFollowStatus.getStatus());
                    redis.zadd(RedisKey.relationListKey(appTypeId, FollowStatusEnum.FRIEND.getStatus(), userId), currentTime, targetUserId);
                    // 关注用户
                    redis.hset(targetUserKey, userId, finalFollowStatus.getStatus());
                    redis.zadd(RedisKey.relationListKey(appTypeId, FollowStatusEnum.FRIEND.getStatus(), targetUserId), currentTime, userId);
                }
                return finalFollowStatus.getStatus();
            }
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        return FollowStatusEnum.NONE.getStatus();
    }


    /**
     * 取消关注
     *
     * @param params
     * @return
     */
    public String cancelFollow(FollowParams params) {
        checkParams(params);
        long currentTime = System.currentTimeMillis();
        Integer appTypeId = params.getAppTypeId();
        String userId = params.getUserId();
        String targetUserId = params.getTargetUserId();
        // redisKey
        String userKey = RedisKey.followStatusKey(appTypeId, userId);
        String targetUserKey = RedisKey.followStatusKey(appTypeId, targetUserId);

        RLock lock = redis.getLock(lockStr(params));
        try {
            if (lock.tryLock()) {
                // 开始处理
                String userStatus = redis.hget(userKey, targetUserId);
                if (StrUtil.isBlank(userStatus) || FollowStatusEnum.FANS.getStatus().equals(userStatus)) {
                    throw new BizException("未关注");
                }

                // 关注zset
                redis.zrem(RedisKey.relationListKey(appTypeId, FollowStatusEnum.FOLLOW.getStatus(), userId), targetUserId);
                // 粉丝zset
                redis.zrem(RedisKey.relationListKey(appTypeId, FollowStatusEnum.FANS.getStatus(), targetUserId), userId);
                FollowStatusEnum finalFollowStatus;
                if (FollowStatusEnum.FOLLOW.getStatus().equals(userStatus)) {
                    // 关注状态 -> 未关注状态
                    finalFollowStatus = FollowStatusEnum.NONE;
                    // 当前用户
                    redis.hdel(userKey, targetUserId);
                    // 关注用户
                    redis.hdel(targetUserKey, userId);
                } else {
                    // 好友状态 -> 粉丝状态
                    finalFollowStatus = FollowStatusEnum.FANS;
                    // 当前用户
                    redis.hset(userKey, targetUserId, finalFollowStatus.getStatus());
                    redis.zrem(RedisKey.relationListKey(appTypeId, FollowStatusEnum.FRIEND.getStatus(), userId), targetUserId);
                    // 关注用户
                    redis.hset(targetUserKey, userId, FollowStatusEnum.FOLLOW.getStatus());
                    redis.zrem(RedisKey.relationListKey(appTypeId, FollowStatusEnum.FRIEND.getStatus(), targetUserId), userId);
                }
                return finalFollowStatus.getStatus();
            }
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        return FollowStatusEnum.NONE.getStatus();
    }


    /**
     * 参数校验
     *
     * @param params
     */
    public void checkParams(FollowParams params) {
        if (params.getAppTypeId() == null) {
            throw new BizException("appTypeId 不能为空");
        }
        if (StrUtil.isBlank(params.getUserId()) || StrUtil.isBlank(params.getTargetUserId())) {
            throw new BizException("用户id 不能为空");
        }
        if (params.getUserId().equals(params.getTargetUserId())) {
            throw new BizException("用户id与目标用户id 不能相同");
        }

    }

    /**
     * redis锁字符串
     *
     * @param params
     * @return
     */
    public String lockStr(FollowParams params) {
        String userId = params.getUserId();
        String targetUserId = params.getTargetUserId();
        String lockSuffix;
        if (userId.compareTo(targetUserId) > 0) {
            lockSuffix = userId + "@" + targetUserId;
        } else {
            lockSuffix = targetUserId + "@" + userId;
        }
        return "community:redisLock:follow:" + params.getAppTypeId() + ":" + lockSuffix;
    }


}
