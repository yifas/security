package com.bin.project.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * name
     */
    private String name;

    /**
     * keyword
     */
    private String keyword;

    /**
     * description
     */
    private String description;

    public SysPermission() {}
}