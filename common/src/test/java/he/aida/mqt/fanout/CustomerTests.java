package he.aida.mqt.fanout;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import he.aida.common.utils.RabbitMQClient;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

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
            String virtualHost = "logs";
            //绑定交换机
            channel.exchangeDeclare(virtualHost, "fanout");
            //创建临时队列
            String queueName = channel.queueDeclare().getQueue();
            //将临时队列绑定exchange
            //参数1：队列，参数二: 交换机
            channel.queueBind(queueName, virtualHost, "");
            //参数1: 是否持久化  参数2:是否独占队列 参数3:是否自动删除  参数4:其他属性
            channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
                //最后一个参数是拿到的参数
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println("body1 = " + new String(body));
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
