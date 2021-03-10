package he.aida.mqt.hello;

import com.rabbitmq.client.*;
import he.aida.mqt.utils.RabbitMQClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: aidawone
 * @Description:
 * @Date: Create in 18:41 2021/3/10
 */
@SpringBootTest
public class CustomerTests {
    public static void main(String[] args) {
        try {
            ConnectionFactory factory = RabbitMQClient.getInstance();
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            //创建通道
            String queue = "hello";
            //参数1: 是否持久化  参数2:是否独占队列 参数3:是否自动删除  参数4:其他属性
            channel.queueDeclare(queue, true, false, false, null);
            //参数1: 队列名称
            //开始消费的自动确认机制
            //回调方法
            channel.basicConsume(queue, false, new DefaultConsumer(channel) {
                //最后一个参数是拿到的参数
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println("body = " + new String(body));
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
