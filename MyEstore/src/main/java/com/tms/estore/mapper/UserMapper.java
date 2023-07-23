package com.tms.estore.mapper;

import com.tms.estore.domain.User;
import com.tms.estore.dto.UserFormDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CustomMapperConfig.class)
public interface UserMapper {

    @Mapping(source = "roleDto", target = "role")
    User convertToUser(UserFormDto user);

    @Mapping(source = "role", target = "roleDto")
    UserFormDto convertToUserFormDto(User user);
}
