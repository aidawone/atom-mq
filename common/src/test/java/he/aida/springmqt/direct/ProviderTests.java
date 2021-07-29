package he.aida.springmqt.direct;

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
        for (int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend("s-direct", "direct.save", "fanout message" + i);
        }
    }

}
