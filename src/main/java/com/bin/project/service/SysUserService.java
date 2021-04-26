package com.bin.project.service;

import com.bin.common.PageQueryBean;
import com.bin.common.PageResult;
import com.bin.project.dto.RegisterParam;
import com.bin.project.pojo.SysUser;
import org.springframework.transaction.annotation.Transactional;

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
     * @return 分页结果对象
     */
    PageResult findUserList(PageQueryBean pageQueryBean);

    /**
     * 更新用户信息
     * @param id
     * @param sysUser
     */
    @Transactional
    void updateUser(Long id, SysUser sysUser);

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    SysUser findUserById(Long id);

    /**
     * 新增用户
     * @param registerParam
     * @return
     */
    SysUser register(RegisterParam registerParam);

    /**
     * 根据ID获得所有的Role和Permission
     * @param id
     * @return
     */
    List<String> getUserAuthorityInfo(Long id);
}
