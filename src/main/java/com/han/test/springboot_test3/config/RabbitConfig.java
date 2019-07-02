package com.han.test.springboot_test3.config;

import com.han.test.springboot_test3.spring.rabbitmq.SpringConsumer;
import com.han.test.springboot_test3.spring.rabbitmq.SpringProducer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sound.midi.Receiver;


@Configuration
public class RabbitConfig {

    public static final String EXCHANGE_A = "exchange_A";
    public static final String EXCHANGE_B = "exchange_B";
    public static final String EXCHANGE_C = "exchange_C";


    public static final String QUEUE_A = "QUEUE_A";
    public static final String QUEUE_B = "QUEUE_B";
    public static final String QUEUE_C = "QUEUE_C";

    public static final String ROUTINGKEY_A = "routingKey_A";
    public static final String ROUTINGKEY_B = "routingKey_B";
    public static final String ROUTINGKEY_C = "routingKey_C";

    @Bean
    public Queue queue() {
        // 是否持久化
        boolean durable = true;
        // 仅创建者可以使用的私有队列，断开后自动删除
        boolean exclusive = false;
        // 当所有消费客户端连接断开后，是否自动删除队列
        boolean autoDelete = false;
        return new Queue(QUEUE_A, durable, exclusive, autoDelete);
    }

    /**
     * 默认的交换机使用的是DirectExchange,这里使用----类型的交换机
     * @return
     */
    @Bean
    public TopicExchange exchange() {
        // 是否持久化
        boolean durable = true;
        // 当所有消费客户端连接断开后，是否自动删除队列
        boolean autoDelete = false;
        return new TopicExchange(EXCHANGE_A, durable, autoDelete);
    }

    /**
     * 绑定一个key ROUTINGKEY_A，当消息匹配到就会放到这个队列中
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_A);
    }

//    @Bean
//    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
//                                             MessageListenerAdapter listenerAdapter) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(QUEUE_A);
//        container.setMessageListener(listenerAdapter);
//        return container;
//    }
//
//    @Bean
//    MessageListenerAdapter listenerAdapter(SpringConsumer springConsumer) {
//        return new MessageListenerAdapter(springConsumer, "process");
//    }

}

