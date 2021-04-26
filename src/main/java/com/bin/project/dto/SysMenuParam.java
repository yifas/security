package com.bin.project.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class SysMenuParam implements Serializable {

    private Long id;
    private String name;
    private String linkUrl;
    private String path;
    private Integer parentMenuId;
    private Integer level;
    private List<SysMenuParam> children=new ArrayList<>();
}
