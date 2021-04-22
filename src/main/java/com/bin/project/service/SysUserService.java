package com.bin.project.service;

import com.bin.project.pojo.SysUser;

import java.util.List;

public interface SysUserService {

    /**
     * 根据用户名查询对象
     * @param username
     * @return
     */
    SysUser findByUsername(String username);

    /**
     * 根据用户名查询角色
     * @param username
     * @return
     */
    List<String> findRoleByUsername(String username);

    /**
     * 查询所有用户
     * @return
     */
    List<SysUser> findUserList();
}
