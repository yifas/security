package com.bin.security.controller;


import com.bin.common.Result;
import com.bin.common.util.JwtTokenUtil;
import com.bin.common.util.RedisUtil;
import com.bin.project.dto.LoginParam;
import com.bin.project.pojo.SysUser;
import com.bin.project.service.SysUserService;
import com.bin.security.component.UserInfo;
import com.bin.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import static com.bin.common.constant.LoginConstant.BEARER;
import static com.bin.common.constant.LoginConstant.SUCCESS;

@RestController
@RequestMapping("/auth")
public class AuthController {


    //expireTime 到期时间
    @Value("${token.expireTime}")
    private int expireTime;


    @Autowired
    private AuthService authService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(@RequestBody @Validated LoginParam loginParam,
                        HttpServletRequest request, HttpServletResponse response){
        UserInfo userInfo = authService.login(loginParam);
        return getLoginResult(request, response, userInfo);
    }

    /**
     * 获取用户信息接口
     * @param principal
     * @return
     */
    @GetMapping("/userInfo")
    public Result userInfo(Principal principal) {

        SysUser sysUser = sysUserService.findByUsername(principal.getName());

        return new Result(200,"获取用户信息成功",sysUser);
    }

    /**
     * 公共方法抽取
     * @param request
     * @param response
     * @return
     */
    private Result getLoginResult(HttpServletRequest request, HttpServletResponse response, UserInfo userInfo) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("id", String.valueOf(userInfo.getId()));
        // 签发token
        //String jwtToken = JwtUtil.generateToken(userInfo.getUsername(), expireTime, map);
        String jwtToken = JwtTokenUtil.generateToken(userInfo.getUsername(), expireTime, map);
        //todo
        //将Token存储到redis中
        redisUtil.setTokenRefresh(jwtToken);
        // 将token放入header返回，Access-Control-Expose-Headers解决自定义请求头前端获取不到的问题
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        response.setHeader("Authorization", BEARER + jwtToken);
        return Result.success(SUCCESS, userInfo);
    }
}
