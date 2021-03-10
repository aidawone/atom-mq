package he.aida.mqt.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import he.aida.mqt.utils.RabbitMQClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProviderTests {

    //第三种:广播
    @Test
    void contextLoads() {
        Channel channel = null;
        Connection connection = null;
        try {
            ConnectionFactory factory = RabbitMQClient.getInstance();
            connection = factory.newConnection();
            //创建通道
            channel = connection.createChannel();
            //声明交换机
            String virtualHost = "logs";
            channel.exchangeDeclare(virtualHost, "fanout");
            //参数1:交换机
            //参数2：队列
            //参数3：额外设置，
            for (int i = 0; i < 5; i++) {
                channel.basicPublish(virtualHost, "", MessageProperties.PERSISTENT_TEXT_PLAIN, ("fanout rabbitmq-" + i).getBytes());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            RabbitMQClient.close(channel, connection);
        }
    }
}
