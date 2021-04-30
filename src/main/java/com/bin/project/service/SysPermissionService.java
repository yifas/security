package com.bin.project.service;

import com.bin.common.PageQueryBean;
import com.bin.common.PageResult;
import com.bin.project.dto.SysPermissionParam;
import com.bin.project.pojo.SysPermission;

import java.util.List;

public interface SysPermissionService {

    List<SysPermission> findPermListInfo();

    List<SysPermissionParam> findPermListTree();

    List<SysPermission> findPermListByRoleId(Long id);

    void updateRolePerm(Long id, List<Long> permIds);

    PageResult findPermList(PageQueryBean pageQueryBean);

    SysPermission findPermById(Long id);

    void addPerm(SysPermission sysPermission);

    void updatePerm(Long id, SysPermission sysPermission);
}
