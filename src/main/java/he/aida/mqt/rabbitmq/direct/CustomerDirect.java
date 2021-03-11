package he.aida.mqt.rabbitmq.direct;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 路由 消费者
 *
 * @Author: aidawone
 * @Description:
 * @Date: Create in 10:15 2021/3/11
 */
@Component
public class CustomerDirect {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue, //临时队列
            key = "direct.item", //路由key
            exchange = @Exchange(name = "s-direct", type = "direct")
    ))
    public void custom(String message) {
        System.out.println("message = " + message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue, //临时队列
            key = "direct.save", //路由key
            exchange = @Exchange(name = "s-direct", type = "direct")
    ))
    public void custom2(String message) {
        System.out.println("message2 = " + message);
    }
}


