package com.han.test.springboot_test3.soap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/wisdom")
public class NBAPlayerController {

    @Resource
    NBAPlayerSoapService nbaPlayerSoapService;

    /**
     * description  根据球员名称获取球员信息
     * date         2019/10/15
     *
     * @param nbaPlayerName
     * @return cn.lqdev.webservice.NbaPlayer
     */
    @GetMapping("/getNBAPlayerByName")
    public NBAPlayer getNBAPlayerByName(@RequestParam("nbaPlayerName") String nbaPlayerName) {
        System.out.println("成功访问1");
        return nbaPlayerSoapService.getNBAPlayerByName(nbaPlayerName);
    }

    /**
     * description  获取全部球员信息
     * date         2019/10/15
     * @return java.util.List<cn.lqdev.webservice.NbaPlayer>
     */
    @GetMapping("/getNBAPlayerList")
    public List<NBAPlayer> getNBAPlayerList() {
        System.out.println("成功访问2");
        return nbaPlayerSoapService.getNBAPlayerList();
    }
}
