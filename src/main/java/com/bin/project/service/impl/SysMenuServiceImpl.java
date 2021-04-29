package com.bin.project.service.impl;

import com.bin.project.dao.SysMenuDao;
import com.bin.project.dto.SysMenuParam;
import com.bin.project.service.SysMenuService;
import com.bin.security.component.UserInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuDao sysMenuDao;


    @Override
    public List<SysMenuParam> getUserMenuInfo() {
        /*
            1.最外层封装顶级菜单
            2.第二层封装次级菜单
            3。第三层封装具体
            根据user信息和level直接查询封装
         */
        UserInfo principal = null;
        try {
            principal = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //获取level 为2的
        List<SysMenuParam> list2 = sysMenuDao.getUserMenuInfo(principal.getUsername());
        List<SysMenuParam> list1 = sysMenuDao.getUserMenuInfoOne(principal.getUsername());
        List<SysMenuParam> list0 = sysMenuDao.getUserMenuInfoZero(principal.getUsername());
        //构建返回数据
        //最外层
        List<SysMenuParam> finalList = new ArrayList<>();

        //mid包含了1 包含了2
        for (SysMenuParam m1 : list1) {
            SysMenuParam mid = new SysMenuParam();
            List<SysMenuParam> midList = new ArrayList<>();
            for (SysMenuParam m2 : list2) {
                SysMenuParam sysMenuParam = new SysMenuParam();
                BeanUtils.copyProperties(m2,sysMenuParam);
                midList.add(sysMenuParam);
            }
            BeanUtils.copyProperties(m1,mid);
            mid.setChildren(midList);
            finalList.add(mid);
        }
        //全部封装到
        //SysMenuParam out = new SysMenuParam();
       //out.setChildren(finalList);
        //底层多个角色对应多个  需要加个distinct
        List<SysMenuParam> finalFinalList = new ArrayList<>();
        for (SysMenuParam m0 : list0) {
            SysMenuParam finalMenu = new SysMenuParam();
            BeanUtils.copyProperties(m0,finalMenu);
            finalMenu.setChildren(finalList);
            finalFinalList.add(finalMenu);
        }

        return finalFinalList;

    }

}
