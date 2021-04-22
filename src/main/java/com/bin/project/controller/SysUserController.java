package com.bin.project.controller;


import com.bin.common.Result;
import com.bin.project.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class SysUserController {


    @Autowired
    private SysUserService sysUserService;

    //@PreAuthorize("hasRole(ROLE_USER)")
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test() {
        return "权限控制";
    }

    //@PreAuthorize("hasRole(ROLE_ADMIN)")
    @RequestMapping(value = "/findUserList",method = RequestMethod.GET)
    public Result findUserList() {
        return new Result(200,"查询成功",sysUserService.findUserList());
    }
}
