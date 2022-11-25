package n.smilehelo.community.realtion.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.smilehelo.redisUtil.Redis;
import n.smilehelo.community.realtion.common.FollowStatusEnum;
import n.smilehelo.community.realtion.common.RedisKey;
import n.smilehelo.community.realtion.exception.BizException;
import n.smilehelo.community.realtion.po.req.FollowParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <pre>
 *
 * project: community
 * className：RelationService
 * description: 关系服务层
 * @author: HeLO
 * version: V1.0
 * createDate: 2022-11-25 16:40
 *
 * </pre>
 **/
@Service
public class RelationService {

    @Autowired
    private Redis redis;


    /**
     * 关系查询
     *
     * @param params
     * @return
     */
    public String relation(FollowParams params) {
        Integer appTypeId = params.getAppTypeId();
        String followStatus = redis.hget(RedisKey.followStatusKey(appTypeId, params.getUserId()), params.getTargetUserId());
        return StrUtil.isBlank(followStatus) ? FollowStatusEnum.NONE.getStatus() : followStatus;
    }


    /**
     * 关系列表
     *
     * @param params
     * @param followStatusEnum
     * @return
     */
    public List<String> relationList(FollowParams params, FollowStatusEnum followStatusEnum) {
        Integer page = params.getPage();
        Integer pageSize = params.getPageSize();
        long start = page == 1 ? 0L : (long) (page - 1) * pageSize;
        long end = page == 1 ? pageSize - 1 : ((long) page * pageSize) - 1;
        String listKey = RedisKey.relationListKey(params.getAppTypeId(), followStatusEnum.getStatus(), params.getUserId());
        Set<String> userIdSet = redis.zrevrange(listKey, start, end);
        return CollectionUtil.isEmpty(userIdSet) ? Collections.emptyList() : new ArrayList<>(userIdSet);
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
        if (StrUtil.isBlank(params.getUserId())) {
            throw new BizException("用户id 不能为空");
        }
        if (params.getUserId().equals(params.getTargetUserId())) {
            throw new BizException("用户id与目标用户id 不能相同");
        }

    }


}
