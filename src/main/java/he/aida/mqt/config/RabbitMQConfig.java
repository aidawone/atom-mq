package he.aida.mqt.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.adapter.DefaultServerWebExchange;

/**
 * 配置类
 *
 * @Author: aidawone
 * @Description:
 * @Date: Create in 11:49 2021/3/11
 */
@Configuration
public class RabbitMQConfig {
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 定义消息转换实例
     *
     * @return
     */
    @Bean
    MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    //    @Bean
//    public SimpleMessageListenerContainer messageListenerContainer() {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        //消息确认机制
//        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
//        //发送消息条数
//        container.setPrefetchCount(1);
//        return container;
//    }

    //项目启动就创建队列，无法做到动态生成
//    @Bean
//    public Queue helloQueue() {
//        return new Queue("hello", true);
//    }
}
