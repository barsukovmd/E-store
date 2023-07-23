package com.tms.estore.mapper;

import com.tms.estore.domain.Role;
import com.tms.estore.dto.RoleDto;
import org.mapstruct.Mapper;

@Mapper(config = CustomMapperConfig.class)
public interface RoleMapper {

    RoleDto convertToRoleDto(Role role);
}
