package com.bin.project.service.impl;

import com.bin.common.PageQueryBean;
import com.bin.common.PageResult;
import com.bin.project.dao.SysUserDao;
import com.bin.project.pojo.SysUser;
import com.bin.project.service.SysUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysUserServiceImpl implements SysUserService {


    @Autowired
    private SysUserDao sysUserDao;


    @Override
    public SysUser findByUsername(String username) {
        return sysUserDao.findByUsername(username);
    }

    @Override
    public List<String> findRoleByUsername(String username) {
        return sysUserDao.findRoleByUsername(username);
    }

    @Override
    public PageResult findUserList(PageQueryBean pageQueryBean) {
        Integer currentPage = pageQueryBean.getCurrentPage();
        Integer pageSize = pageQueryBean.getPageSize();
        String queryString = pageQueryBean.getQueryString();

        PageHelper.startPage(currentPage,pageSize);
        Page<SysUser> page = sysUserDao.findUserList(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void updateUser(Long id, SysUser sysUser) {
        sysUser.setId(id);
       // SysUser user = sysUserDao.findById(id);

        sysUserDao.updateUser(sysUser);

    }


}
