package n.smilehelo.community.realtion.common;

/**
 * <pre>
 *
 * project: community
 * className：RedisKey
 * description:
 * @author: HeLO
 * version: V1.0
 * createDate: 2022-11-25 15:02
 *
 * </pre>
 **/
public class RedisKey {

    /**
     * 关注状态
     * hash类型，hkey为targetUserId，value为{@link FollowStatusEnum}
     *
     * @param appTypeId 应用id
     * @param userId    用户id
     * @return
     */
    public static String followStatusKey(Integer appTypeId, String userId) {
        return String.format("community:%s:followStatus:%s", appTypeId, userId);
    }


    /**
     * 关系列表
     * zset类型，score为时间戳，value为targetUserId
     *
     * @param appTypeId    应用id
     * @param followStatus 关注状态
     * @param userId       用户id
     * @return
     */
    public static String relationListKey(Integer appTypeId, String followStatus, String userId) {
        return String.format("community:%s:relationList:%s:%s", appTypeId, followStatus, userId);
    }
}
