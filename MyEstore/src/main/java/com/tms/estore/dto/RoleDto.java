package com.tms.estore.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class RoleDto {

    private Long id;
    private String role;
}
