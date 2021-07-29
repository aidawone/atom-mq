package he.aida.consumer.config.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: zhanggr
 * @Description:
 * @Date: Create in 9:54 2021/7/27
 */
@Component
@RabbitListener(queues = "topic.woman")
public class TopicReceiver2 {
    @RabbitHandler
    public void process(Map<String, Object> testMessage) {
        System.out.println("TopicManReceiver消费者收到消息  : " + testMessage.toString());
    }
}
