package com.bin.security.component;

import com.bin.common.constant.LoginConstant;
import com.bin.project.dao.SysUserDao;
import com.bin.project.pojo.SysPermission;
import com.bin.project.pojo.SysRole;
import com.bin.project.pojo.SysUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 用于实现自定义的登录逻辑
 * 获取保存在服务端的用户信息类
 * Spring Security 提供的 UserDetailsService有一个通过名字返回 Spring Security 可用于身份验证的UserDetails对象的方法：loadUserByUsername()。
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //从数据库查询用户名是否存在
        SysUser result = sysUserDao.findByUsername(username.trim());
        if (result==null){
            throw new UsernameNotFoundException(LoginConstant.USER_DOES_NOT_EXIST);
        }else {
            //查询用户名对应的所有role  List<Role> 可以为Role对象 get需要的数据
            List<String> roleList = sysUserDao.findRoleByUsername(username.trim());
            //todo  遍历list  查询对应的menu  Role需要前缀
            //#根据username查询ROle
            Set<SysRole> roleSet = sysUserDao.findRole(username);
            //存储权限数据
            HashSet<GrantedAuthority> set = new HashSet<>();
            //根据Role查询对应的Auth 封装到  HashSet<GrantedAuthority> set
            for (SysRole role : roleSet) {
                set.add(new SimpleGrantedAuthority("ROLE_"+role.getKeyword()));
                //根据role ID查询对应的auth Permission
                Set<SysPermission> auths = sysUserDao.findPermission(role.getId());
                for (SysPermission auth : auths) {
                    set.add(new SimpleGrantedAuthority(auth.getKeyword()));
                }
            }

            /*
            List<GrantedAuthority> list = new ArrayList<>();
            for (String s : roleList) {
                list.add(new SimpleGrantedAuthority("ROLE_"+s));
                //list.add(new SimpleGrantedAuthority(s));
            }
            //交给框架比较密码 org.springframework.security.core.userdetails.User;
            return new User(result.getUsername(),result.getPassword(),list);*/
            //该方法需要返回UserDetails的对象 所以创建了UserInfo实现了UserDetails
            UserInfo userInfo = new UserInfo();
            BeanUtils.copyProperties(result, userInfo);
/*
            HashSet<GrantedAuthority> set = new HashSet<>();
            //List<GrantedAuthority> list = new ArrayList<>();
            for (String s : roleList) {
                set.add(new SimpleGrantedAuthority("ROLE_"+s));
            }

 */
            userInfo.setAuthorities(set);
            return userInfo;
        }
    }
}
