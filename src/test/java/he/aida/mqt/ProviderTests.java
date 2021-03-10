package he.aida.mqt;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import he.aida.mqt.utils.RabbitMQClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SpringBootTest
class ProviderTests {

    //第一种： 直连
    @Test
    void contextLoads() {
        Channel channel = null;
        Connection connection = null;
        try {
            ConnectionFactory factory = RabbitMQClient.getInstance();
            try {
                connection = factory.newConnection();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                System.out.println(e.getMessage());
            }
            //创建通道
            String queue = "hello";
            try {
                channel = connection.createChannel();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            //参数1: 是否持久化  参数2:是否独占队列 参数3:是否自动删除  参数4:其他属性
            channel.queueDeclare(queue, true, false, false, null);
            channel.basicPublish("", queue, null, "hello rabbitmq".getBytes());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            RabbitMQClient.close(channel, connection);
        }
    }
}
