package com.han.test.springboot_test3.soap;

import org.springframework.stereotype.Service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(targetNamespace = WsConst.NAMESPACE_URI, name = "NBAPlayerSoap")
@Service
public interface NBAPlayerSoapService {
    /**
     * description  根据姓名获取球员信息
     * date         2019/10/15
     *
     * @param NBAPlayerName
     * @return cn.forward.springboot.cxf.service.entity.NBAPlayer
     */
    @WebMethod(operationName = "getNBAPlayerByName")
    NBAPlayer getNBAPlayerByName(@WebParam(name = "NBAPlayerName") String NBAPlayerName);

    /**
     * description  获取球员列表
     * date         2019/10/15
     *
     * @return java.util.List<cn.forward.springboot.cxf.service.entity.NBAPlayer>
     */
    @WebMethod
    List<NBAPlayer> getNBAPlayerList();

}
