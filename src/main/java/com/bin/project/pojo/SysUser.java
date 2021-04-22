package com.bin.project.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * birthday
     */
    private Date birthday;

    /**
     * gender
     */
    private String gender;

    /**
     * username
     */
    private String username;

    /**
     * password
     */
    private String password;

    /**
     * remark
     */
    private String remark;

    /**
     * station
     */
    private String station;

    /**
     * telephone
     */
    private String telephone;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 登陆时间
     */
    private Date loginTime;

    public SysUser() {}
}