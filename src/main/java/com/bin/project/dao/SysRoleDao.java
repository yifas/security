package com.bin.project.dao;

import com.bin.project.pojo.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleDao {

    /**
     * 查询所有角色
     * @return
     */
    List<SysRole> roleList();

    /**
     * 获取当前用户的角色
     * @param username
     * @return
     */
    List<SysRole> findRoleAlreadyLogin(@Param("username") String username);

    /**
     * 根据id查询角色
     * @param id
     * @return
     */
    List<SysRole> findRoleById(@Param("id")Long id);
}
