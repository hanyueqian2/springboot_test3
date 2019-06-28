package com.han.test.springboot_test3.rabbitmq;

import com.han.test.springboot_test3.utils.excel.RabbitMQUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 */
public class MyProducer {

    public static void publishMsg(String exchange, BuiltinExchangeType exchangeType, String toutingKey, String message)
            throws IOException, TimeoutException {

        //创建连接
        Connection connection = RabbitMQUtil.getConnection();

        //创建消息通道
        Channel channel = connection.createChannel();

        // 声明exchange中的消息为可持久化，不自动删除
        channel.exchangeDeclare(exchange, exchangeType, true, false, null);

        // 发布消息
        channel.basicPublish(exchange, toutingKey, null, message.getBytes());

        channel.close();
        connection.close();
    }
}
