package n.smilehelo.community.realtion.po.resp;

import lombok.Data;

/**
 * <pre>
 *
 * project: community
 * className：RelationCounter
 * description: 关系计数器
 * @author: HeLO
 * version: V1.0
 * createDate: 2023-03-07 17:37
 *
 * </pre>
 **/
@Data
public class RelationCounter {

    /** 关注数 */
    private Integer followCounter;
    /** 粉丝数 */
    private Integer fansCounter;
    /** 好友数 */
    private Integer friendCounter;

}
