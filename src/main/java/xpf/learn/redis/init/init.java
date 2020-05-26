package xpf.learn.redis.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import xpf.learn.redis.service.InitService;

/**
 *ApplicationRunner
 * 在开发中可能会有这样的情景。需要在容器启动的时候执行一些内容。比如读取配置文件，数据库连接之类的。
 * SpringBoot给我们提供了两个接口来帮助我们实现这种需求。这两个接口分别为CommandLineRunner和ApplicationRunner。
 * 他们的执行时机为容器启动完成的时候。
 *
 *ApplicationListener 监听器
 *当我们使用spring boot项目开发时候，碰到应用启动后做一些初始化操作，可以使用ApplicationListener。
 * 比如：netty 随着应用启动完成后进行初始化、初始化定时任务
 * @author xpf
 * @date 2020/5/26 14:31
 */
@Component
public class init implements ApplicationRunner {

    @Autowired
    private InitService initService;

    @Async
    @Override
    public void run(ApplicationArguments args) throws Exception {
            initService.init();
    }
}
