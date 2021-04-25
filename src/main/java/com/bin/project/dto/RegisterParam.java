package com.bin.project.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 注册信息对应前端表单
 */
@Data
public class RegisterParam implements Serializable {

    /**
     * username
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * password
     */
    @NotBlank(message = "密码不能为空")
    private String password;
    /**
     * birthday
     */

    private Date birthday;
    //private String birthday;
    /**
     * gender
     */
    @NotBlank(message = "性别不能为空")
    private String gender;

    /**
     * station
     */
    private String station;

    /**
     * telephone
     */
    @NotNull(message = "手机号不能为空")
    @Size(min = 11, max = 11, message = "手机号只能为11位")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    private String telephone;



}

