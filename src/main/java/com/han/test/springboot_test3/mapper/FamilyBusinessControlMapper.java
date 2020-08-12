package com.han.test.springboot_test3.mapper;

import java.util.List;
import java.util.Map;

import com.han.test.springboot_test3.domain.FamilyBusinessControl;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2020-08-12 10:05:37.
 */
@Repository
public interface FamilyBusinessControlMapper {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<FamilyBusinessControl> selectPage(Map map);

    int insertList(List<FamilyBusinessControl> list);
}