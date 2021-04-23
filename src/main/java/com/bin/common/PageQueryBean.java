package com.bin.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页查询条件类
 */
@Data
public class PageQueryBean implements Serializable {
    private Integer currentPage;//页码
    private Integer pageSize;//每页记录数
    private String queryString;//查询条件

}
