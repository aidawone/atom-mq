package he.aida.mqt.rabbitmq.hello;

import he.aida.mqt.entity.User;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * hello 消费者
 *
 * @Author: aidawone
 * @Description:
 * @Date: Create in 10:15 2021/3/11
 */
@Component
@RabbitListener(queuesToDeclare = @Queue("hello"))
public class CustomerHello {

    @RabbitHandler
    public void custom(User message) {
        System.out.println("message = " + message);
    }
}
