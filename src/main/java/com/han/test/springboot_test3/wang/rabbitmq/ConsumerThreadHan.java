package com.han.test.springboot_test3.wang.rabbitmq;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class ConsumerThreadHan implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(ConsumerThreadHan.class);
    private Channel channel;
    private static final String QUEUE_NAME = "agv_pod_queue3";
    private static final String EXCHANGE_NAME = "iwcs_exchange_A";
    /**
     *   TODO 存放一个大对象
     */
    private List<String> bigList = new ArrayList<>();

    private Object lock = new Object();

    public ConsumerThreadHan() {
    }

    @Override
    public void run() {
        while (true){
            try {
                logger.info("Thread started.");
                listenMsg();
                myListenMsg();
                synchronized (lock) {
                    lock.wait();
                }
                logger.info("Thread exited.");
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void myListenMsg() {
        String queueName = "task_log_queue";
        Connection connection = ConnectionUtil.getNewConnection();
        Channel channel;
        try {
            channel = connection.createChannel();
            String queue = channel.queueDeclare(queueName, false, false, true, null).getQueue();
            logger.debug("建立消息队列成功:" + queue);
            channel.queueBind(queue, "iwcs_exchange_A", "agv.task.taskLog");
            logger.debug("交换机与消息队列绑定成功:" + queue);
            //每次仅处理一条消息
            channel.basicQos(1);

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    logger.info("队列名称:{} routeKey:{} 信息:{}", queueName, envelope.getRoutingKey() , message);
                    //调用消费者活动
//                    consumerAction.action(new ConsumerActionInfo(message, queueName));
                    System.out.println("消息日志:" + message);
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    channel.basicAck(envelope.getDeliveryTag(), false);
                    //返回确认状态
                    if (!queueName.contains("_")) {
                        return;
                    }
                    //获取子任务单号
                    String subTaskNum = queueName.split("_")[1];
                    //当队列消息含有结束标识,并且含有启动这个子任务的子任务号时,则认为这个子任务已经执行完了,可以关闭这个消息队列了
                    if (message.contains("OK") && message.contains(subTaskNum)){
                        logger.info("{}队列的连接将被关闭: {}, routeKey:{}", queueName, consumerTag, envelope.getRoutingKey());
                        channel.basicCancel(consumerTag);
                        try {
                            channel.close();
                        } catch (TimeoutException e) {
                            e.printStackTrace();
                        }
                        synchronized (lock) {
                            lock.notifyAll();
                        }
                    }
                }
            };
                //false是取消自动应答机制,开启手动应答机制
            boolean autoAck = false;
            String consumerTag = channel.basicConsume(queueName, autoAck, consumer);
            logger.info("Consume with tag: {}", consumerTag);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listenMsg(){
        Connection connection = ConnectionUtil.getNewConnection();
        try {
            Channel channel = connection.createChannel();

            try {
                boolean durable = true;
                boolean autoDelete = false;
                channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC, durable, autoDelete, null);
//                boolean durable, boolean exclusive, boolean autoDelete,
                String queueName = channel.queueDeclare(QUEUE_NAME + UUID.randomUUID().toString().substring(0,6), false, false, true, null).getQueue();
                String routeKey = "agv.test.test1";
                channel.queueBind(queueName, EXCHANGE_NAME, routeKey);
                channel.basicQos(1);

                Consumer tConsumer = new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        String message = new String(body, "UTF-8");
                        channel.basicAck(envelope.getDeliveryTag(), false);
                        System.out.println("ReceiveLogsTopic1 [" + queueName + "] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (message.contains("StopMe")){
                            channel.basicCancel(consumerTag);
                            try {
                                channel.close();
//                                connection.close();
                            } catch (TimeoutException e) {
                                e.printStackTrace();
                            }

                            synchronized (lock) {
                                lock.notifyAll();
                            }
                        }
                    }
                };
                String consumerTag = channel.basicConsume(queueName, false, tConsumer);
                logger.info("Consume with tag: {}", consumerTag);

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String [] args){
        for (int i = 0; i < 1; i++) {
            // TODO 给线程起个名
            Thread t = new Thread(new ConsumerThreadHan() , "线程" + (i + 1));
            t.start();
        }


    }

}
