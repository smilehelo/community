package n.smilehelo.community.realtion.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 *
 * project: community
 * className：EchoController
 * description:
 * @author: HeLO
 * version: V1.0
 * createDate: 2022-11-13 17:44
 *
 * </pre>
 **/
@RestController
@RequestMapping(value = "/")
public class EchoController {

    @RequestMapping(value = "/")
    public String echo() {
        return "hello relation";
    }
}
