package com.bin.project.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysRolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 权限id
     */
    private Long permissionId;

    public SysRolePermission() {}
}