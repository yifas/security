package com.bin.project.service.impl;

import com.bin.project.dao.SysUserDao;
import com.bin.project.pojo.SysUser;
import com.bin.project.service.SysUserService;
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
    public List<SysUser> findUserList() {
        return sysUserDao.findUserList();
    }
}
