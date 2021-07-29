package he.aida.mqt.hello;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import he.aida.common.utils.RabbitMQClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProviderTests {

    //第一种： 直连
    @Test
    void contextLoads() {
        Channel channel = null;
        Connection connection = null;
        try {
            ConnectionFactory factory = RabbitMQClient.getInstance();
            connection = factory.newConnection();
            channel = connection.createChannel();
            //创建通道
            String queue = "hello";
            //参数1: 是否持久化  参数2:是否独占队列 参数3:是否自动删除  参数4:其他属性
            channel.queueDeclare(queue, true, false, false, null);
            //参数1:交换机
            //参数2：队列
            //参数3：额外设置，
            for (int i = 0; i < 300; i++) {
                channel.basicPublish("", queue, MessageProperties.PERSISTENT_TEXT_PLAIN, ("work rabbitmq-" + i).getBytes());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            RabbitMQClient.close(channel, connection);
        }
    }
}
