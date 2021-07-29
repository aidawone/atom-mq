package he.aida.consumer.config.listener;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: zhanggr
 * @Description:
 * @Date: Create in 9:44 2021/7/27
 */
@Component
@RabbitListener(queues = "TestDirectQueue")
public class DirectReceiver {
    @RabbitHandler
    public void process(Map<String, Object> testMessage) {
        System.out.println("DirectReceiver消费者收到消息  : " + testMessage.toString());
    }
}
