package com.bin.project.controller;

import com.bin.common.Result;
import com.bin.project.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perm")
public class SysPermissionController {


    @Autowired
    private SysPermissionService sysPermissionService;

    /**
     * 查询所有权限
     * @return
     */
    @GetMapping
    public Result findPermListInfo(){
        return new Result(200,"查询成功",sysPermissionService.findPermListInfo());
    }

    /**
     * 用于构建Tree数据
     * @return
     */
    @GetMapping("/findPermListTree")
    public Result findPermListTree(){
        return new Result(200,"查询成功",sysPermissionService.findPermListTree());
    }


    /**
     * 获取角色对应的权限
     * @param id
     * @return
     */
    @GetMapping("/findPermListByRoleId/{id}")
    public Result findPermListByRoleId(@PathVariable Long id){
        return new Result(200,"查询成功",sysPermissionService.findPermListByRoleId(id));
    }

    /**
     * 更新角色对应权限
     * @param id    roleID
     * @param permIds  权限ID集合
     * @return
     */
    @PostMapping("/updateRolePerm/{id}")
    public Result updateRolePerm(@PathVariable Long id,
                             @RequestBody List<Long> permIds){

        sysPermissionService.updateRolePerm(id,permIds);

        return new Result(200,"查询成功");
    }
}
