package he.aida.common.basic.rabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 动态路由 消费者
 *
 * @Author: aidawone
 * @Description:
 * @Date: Create in 10:15 2021/3/11
 */
@Component
public class CustomerTopic {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue, //临时队列
            key = {"topic.*", "item.*", "user.#"}, //路由key
            exchange = @Exchange(name = "s-topic", type = "topic")
    ))
    public void custom(String message) {
        System.out.println("message = " + message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue, //临时队列
            key = "#.topic.save", //路由key
            exchange = @Exchange(name = "s-topic", type = "topic")
    ))
    public void custom2(String message) {
        System.out.println("message2 = " + message);
    }
}


