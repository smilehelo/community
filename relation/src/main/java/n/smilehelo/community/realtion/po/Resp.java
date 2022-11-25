package n.smilehelo.community.realtion.po;

import lombok.Data;

/**
 * <pre>
 *
 * project: community
 * className：Resp
 * description: 基础返回
 * @author: HeLO
 * version: V1.0
 * createDate: 2022-11-25 14:05
 *
 * </pre>
 **/
@Data
public class Resp {

    /** code，0为成功，非0为异常 */
    private Integer code;
    /** 错误信息 */
    private String msg;
    /** 数据信息 */
    private Object data;

    private Resp(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Resp success(Object data) {
        return new Resp(0, null, data);
    }


    public static Resp error(String msg) {
        return new Resp(100, msg, null);
    }

    public static Resp error(Integer code, String msg) {
        return new Resp(code, msg, null);
    }


}
