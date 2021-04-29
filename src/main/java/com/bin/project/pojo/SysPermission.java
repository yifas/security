package com.bin.project.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

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

    /**
     * 描述
     */
    private String description;

    /**
     * 父id
     */
    private Long pid;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 资源路径
     */
    private String uri;

    /**
     * 状态
     */
    private Integer status;

    public SysPermission() {}
}