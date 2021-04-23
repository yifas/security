package com.bin.project.controller;


import com.bin.common.PageQueryBean;
import com.bin.common.Result;
import com.bin.project.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户的增删改查
 */
@RestController
@RequestMapping("/user")
public class SysUserController {


    @Autowired
    private SysUserService sysUserService;

    //@PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test() {
        return "权限控制";
    }

    /**
     * 带条件的分页查询
     * 不传查询条件 为查询所有
     * @param pageQueryBean
     * @return
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/findUserList",method = RequestMethod.POST)
    public Result findUserList(@RequestBody PageQueryBean pageQueryBean) {
        return new Result(200,"查询成功",sysUserService.findUserList(pageQueryBean));
    }



}
