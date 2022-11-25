package n.smilehelo.community.realtion.config;

import cn.smilehelo.redisUtil.Redis;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * <pre>
 *
 * project: community
 * className：InitConfig
 * description:
 * @author: HeLO
 * version: V1.0
 * createDate: 2022-11-13 17:57
 *
 * </pre>
 **/
@Configuration
public class InitConfig {

    @Value("#{${redis}}")
    private Map<String, String> redisMap;


    @Bean
    public Redis redis() {
        Redis.RedisConfig redisConfig = Redis.RedisConfig.of(redisMap.get("host"));
        return new Redis(redisConfig);
    }

}
