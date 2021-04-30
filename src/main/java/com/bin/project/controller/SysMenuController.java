package com.bin.project.controller;

import com.bin.common.PageQueryBean;
import com.bin.common.Result;
import com.bin.common.enums.ResultEnum;
import com.bin.project.dto.SysMenuParam;
import com.bin.project.pojo.SysMenu;
import com.bin.project.pojo.SysUser;
import com.bin.project.service.SysMenuService;
import com.bin.project.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        if (principal==null){
            return Result.error(ResultEnum.LOGIN_OVERDUE);
        }
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


    /**
     * 用于构建Tree数据
     * @return
     */
    @GetMapping("/findMenuListTree")
    public Result findMenuListTree(){
        return new Result(200,"查询成功",sysMenuService.findMenuListTree());
    }


    @GetMapping("/findMenuListByRoleId/{id}")
    public Result findMenuListByRoleId(@PathVariable Long id){
        return new Result(200,"查询成功",sysMenuService.findMenuListByRoleId(id));
    }

    /**
     * 更新角色对应菜单
     * @param id    roleID
     * @param menuIds  权限ID集合
     * @return
     */
    @PostMapping("/updateRoleMenu/{id}")
    public Result updateRoleMenu(@PathVariable Long id,
                                 @RequestBody List<Long> menuIds){

        sysMenuService.updateRoleMenu(id,menuIds);

        return new Result(200,"查询成功");
    }

    /**
     *  分页查询
     * @param pageQueryBean
     * @return
     */
    @PostMapping("/findMenuList")
    public Result findMenuList(@RequestBody PageQueryBean pageQueryBean) {
        return new Result(200,"查询成功",sysMenuService.findMenuList(pageQueryBean));
    }

    /**
     * 信息回显
     * @param id
     * @return
     */
    @GetMapping("/findMenuById/{id}")
    public Result findMenuById(@PathVariable Long id){
        return new Result(200,"查询成功",sysMenuService.findMenuById(id));
    }

    /**
     * 修改菜单信息
     * @param id
     * @param sysMenu
     * @return
     */
    @PutMapping(value = "/updateMenu/{id}")
    public Result updateMenu(@PathVariable Long id,@RequestBody SysMenu sysMenu) {
        sysMenuService.updateMenu(id,sysMenu);
        return new Result(200,"更新成功");
    }

    /**
     * 新增菜单
     * @param sysMenu
     * @return
     */
    @PostMapping("/addMenu")
    public Result addMenu(@RequestBody SysMenu sysMenu) {
        sysMenuService.addMenu(sysMenu);
        return new Result(200,"查询成功");
    }
}
