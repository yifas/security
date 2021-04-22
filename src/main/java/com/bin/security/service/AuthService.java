package com.bin.security.service;

import com.bin.project.dto.LoginParam;
import com.bin.security.component.UserInfo;

public interface AuthService {

    /**
     * 登录
     * @param loginParam 登录信息
     * @return 用户信息
     */
    UserInfo login(LoginParam loginParam);


    /**
     * 注册
     * @param registerParam 注册信息
     * @return 用户信息
     */
    //UserInfo register(RegisterParam registerParam);

}
