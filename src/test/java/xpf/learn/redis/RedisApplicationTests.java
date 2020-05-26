package xpf.learn.redis;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import xpf.learn.redis.entity.User;
import xpf.learn.redis.util.RedisUtil;

import javax.annotation.Resource;

@SpringBootTest
class RedisApplicationTests {
    @Resource
    private RedisUtil redisUtil;

    @Test
    void testRedis() {
        User user = new User();
        user.setUserName("xpf");
        user.setPassword("xpf6960585");
        user.setType("管理员");
        redisUtil.set("user", user);
    }

}
