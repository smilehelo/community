package n.smilehelo.community.realtion.exception;

/**
 * <pre>
 *
 * project: community
 * className：BizException
 * description: 业务异常
 * @author: HeLO
 * version: V1.0
 * createDate: 2022-11-25 14:23
 *
 * </pre>
 **/
public class BizException extends RuntimeException {

    private Integer code;

    public BizException() {
    }

    public BizException(String message) {
        super(message);
        this.code = 100;
    }

    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
