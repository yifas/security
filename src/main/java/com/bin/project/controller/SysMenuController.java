package com.bin.project.controller;

import com.bin.common.Result;
import com.bin.project.dto.SysMenuParam;
import com.bin.project.pojo.SysUser;
import com.bin.project.service.SysMenuService;
import com.bin.project.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class SysMenuController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 用户当前用户的菜单和权限信息
     * @param principal
     * @return
     */
    @GetMapping("/nav")
    public Result nav(Principal principal) {
        SysUser sysUser = sysUserService.findByUsername(principal.getName());

        // 获取当前权限信息
        // ROLE_admin,ROLE_normal,sys:user:list,....
        List<String> userAuthorityInfo = sysUserService.getUserAuthorityInfo(sysUser.getId());

        Map map = new HashMap<>();
        map.put("authoritys",userAuthorityInfo);

        // 获取导航栏信息
        List<SysMenuParam> menuList = sysMenuService.getUserMenuInfo();

        map.put("nav",menuList);
        return new Result(200,"返回菜单成功",map);
    }
}
