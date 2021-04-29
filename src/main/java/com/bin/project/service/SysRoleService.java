package com.bin.project.service;

import com.bin.common.PageQueryBean;
import com.bin.common.PageResult;
import com.bin.project.pojo.SysRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SysRoleService {

    PageResult roleList(PageQueryBean pageQueryBean);

    List<SysRole> findRoleAlreadyLogin();

    List<SysRole> findRoleById(Long id);

    @Transactional
    int updateRole(Long id, List<Long> roleIds);

    SysRole findRoleByRoleId(Long id);

    void updateRoleInfo(Long id, SysRole sysRole);

    List<SysRole> findRoleList();

    void addRole(SysRole sysRole);
}
