package he.aida.springmqt.hello;

import he.aida.common.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: aidawone
 * @Description:
 * @Date: Create in 10:20 2021/3/11
 */
//@SpringBootTest(classes = MqtApplication.class) //引入springboot入口
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
