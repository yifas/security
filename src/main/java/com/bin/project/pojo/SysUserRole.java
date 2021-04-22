package com.bin.project.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;

    public SysUserRole() {}
}