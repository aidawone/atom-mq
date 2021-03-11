package he.aida.mqt.rabbitmq.fanout;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 广播 消费者
 *
 * @Author: aidawone
 * @Description:
 * @Date: Create in 10:15 2021/3/11
 */
@Component
public class CustomerFanout {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue, //临时队列
            exchange = @Exchange(name = "s-logs", type = "fanout")
    ))
    public void custom(String message) {
        System.out.println("message = " + message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue, //临时队列
            exchange = @Exchange(name = "s-logs", type = "fanout")
    ))
    public void custom2(String message) {
        System.out.println("message2 = " + message);
    }
}


