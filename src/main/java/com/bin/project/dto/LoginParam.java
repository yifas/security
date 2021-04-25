package com.bin.project.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class LoginParam implements Serializable {


    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    // @NotNull(message = "请选择登录类型")
    //private Integer role;
}
