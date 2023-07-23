package com.tms.estore.service.impl;

import com.tms.estore.domain.Role;
import com.tms.estore.dto.RoleDto;
import com.tms.estore.mapper.RoleMapper;
import com.tms.estore.repository.RoleRepository;
import com.tms.estore.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public RoleDto getRole(String role) {
        Role userRole = roleRepository.findRoleByRole(role)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        return roleMapper.convertToRoleDto(userRole);
    }
}
