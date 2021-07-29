package he.aida.consumer.config.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: zhanggr
 * @Description:
 * @Date: Create in 11:10 2021/7/29
 */
@Component
@RabbitListener(queues = "fanout.C")
public class FanoutListenerC {
    @RabbitHandler
    public void process(Map<String, Object> testMessage) {
        System.out.println("FanoutReceiverC消费者收到消息  : " + testMessage.toString());
    }
}
