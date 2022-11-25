package n.smilehelo.community.realtion.controller;

import n.smilehelo.community.realtion.po.Resp;
import n.smilehelo.community.realtion.po.req.FollowParams;
import n.smilehelo.community.realtion.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 *
 * project: community
 * className：FollowController
 * description:
 * @author: HeLO
 * version: V1.0
 * createDate: 2022-11-25 14:02
 *
 * </pre>
 **/
@RestController
@RequestMapping(value = "/follow")
public class FollowController {

    @Autowired
    private FollowService service;


    /**
     * 关注
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "follow")
    public Resp follow(@RequestBody FollowParams params) {
        return Resp.success(service.follow(params));
    }


    /**
     * 取消关注
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "cancelFollow")
    public Resp cancelFollow(@RequestBody FollowParams params) {
        return Resp.success(service.cancelFollow(params));
    }


}
