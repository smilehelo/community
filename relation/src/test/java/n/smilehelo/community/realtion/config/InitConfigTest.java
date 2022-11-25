package n.smilehelo.community.realtion.config;


import cn.smilehelo.redisUtil.Redis;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InitConfigTest {

    @Autowired
    public Redis redis;

    @Test
    public void redisTest(){
        System.out.println(redis.get("hahaha"));
    }

}