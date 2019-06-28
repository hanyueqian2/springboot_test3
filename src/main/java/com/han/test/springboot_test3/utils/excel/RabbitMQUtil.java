package com.han.test.springboot_test3.utils.excel;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQUtil {

    private static String host;
    private static Integer port;
    private static String username;
    private static String password;

    @Value("${host}")
    private String ymlHost;
    @Value("${port}")
    private Integer ymlPort;
    @Value("${username}")
    private String ymlUsername;
    @Value("${password}")
    private String ymlPassword;

    //利用@PostConstruct将yml中配置的值赋给本地的变量
    @PostConstruct
    public void getHost(){
        host = this.ymlHost;
    }
    @PostConstruct
    public void getPort(){
        port = this.ymlPort;
    }
    @PostConstruct
    public void getUsername(){
        username = this.ymlUsername;
    }
    @PostConstruct
    public void getPassword(){
        password = this.ymlPassword;
    }

    /**
     * 创建连接
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        //1、定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //2、设置服务器地址
        factory.setHost("127.0.0.1");
        //3、设置端口
        factory.setPort(5672);
        //4、设置虚拟主机、用户名、密码
        factory.setVirtualHost("/");
        factory.setUsername("admin");
        factory.setPassword("123456");
        //5、通过连接工厂获取连接
        Connection connection = factory.newConnection();
        return connection;
    }

    /**
     * 创建消息信道
     * @param connection
     * @return
     * @throws IOException
     */
    public static Channel createChannel(Connection connection) throws IOException {
        if (connection == null) {
            throw new NullPointerException("createChannel()方法参数不能为空");
        }
        return connection.createChannel();
    }


    /**
     * 某个队列上注册消费者
     *
     * @param channel
     * @param consumer
     * @return 一个消费者与消息队列之间连接的标记, 用于取消连接
     * @throws IOException
     */
    public static String startConsume(Channel channel, Consumer consumer) throws IOException {
        //队列名称
        String queue = "";
        String consumerTag = channel.basicConsume(queue, consumer);
        return consumerTag;
    }

    /**
     * 取消消费者与消息队列之间的连接
     * @param channel
     * @param consumerTag 消费者与消息队列之间连接的标记
     * @throws IOException
     */
    public static void cancelConsume(Channel channel, String consumerTag) throws IOException {
        channel.basicCancel(consumerTag);
    }
    

}
