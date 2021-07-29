package he.aida.common.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: aidawone
 * @Description:
 * @Date: Create in 17:51 2021/3/10
 */
public class RabbitMQClient {
    private static final String USERNAME = "user";
    private static final String PASSWORD = "123456";
    private static final String VIRTUAL_HOST = "/mqt";
    private static final String HOST = "192.168.0.101";
    private static final Integer PORT = 5672;

    private static class RabbitMQSingleton {
        private static final ConnectionFactory FACTORY = new ConnectionFactory();

        static {
            RabbitMQSingleton.initConnection();
        }

        private static void initConnection() {
            FACTORY.setUsername(USERNAME);
            FACTORY.setPort(PORT);
            FACTORY.setVirtualHost(VIRTUAL_HOST);
            FACTORY.setPassword(PASSWORD);
            FACTORY.setHost(HOST);
        }
    }


    private RabbitMQClient() {
    }

    public static ConnectionFactory getInstance() {

        return RabbitMQSingleton.FACTORY;
    }

    public static void close(Channel channel, Connection connection) {
        if (channel != null) {
            try {
                channel.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (TimeoutException e) {
                System.out.println(e.getMessage());
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
