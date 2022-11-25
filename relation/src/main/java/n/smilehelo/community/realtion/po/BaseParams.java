package n.smilehelo.community.realtion.po;

import lombok.Data;

/**
 * <pre>
 *
 * project: community
 * className：BaseParams
 * description: 基础请求参数
 * @author: HeLO
 * version: V1.0
 * createDate: 2022-11-25 14:11
 *
 * </pre>
 **/
@Data
public class BaseParams {

    /** 应用id */
    private Integer appTypeId;
    /** 当前用户id */
    private String userId;
    /** 分页参数 */
    private Integer page;
    /** 分页参数 */
    private Integer pageSize;


    public Integer getPage() {
        return page == null ? 1 : page;
    }

    public Integer getPageSize() {
        return pageSize == null ? 10 : pageSize;
    }
}
