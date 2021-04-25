package com.bin.project.controller;


import com.bin.common.PageQueryBean;
import com.bin.common.Result;
import com.bin.common.enums.ResultEnum;
import com.bin.project.dto.RegisterParam;
import com.bin.project.pojo.SysUser;
import com.bin.project.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 更新用户信息
     * @param id
     * @param sysUser
     * @return
     */
    @PutMapping(value = "/updateUser/{id}")
    public Result updateUser(@PathVariable Long id,@RequestBody SysUser sysUser) {
        sysUserService.updateUser(id,sysUser);
        return new Result(200,"更新成功");
    }

    /**
     * 用户信息回显
     * @param id
     * @return
     */
    @GetMapping("/findUserById/{id}")
    public Result findUserById(@PathVariable Long id){
        return new Result(200,"查询成功",sysUserService.findUserById(id));
    }

    /**
     * 新增用户(注册)
     * @param registerParam
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody @Validated RegisterParam registerParam){

     /*放在service 层校验了
     SysUser checkUnique = sysUserService.findByUsername(registerParam.getUsername());
        if (checkUnique==null){

        }*/
        //返回对象用于判断是否注册成功
        SysUser sysUser = sysUserService.register(registerParam);
        if (sysUser==null){
            return Result.error(ResultEnum.FAILURE);
        }
        return new Result(200,"新增成功",sysUser);
    }
}
