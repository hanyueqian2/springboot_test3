package com.han.test.springboot_test3.rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;

/**
 * 生产者测试类
 */
public class ProductTest {
    private static final String EXCHANGE_NAME = "ExchangeTest1";

    public void publishMsg(String routingKey, String msg) {
        try {
            //使用Direct类型的交换机
            MyProducer.publishMsg(EXCHANGE_NAME, BuiltinExchangeType.TOPIC, routingKey, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("---------start------------");
        String queue = "QueueTest1";
        String routingKey = "RoutingKeyTest1";
        ProductTest rabbitMQTest = new ProductTest();
        for (int i = 0; i < 15; i++) {
            String msg = "hello >>>" + i;
            rabbitMQTest.publishMsg(routingKey, msg);
        }
        Thread.sleep(1000 * 2);
        System.out.println("---------over------------");
    }
}
