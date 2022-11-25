package n.smilehelo.community.realtion.po.req;

import lombok.Data;
import n.smilehelo.community.realtion.po.BaseParams;

/**
 * <pre>
 *
 * project: community
 * className：FollowParams
 * description:
 * @author: HeLO
 * version: V1.0
 * createDate: 2022-11-25 14:17
 *
 * </pre>
 **/
@Data
public class FollowParams extends BaseParams {

    /** 关注用户id */
    private String targetUserId;
}
