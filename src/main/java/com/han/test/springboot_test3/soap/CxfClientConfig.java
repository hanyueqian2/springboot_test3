package com.han.test.springboot_test3.soap;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CxfClientConfig {
    /**
     * 采用代理方式
     *
     * @return NBAPlayerSoap
     */
    @Bean
    public NBAPlayerSoapService createAuthorPortTypeProxy() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(NBAPlayerSoapService.class);
        jaxWsProxyFactoryBean.setAddress(WsConst.SERVICE_ADDRESS);
        return (NBAPlayerSoapService) jaxWsProxyFactoryBean.create();
    }

    /**
     * 采用动态工厂方式 不需要指定服务接口
     */
//    @Bean
//    public Client createDynamicClient() {
//        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
//        Client client = dcf.createClient(WsConst.SERVICE_ADDRESS);
//        return client;
//    }
}

