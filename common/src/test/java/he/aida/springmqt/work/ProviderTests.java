package he.aida.springmqt.work;

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
        for (int i = 0; i < 5; i++) {
            rabbitTemplate.convertAndSend("work", "work message");
        }
    }

}
