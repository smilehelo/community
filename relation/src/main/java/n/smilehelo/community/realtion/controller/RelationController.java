package n.smilehelo.community.realtion.controller;

import n.smilehelo.community.realtion.common.FollowStatusEnum;
import n.smilehelo.community.realtion.po.BaseParams;
import n.smilehelo.community.realtion.po.Resp;
import n.smilehelo.community.realtion.po.req.FollowParams;
import n.smilehelo.community.realtion.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 *
 * project: community
 * className：RelationController
 * description: 关系
 * @author: HeLO
 * version: V1.0
 * createDate: 2022-11-25 16:19
 *
 * </pre>
 **/
@RestController
@RequestMapping(value = "/relation")
public class RelationController {

    @Autowired
    private RelationService service;

    /**
     * 关系计数器
     *
     * @param params
     * @return
     */
    @RequestMapping("counter")
    public Resp counter(@RequestBody BaseParams params) {
        return Resp.success(service.counter(params));
    }

    /**
     * 关系查询
     *
     * @param params
     * @return
     */
    @RequestMapping("relation")
    public Resp relation(@RequestBody FollowParams params) {
        return Resp.success(service.relation(params));
    }


    /**
     * 关注列表
     *
     * @param params
     * @return
     */
    @RequestMapping("followList")
    public Resp followList(@RequestBody FollowParams params) {
        return Resp.success(service.relationList(params, FollowStatusEnum.FOLLOW));
    }

    /**
     * 粉丝列表
     *
     * @param params
     * @return
     */
    @RequestMapping("fansList")
    public Resp fansList(@RequestBody FollowParams params) {
        return Resp.success(service.relationList(params, FollowStatusEnum.FANS));
    }


    /**
     * 好友列表
     *
     * @param params
     * @return
     */
    @RequestMapping("friendList")
    public Resp friendList(@RequestBody FollowParams params) {
        return Resp.success(service.relationList(params, FollowStatusEnum.FRIEND));
    }


}
