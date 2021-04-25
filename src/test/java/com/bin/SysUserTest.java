package com.bin;

import com.bin.project.dao.SysUserDao;
import com.bin.project.pojo.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SysUserTest {

    @Autowired
    private SysUserDao sysUserDao;
    /**
     * 测试根据id查询用户信息
     */
    @Test
    void testFindUserById(){
        System.out.println(sysUserDao.findUserById(1L));
    }

    /**
     * 测试更新用户信息
     * 变量更新！
     */
    @Test
    void testUpdateUser(){
        SysUser sysUser  = new SysUser();
        sysUser.setId(1L);
        sysUser.setGender("男");
        sysUser.setStation("1");
        sysUserDao.updateUser(sysUser);
    }

    /**
     * 测试新增
     */
    void testRegister(){

    }
}
