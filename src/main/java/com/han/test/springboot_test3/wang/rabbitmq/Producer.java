package com.han.test.springboot_test3.wang.rabbitmq;


import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


/**
 * @program: rabbit-learn
 * @description: 生产者，订阅模式
 * @author:
 * @create:
 * 消息发送到没有队列绑定的交换机时，消息将丢失，因为，交换机没有存储消息的能力，消息只能存在在队列中。
 **/
public class Producer {

    //交换机名称
//    private static final String EXCHANGE_NAME = "test_exchange_fanout";
//    private static final String EXCHANGE_NAME = "test_exchange_direct";
//    private static final String EXCHANGE_NAME = "test_exchange_topic";
    private static final String EXCHANGE_NAME = "iwcs_exchange_A";
    private static String rkEventType = "EVENT_RESOURCE_RELEASED";

//    public static void fanoutExchange() throws InterruptedException, IOException, TimeoutException {
//        Connection connection = ConnectionUtil.getConnection();
//        Channel channel = connection.createChannel();
//        /*
//            声明exchange交换机
//            参数1：交换机名称
//            参数2：交换机类型
//            参数3：交换机持久性，如果为true则服务器重启时不会丢失
//            参数4：交换机在不被使用时是否删除
//            参数5：交换机的其他属性
//         */
//        channel.exchangeDeclare(EXCHANGE_NAME,"fanout", true,true,null);
//
//        String message = "订阅消息";
//        int idx = 0;
//        while (idx < 1000) {
//            String tmpMsg = message + ++idx;
//            channel.basicPublish(EXCHANGE_NAME, "", null, tmpMsg.getBytes("UTF-8"));
//            System.out.println("生产者 send ：" + tmpMsg);
//            Thread.sleep(1000);
//        }
//        channel.close();
//        connection.close();
//
//    }
//
//    public static void directExchange() throws InterruptedException, IOException, TimeoutException {
//        Connection connection = ConnectionUtil.getConnection();
//        Channel channel = connection.createChannel();
//        /*
//            声明exchange交换机
//            参数1：交换机名称
//            参数2：交换机类型
//            参数3：交换机持久性，如果为true则服务器重启时不会丢失
//            参数4：交换机在不被使用时是否删除
//            参数5：交换机的其他属性
//         */
//        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true,true,null);
//        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true,true,null);
//        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true,true,null);
//
//        String message = "订阅消息";
//        int idx = 0;
//        while (idx < 1000) {
//            String tmpMsg = message + ++idx;
//            channel.basicPublish(EXCHANGE_NAME, rkEventType, null, tmpMsg.getBytes("UTF-8"));
//            System.out.println("生产者 send ：" + tmpMsg);
//            Thread.sleep(1000);
//        }
//        channel.close();
//        connection.close();
//
//    }

    public static void topicExchange(String name) throws InterruptedException, IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        /*
            声明exchange交换机
            参数1：交换机名称
            参数2：交换机类型
            参数3：交换机持久性，如果为true则服务器重启时不会丢失
            参数4：交换机在不被使用时是否删除
            参数5：交换机的其他属性
         */
//        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC, true,false,null);

        //agingNewPod  bufferPod  test1
        String routeKey = "agv.test.test1";
        String message = "货架释放消息发送";
        int idx = 0;
        while (idx < 493) {
            String tmpMsg = message + ++idx;
            String mainTaskNum = "M" + name + idx;
//            channel.basicPublish(EXCHANGE_NAME, routeKey, null, tmpMsg.getBytes("UTF-8"));
            String jsonString = "{\"createdTime\":1564562737167,\"operationContent\":\"线体去老化区任务创建完成,主任务号:" + mainTaskNum + "\",\"operationStatus\":\"create_task\",\"resultFlag\":\"0\",\"subTaskNum\":\"S204090315194368\"}";
            channel.basicPublish("iwcs_exchange_A", "agv.task.taskLog", null, jsonString.getBytes("UTF-8"));
            System.out.println(name + "生产者 send pod ：" + tmpMsg);
//            tmpMsg = message + idx;
//            channel.basicPublish(EXCHANGE_NAME, "agv.pos.release", null, tmpMsg.getBytes("UTF-8"));
//            System.out.println("生产者 send  pos：" + tmpMsg);
        }
        channel.close();
        if ("BB".equals(name)) {
//            channel.basicPublish(EXCHANGE_NAME, routeKey, null, "OK".getBytes("UTF-8"));

            connection.close();
        }

    }

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        topicExchange("AA");
        topicExchange("BB");
    }


}