package n.smilehelo.community.realtion.common;

/**
 * <pre>
 *
 * project: community
 * className：FollowStatusEnum
 * description: 关注状态
 * @author: HeLO
 * version: V1.0
 * createDate: 2022-11-25 14:40
 *
 * </pre>
 **/
public enum FollowStatusEnum {

    /** 没关系 */
    NONE("-1","没关系"),

    /** 关注 */
    FOLLOW("1","关注"),

    /** 关注 */
    FANS("2","粉丝"),

    /** 互关好友 */
    FRIEND("3","互关好友"),

    ;

    /** 关注状态 */
    private String status;
    /** 描述 */
    private String desc;

    FollowStatusEnum(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
