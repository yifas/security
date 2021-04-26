package com.bin.project.service.impl;
import java.util.ArrayList;
import java.util.Date;

import com.bin.common.PageQueryBean;
import com.bin.common.PageResult;
import com.bin.project.dao.SysUserDao;
import com.bin.project.dto.RegisterParam;
import com.bin.project.pojo.SysUser;
import com.bin.project.service.SysUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Override
    public SysUser findUserById(Long id) {
        return sysUserDao.findUserById(id);
    }

    @Override
    public SysUser register(RegisterParam registerParam) {
        SysUser sysUser = new SysUser();
        //属性设置
        BeanUtils.copyProperties(registerParam,sysUser);
        //sysUser.setBirthday(DateUtil.fomatDate(registerParam.getBirthday()));
        sysUser.setCreateTime(new Date());
        sysUser.setLoginTime(new Date());
        //查询用户名是否重复
        SysUser checkUnique = sysUserDao.findByUsername(registerParam.getUsername());
        //重复 返回null
        if (checkUnique!=null){
            return null;
        }
        //密码BCrypt加密存储
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(sysUser.getPassword());
        sysUser.setPassword(encodePassword);
        //日期处理
        //sysUser.setBirthday(DateUtil.fomatDate(sysUser.getBirthday().toString()));
        sysUserDao.register(sysUser);
        return sysUser;
    }

    @Override
    public List<String> getUserAuthorityInfo(Long id) {
        List<String> list =new ArrayList<>();
        //待修改
        for (String s : sysUserDao.getUserRoleInfo(id)) {
            list.add(s);
        }
        for (String s : sysUserDao.getUserAuthInfo(id)) {
            list.add(s);
        }
        return list;
    }


}
