package n.smilehelo.community.realtion.service;

import n.smilehelo.community.realtion.po.req.FollowParams;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FollowServiceTest {

    @Autowired
    private FollowService service;


    @Test
    void follow() {
        FollowParams params = new FollowParams();
        params.setAppTypeId(1001);
        params.setUserId("BBB");
        params.setTargetUserId("AAA");
        service.follow(params);
    }

    @Test
    void cancelFollow() {
        FollowParams params = new FollowParams();
        params.setAppTypeId(1001);
        params.setUserId("BBB");
        params.setTargetUserId("AAA");
        service.cancelFollow(params);
    }

    @Test
    void lockStrTest(){
        FollowParams params = new FollowParams();
        params.setAppTypeId(1001);
        params.setUserId("BBB");
        params.setTargetUserId("AAA");
        System.out.println(service.lockStr(params));
    }
}