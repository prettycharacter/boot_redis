package xpf.learn.redis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import xpf.learn.redis.entity.User;
import xpf.learn.redis.util.RedisUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xpf
 * @date 2020/5/26 15:05
 */
@Service
public class InitService {

    Logger logger = LoggerFactory.getLogger(InitService.class);
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private TaskExecutor taskExecutor;

    public void init() {
        logger.info("进入初始化缓存的方法+++++++++++++++++++++++++");
        User xpf = new User();
        xpf.setUserName("xpf");
        xpf.setPassword("xpf696058");
        xpf.setType("小神童");
        redisUtil.set("xpf", xpf);
    }

    public void initByTaskExecutor(){
        long start = System.currentTimeMillis();

        List<User> list = new ArrayList();
        User xpf = new User();
        xpf.setUserName("xpf");
        xpf.setPassword("xpf696058");
        xpf.setType("小神童");
        list.add(xpf);
        int pageCount = list.size();
        int pageSize = 100;
        int  threadCount = pageCount%pageSize == 0 ? pageCount/pageSize : pageCount/pageSize+1;
        for (int pageNumber=1; pageNumber <= threadCount;pageNumber++){
            final int executeNumber = pageSize * pageNumber;
            taskExecutor.execute(() -> executeCache(list,pageCount,pageSize,executeNumber));
        }
    }

    /**
     * 将数据刷入缓存
     * @param list
     * @param pageCount
     * @param pageSize
     * @param executeNumber
     */
    public void executeCache(List<User> list,int pageCount,int pageSize,int executeNumber){
        //循环将数据刷入缓存。
        for (int j = executeNumber -pageSize;j<executeNumber;j++){
            User user= list.get(j);
            String key = user.getUserName()+"_"+user.getPassword();
            redisUtil.set(key, user);
        }
    }
}
