package com.bin.project.controller;

import com.bin.common.PageQueryBean;
import com.bin.common.Result;
import com.bin.project.pojo.SysMenu;
import com.bin.project.pojo.SysPermission;
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


    /**
     *  分页查询
     * @param pageQueryBean
     * @return
     */
    @PostMapping("/findPermList")
    public Result findPermList(@RequestBody PageQueryBean pageQueryBean) {
        return new Result(200,"查询成功",sysPermissionService.findPermList(pageQueryBean));
    }


    /**
     * 信息回显
     * @param id
     * @return
     */
    @GetMapping("/findPermById/{id}")
    public Result findPermById(@PathVariable Long id){
        return new Result(200,"查询成功",sysPermissionService.findPermById(id));
    }


    /**
     * 新增权限
     * @param sysPermission
     * @return
     */
    @PostMapping("/addPerm")
    public Result addPerm(@RequestBody SysPermission sysPermission) {
        sysPermissionService.addPerm(sysPermission);
        return new Result(200,"查询成功");
    }

    /**
     * 修改权限信息
     * @param id
     * @param sysPermission
     * @return
     */
    @PutMapping(value = "/updatePerm/{id}")
    public Result updatePerm(@PathVariable Long id,@RequestBody SysPermission sysPermission) {
        sysPermissionService.updatePerm(id,sysPermission);
        return new Result(200,"更新成功");
    }


}
