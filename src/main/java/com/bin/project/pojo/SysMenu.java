package com.bin.project.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * name
     */
    private String name;

    /**
     * linkurl
     */
    private String linkUrl;

    /**
     * path
     */
    private String path;

    /**
     * priority
     */
    private Integer priority;

    /**
     * icon
     */
    private String icon;

    /**
     * description
     */
    private String description;

    /**
     * parentmenuid
     */
    private Integer parentmenuid;

    /**
     * level
     */
    private Integer level;

    /**
     * 动态路由
     */
    private String component;

    public SysMenu() {}
}