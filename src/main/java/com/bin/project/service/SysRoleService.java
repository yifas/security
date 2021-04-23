package com.bin.project.service;

import com.bin.project.pojo.SysRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SysRoleService {

    List<SysRole> roleList();

    List<SysRole> findRoleAlreadyLogin();

    List<SysRole> findRoleById(Long id);

    @Transactional
    int updateRole(Long id, List<Long> roleIds);
}
