package com.bin.project.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysRole implements Serializable {

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
     * keyword
     */
    private String keyword;

    /**
     * description
     */
    private String description;

    public SysRole() {}
}