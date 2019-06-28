package com.han.test.springboot_test3.rabbitmq;

import com.han.test.springboot_test3.utils.excel.RabbitMQUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者测试类
 */
public class ConsumerTest {
    private static final String exchangeName = "direct.exchange";

    public void msgConsumer(String queueName, String routingKey) {
        try {
            MyConsumer.consumerMsg(exchangeName, queueName, routingKey);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException, TimeoutException {
//        ConsumerTest consumerTest = new ConsumerTest();
//        String[] routingKey = new String[]{"aaa", "bbb"};
//        String[] queueNames = new String[]{"qa", "qb"};
//
//        for (int i = 0; i < 2; i++) {
//            consumerTest.msgConsumer(queueNames[i], routingKey[i]);
//        }
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
                } finally {
                    System.out.println("[" + queue + "] Done");
                    //确认收到的一条或多条消息
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };

        String tag = RabbitMQUtil.startConsume(channel, consumer);
        System.out.println("Tag : " + tag);
        Thread.sleep(1000 * 40);
        RabbitMQUtil.cancelConsume(channel, tag);

        Thread.sleep(1000 * 60 * 10);
    }
}
