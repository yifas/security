package com.bin.security.service.impl;

import com.bin.project.dto.LoginParam;
import com.bin.security.component.UserInfo;
import com.bin.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.bin.common.constant.LoginConstant.FAILURE;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public UserInfo login(LoginParam loginParam) {
        String username = loginParam.getUsername();
        String password = loginParam.getPassword();
        //是否走重写的逻辑?
        //UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UserInfo userInfo = (UserInfo) userDetailsService.loadUserByUsername(username);
        //checkRole(userInfo.getRole(), loginParam.getRole());
        String detailsPassword = userInfo.getPassword();
        //密码验证
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(password,detailsPassword)) {
            //todo
            //添加统一异常处理类  处理
            throw new RuntimeException(FAILURE);
        }
        return userInfo;
    }
}
