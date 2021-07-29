package he.aida.consumer.config.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @Author: zhanggr
 * @Description:
 * @Date: Create in 16:04 2021/7/29
 */
@Configuration
public class DlxListener {
    @RabbitListener(queues = "sunspring.dlx.queue")
    public void dlxListener(Message message, Channel channel) throws IOException {
        System.out.println(new String(message.getBody()));

        //对消息进行业务处理....
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

}
