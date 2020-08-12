package com.han.test.springboot_test3.rabbitmq;

import com.han.test.springboot_test3.service.RabbitMQUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者测试类
 */
public class ConsumerTest {
    private static final String exchangeName = "direct.exchange";

    public static void msgConsumer() throws IOException, TimeoutException {
        String queue = "QueueTest1";
        String exchange = "ExchangeTest1";
        String routingKey = "RoutingKeyTest1";
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = RabbitMQUtil.createChannel(connection);


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
                    System.out.println(Thread.currentThread().getName());
                    synchronized (consumerTag) {
                        try {
                            consumerTag.wait(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } finally {
                    System.out.println("[" + queue + "] Done" + consumerTag);
                    //确认收到的一条或多条消息
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };

        channel.basicConsume(queue, false, consumer);
    }

    public static void main(String[] args) throws InterruptedException, IOException, TimeoutException {
        msgConsumer();
        msgConsumer();
        msgConsumer();
        msgConsumer();
        msgConsumer();
        System.out.println("------start---------");
        Thread.sleep(1000 * 30);

        Thread.sleep(1000 * 60 * 10);
    }
}

