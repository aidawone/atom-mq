package he.aida.provider.config;

/**
 * @Author: zhanggr
 * @Description:
 * @Date: Create in 15:18 2021/7/29
 */

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wulei
 */
@Configuration
public class DeadQueueConfig {

    @Resource
    RabbitAdmin rabbitAdmin;

    @Value("${sunspring.order.exchange}")
    private String orderExchange;

    @Value("${sunspring.order.queue}")
    private String orderQueue;

    @Value("${sunspring.order.routingKey}")
    private String orderRoutingKey;

    @Value("${sunspring.dlx.exchange}")
    private String dlxExchange;

    @Value("${sunspring.dlx.queue}")
    private String dlxQueue;

    @Value("${sunspring.dlx.routingKey}")
    private String dlxRoutingKey;

    /**
     * 声明死信队列
     *
     * @return DirectExchange
     */
    @Bean
    public DirectExchange dlxExchange() {
        return new DirectExchange(dlxExchange);
    }

    /**
     * 声明死信队列
     *
     * @return Queue
     */
    @Bean
    public Queue dlxQueue() {
        return new Queue(dlxQueue);
    }

    /**
     * 绑定死信队列到死信交换机
     *
     * @return Binding
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(dlxQueue())
                .to(dlxExchange())
                .with(dlxRoutingKey);
    }

    /**
     * 声明订单业务交换机
     *
     * @return DirectExchange
     */
    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange(orderExchange);
    }

    //创建初始化RabbitAdmin对象
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        // 只有设置为 true，spring 才会加载 RabbitAdmin 这个类
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    //创建交换机和对列
    @Bean
    public void createExchangeQueue (){
        rabbitAdmin.declareExchange(orderExchange());
        rabbitAdmin.declareExchange(dlxExchange());
        rabbitAdmin.declareQueue(dlxQueue());
        rabbitAdmin.declareQueue(orderQueue());
    }
    /**
     * 声明订单业务队列
     *
     * @return Queue
     */
    @Bean
    public Queue orderQueue() {
        Map<String, Object> arguments = new HashMap<>(2);
        // 绑定该队列到私信交换机
        arguments.put("x-dead-letter-exchange", dlxExchange);
        arguments.put("x-dead-letter-routing-key", dlxRoutingKey);
        return new Queue(orderQueue, true, false, false, arguments);
    }

    /**
     * 绑定订单队列到订单交换机
     *
     * @return Binding
     */
    @Bean
    public Binding orderBinding() {
        return BindingBuilder.bind(orderQueue())
                .to(orderExchange())
                .with(orderRoutingKey);

    }

}

