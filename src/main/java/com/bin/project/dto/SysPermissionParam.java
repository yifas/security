package com.bin.project.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于封装Tree 树形控件参数
 */
@Data
public class SysPermissionParam implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 权限名
     */
    private String name;

    /**
     * 关键字【admin】
     */
    private String keyword;

    private List<SysPermissionParam> children = new ArrayList<>();
}
