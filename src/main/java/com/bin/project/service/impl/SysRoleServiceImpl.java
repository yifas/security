package com.bin.project.service.impl;

import com.bin.project.dao.SysRoleDao;
import com.bin.project.dao.SysUserRoleDao;
import com.bin.project.pojo.SysRole;
import com.bin.project.pojo.SysUserRole;
import com.bin.project.service.SysRoleService;
import com.bin.security.component.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {


    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private SysUserRoleDao sysUserRoleDao;


    @Override
    public List<SysRole> roleList() {
        return sysRoleDao.roleList();
    }

    @Override
    public List<SysRole> findRoleAlreadyLogin() {

        UserInfo principal = null;
        try {
            principal = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            //一般加了权限 在权限层会被拦截掉（配置登陆后访问）  没有携带Auth访问接口出现的
            e.printStackTrace();
        }
        //System.out.println(principal.getUsername());
        return sysRoleDao.findRoleAlreadyLogin(principal.getUsername());
    }

    @Override
    public List<SysRole> findRoleById(Long id) {
        return sysRoleDao.findRoleById(id);
    }

    @Override
    public int updateRole(Long id, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
        //删除数据中原来的关系【user_role】
        sysUserRoleDao.delRelationByUserId(id);
        //测试事务是否生效
        //【注】捕获异常或导致事务失效
        //int i = 1/0;
        //建立新的关系（true）
        if (!CollectionUtils.isEmpty(roleIds)) {
            List<SysUserRole> list = new ArrayList<>();
            for (Long roleId : roleIds) {
                //将roleID插入关系表
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(id);
                sysUserRole.setRoleId(roleId);
                list.add(sysUserRole);
            }

            sysUserRoleDao.insertList(list);
        }
        //todo
        //还需要删除Permission
        //删除menu
        return count;
    }
}
