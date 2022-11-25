package n.smilehelo.community.realtion.config;

import n.smilehelo.community.realtion.exception.BizException;
import n.smilehelo.community.realtion.po.Resp;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <pre>
 *
 * project: community
 * className：ExceptionAdvice
 * description:
 * @author: HeLO
 * version: V1.0
 * createDate: 2022-11-25 14:25
 *
 * </pre>
 **/
@RestControllerAdvice
public class ExceptionAdvice {

    /**
     * 业务异常捕获
     *
     * @param e 请求参数异常
     * @return Resp
     */
    @ExceptionHandler(value = BizException.class)
    public Resp handle(BizException e) {
        return Resp.error(e.getCode(), e.getMessage());
    }
}
