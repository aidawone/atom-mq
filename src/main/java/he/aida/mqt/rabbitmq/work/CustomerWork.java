package he.aida.mqt.rabbitmq.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * hello work
 *
 * @Author: aidawone
 * @Description:
 * @Date: Create in 10:15 2021/3/11
 */
@Component
public class CustomerWork {

    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void custom(String message) {
        System.out.println("message = " + message);
    }

    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void custom2(String message) {
        System.out.println("message2= " + message);
    }
}
