package com.bin.project.service;

import com.bin.project.dto.SysPermissionParam;
import com.bin.project.pojo.SysPermission;

import java.util.List;

public interface SysPermissionService {

    List<SysPermission> findPermListInfo();

    List<SysPermissionParam> findPermListTree();

    List<SysPermission> findPermListByRoleId(Long id);

    void updateRolePerm(Long id, List<Long> permIds);
}
