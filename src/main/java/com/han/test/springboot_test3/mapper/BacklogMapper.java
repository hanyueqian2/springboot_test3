package com.han.test.springboot_test3.mapper;

import com.han.test.springboot_test3.domain.Backlog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface BacklogMapper extends Mapper<Backlog> {

    List<Backlog> getList(@Param("leaveTime") Long leaveTime,
                          @Param("module") String module,
                          @Param("task") String task);//查找指定离港时间的数据

    //void save(@Param("backlogs") List<Backlog> backlogs);//添加


    //void update(@Param("backlogs") List<Backlog> backlogs);//更新

    List<Backlog> selectMany(@Param("testNum") Integer testNum);

    int updateSome(@Param("testNum") Integer testNum);
    int updateSome1(@Param("testNum") Integer testNum);

}
