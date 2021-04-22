package com.bin.project.dao;

import com.bin.project.pojo.SysPermission;
import com.bin.project.pojo.SysRole;
import com.bin.project.pojo.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface SysUserDao {


    /**
     * 查询用户是否存在
     * @return
     */
    SysUser findByUsername(@Param("username") String username);

    /**
     * 根据用户名 查询用户角色
     * @param username
     * @return
     */
    List<String> findRoleByUsername(@Param("username") String username);

    /**
     * 查询所有用户
     * @return
     */
    List<SysUser> findUserList();

    /**
     * 查询Role集合
     * @param username
     * @return
     */
    Set<SysRole> findRole(@Param("username") String username);

    /**
     * 根据role ID查询对应的auth name
     * @param id
     * @return
     */
    Set<SysPermission> findPermission(@Param("id") Integer id);
}
