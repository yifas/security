package com.bin.project.dao;

import com.bin.project.pojo.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserRoleDao {

    /**
     * 根据用户ID删除User Role关系
     * @param id
     */
    void delRelationByUserId(@Param("id") Long id);

    /**
     * 新建关系
     * @param list
     */
    void insertList(List<SysUserRole> list);
}
