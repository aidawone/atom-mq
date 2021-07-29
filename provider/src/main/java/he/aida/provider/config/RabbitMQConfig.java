package he.aida.provider.config;

import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("ConfirmCallback:     "+"相关数据："+correlationData);
                System.out.println("ConfirmCallback:     "+"确认情况："+ack);
                System.out.println("ConfirmCallback:     "+"原因："+cause);
            }
        });
        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returnedMessage) {
                System.out.println("ReturnCallback:     "+"消息："+returnedMessage.getMessage());
                System.out.println("ReturnCallback:     "+"回应码："+returnedMessage.getReplyCode());
                System.out.println("ReturnCallback:     "+"回应信息："+returnedMessage.getReplyText());
                System.out.println("ReturnCallback:     "+"交换机："+returnedMessage.getExchange());
                System.out.println("ReturnCallback:     "+"路由键："+returnedMessage.getRoutingKey());
            }
        });
        return rabbitTemplate;
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
