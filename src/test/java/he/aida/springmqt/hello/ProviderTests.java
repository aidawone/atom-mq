package he.aida.springmqt.hello;

import com.rabbitmq.client.MessageProperties;
import he.aida.mqt.MqtApplication;
import he.aida.mqt.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: aidawone
 * @Description:
 * @Date: Create in 10:20 2021/3/11
 */
@SpringBootTest(classes = MqtApplication.class) //引入springboot入口
public class ProviderTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void send() {
        User user = new User();
        user.setName("hello");
        user.setAge(100);
        user.setAddress("rabbitmq");
        rabbitTemplate.convertAndSend("hello", user);
    }
}
