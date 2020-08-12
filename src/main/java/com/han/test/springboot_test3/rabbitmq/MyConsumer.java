package com.han.test.springboot_test3.rabbitmq;

import com.han.test.springboot_test3.service.RabbitMQUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者
 */
public class MyConsumer {

    public static void consumerMsg(String exchange, String queue, String routingKey)
            throws IOException, TimeoutException {
        //创建连接
        Connection connection = RabbitMQUtil.getConnection();

        //创建消息信道
        final Channel channel = connection.createChannel();

        //声明一个消息队列
        channel.queueDeclare(queue, true, false, false, null);
        //将队列绑定到交换器，不需要额外的参数。
        channel.queueBind(queue, exchange, routingKey);

        //收到消息之后应该做的事
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String message = new String(body, "UTF-8");

                try {
                    System.out.println("[" + queue + "] Received '" + message);
                } finally {
                    System.out.println("[" + queue + "] Done");
                    //确认收到的一条或多条消息
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };

        //5、监听队列
        /*
         true:表示自动确认，只要消息从队列中获取，无论消费者获取到消息后是否成功消费，都会认为消息已经成功消费
         false:表示手动确认，消费者获取消息后，服务器会将该消息标记为不可用状态，等待消费者的反馈，
                如果消费者一直没有反馈，那么该消息将一直处于不可用状态，并且服务器会认为该消费者已经挂掉，不会再给其
                发送消息，直到该消费者反馈。
        */
        // 取消自动ack
        //使用服务器生成的consumerTag启动非nolocal、非独占的消费者。
        channel.basicConsume(queue, false, consumer);
        channel.basicConsume(queue, false, consumer);
        channel.basicConsume(queue, false, consumer);
        channel.basicConsume(queue, false, consumer);
        channel.basicConsume(queue, false, consumer);


    }
}
