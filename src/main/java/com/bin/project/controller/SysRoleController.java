package com.bin.project.controller;

import com.bin.common.PageQueryBean;
import com.bin.common.Result;
import com.bin.project.pojo.SysRole;
import com.bin.project.pojo.SysUser;
import com.bin.project.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@Slf4j
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;


    /**
     * 分页查询所有角色信息
     * @return
     */
    @PostMapping("/findAllRole")
    public Result roleList(@RequestBody PageQueryBean pageQueryBean){
        return new Result(200,"查询成功",sysRoleService.roleList(pageQueryBean));
    }

    /**
     * 查询所有角色信息
     * @return
     */
    @GetMapping
    public Result findRoleList(){
        return new Result(200,"查询成功",sysRoleService.findRoleList());
    }


    /**
     * 获取已登录用户的角色
     * @return
     */
    @GetMapping("/findRoleAlreadyLogin")
    public Result findRoleAlreadyLogin(){
        return new Result(200,"查询成功",sysRoleService.findRoleAlreadyLogin());
    }


    /**
     * 用于修改某个用户的角色时查询
     * @param id
     * @return
     */
    @GetMapping("/findRoleById/{id}")
    public Result findRoleById(@PathVariable Long id){
        return new Result(200,"查询成功",sysRoleService.findRoleById(id));
    }

    /**
     * 修改用户角色
     * @param id  用户ID
     * @param roleIds  角色ID
     * @return
     */
    @PostMapping("/updateRole/{id}")
    public Result updateRole(@PathVariable Long id,
                             @RequestBody List<Long> roleIds){

        //返回影响行数 判断是否成功

        int count = sysRoleService.updateRole(id,roleIds);
        if (count>0){

            return new Result(200,"修改成功",count);
        }
        return Result.error();
    }

    /**
     * 增删改查的信息回显
     * @param id
     * @return
     */
    @GetMapping("/findRoleByRoleId/{id}")
    public Result findRoleByRoleId(@PathVariable Long id){
        return new Result(200,"查询成功",sysRoleService.findRoleByRoleId(id));
    }

    /**
     * 修改角色
     * @param id
     * @param sysRole
     * @return
     */
    @PostMapping(value = "/updateRoleInfo/{id}")
    public Result updateRoleInfo(@PathVariable Long id,@RequestBody SysRole sysRole) {

        sysRoleService.updateRoleInfo(id,sysRole);
        return new Result(200,"更新成功");
    }

    /**
     * 新增角色
     * @param sysRole
     * @return
     */
    @PostMapping(value = "/addRole")
    public Result addRole(@RequestBody SysRole sysRole) {

        sysRoleService.addRole(sysRole);
        return new Result(200,"新增成功");

    }
}
