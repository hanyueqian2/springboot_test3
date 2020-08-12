package com.han.test.springboot_test3.mapper;

import java.util.List;
import java.util.Map;

import com.han.test.springboot_test3.domain.WmsAlley;
import com.han.test.springboot_test3.service.arithmetic.AlleyTypeAndNum;
import com.han.test.springboot_test3.service.arithmetic.WmsAlleyDTO;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2020-02-27 13:59:58.
 */
@Repository
public interface WmsAlleyMapper{
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<WmsAlleyDTO> selectPage(Map map);


    List<WmsAlleyDTO> selectAll();
    List<WmsAlleyDTO> selectNotFull();


    List<AlleyTypeAndNum> selectAllAlleyType();


    List<WmsAlleyDTO> selectByGoodsType(String goodsType);

    int updateByCode(WmsAlleyDTO wmsAlley);
}