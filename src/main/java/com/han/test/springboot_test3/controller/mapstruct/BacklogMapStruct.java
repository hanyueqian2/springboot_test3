package com.han.test.springboot_test3.controller.mapstruct;

import com.han.test.springboot_test3.domain.Backlog;
import com.han.test.springboot_test3.domain.dto.BacklogDto;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring")
public interface BacklogMapStruct {

//    BacklogMapStruct INSTANCE = Mappers.getMapper(BacklogMapStruct.class);

    BacklogDto toDto(Backlog backlog);
}
