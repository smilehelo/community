package n.smilehelo.community.realtion.service;

import com.alibaba.fastjson.JSON;
import n.smilehelo.community.realtion.common.FollowStatusEnum;
import n.smilehelo.community.realtion.po.req.FollowParams;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RelationServiceTest {

    @Autowired
    private RelationService service;

    @Test
    void relation() {
        FollowParams params = new FollowParams();
        params.setAppTypeId(1001);
        params.setUserId("AAA");
        params.setTargetUserId("BBB");
        System.out.println(service.relation(params));
    }

    @Test
    void relationList() {
        FollowParams params = new FollowParams();
        params.setAppTypeId(1001);
        params.setUserId("AAA");
        System.out.println(JSON.toJSONString(service.relationList(params, FollowStatusEnum.FOLLOW)));
    }
}