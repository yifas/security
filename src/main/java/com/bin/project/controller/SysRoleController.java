package com.bin.project.controller;

import com.bin.common.Result;
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
     * 查询所有角色信息
     * @return
     */
    @GetMapping
    public Result roleList(){
        return new Result(200,"查询成功",sysRoleService.roleList());
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
    @PostMapping("/updateRole")
    public Result updateRole(@RequestParam("id") Long id,
                             @RequestParam("roleIds") List<Long> roleIds){

        //返回影响行数 判断是否成功

        int count = sysRoleService.updateRole(id,roleIds);
        if (count>0){

            return new Result(200,"修改成功",count);
        }
        return Result.error();
    }

}
