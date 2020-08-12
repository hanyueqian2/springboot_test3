package com.han.test.springboot_test3.soap;

import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

@WebService(
        targetNamespace = WsConst.NAMESPACE_URI, //wsdl命名空间
        name = "NBAPlayerSoap",                  //portType名称 客户端生成代码时 为接口名称
        serviceName = "NBAPlayerSoapService",    //服务name名称
        portName = "NBAPlayerPortName",          //port名称
        endpointInterface = "com.han.test.springboot_test3.soap.NBAPlayerSoapService")
public class NBAPlayerSoapImpl implements NBAPlayerSoapService {

    @Override
    public NBAPlayer getNBAPlayerByName(String name) {
        List<NBAPlayer> nbaPlayers = getNBAPlayerList();
        AtomicReference<NBAPlayer> player = new AtomicReference<>(nbaPlayers.get(new Random().nextInt(2)));
        nbaPlayers.forEach(nbaPlayer -> {
            if (nbaPlayer.getName().equals(name)) {
                player.set(nbaPlayer);
            }
        });
        return player.get();
    }
    @Override
    public List<NBAPlayer> getNBAPlayerList() {
        return generatorList();
    }
    public List<NBAPlayer> generatorList() {
        List<NBAPlayer> resultList = new ArrayList<>();
        NBAPlayer LBJ = new NBAPlayer();
        LBJ.setName("勒布朗·詹姆斯");
        LBJ.setTeam("洛杉矶湖人");
        LBJ.setTeams(Arrays.asList("克里夫蘭騎士（2003−2010）", "邁阿密熱火（2010−2014）"));
        LBJ.setPosition(Position.FORWARD);
        LBJ.setDescription(LBJ.toString());
        resultList.add(LBJ);
        NBAPlayer WS = new NBAPlayer();
        WS.setName("罗素·威斯布鲁克");
        WS.setTeam("休斯頓火箭");
        WS.setTeams(Arrays.asList("奧克拉荷馬雷霆 (2008-2019)"));
        WS.setPosition(Position.SHOOTING);
        WS.setDescription(WS.toString());
        resultList.add(WS);
        return resultList;
    }
}
