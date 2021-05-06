package com.bin.project.service.impl;

import com.bin.common.PageQueryBean;
import com.bin.common.PageResult;
import com.bin.project.dao.SysMenuDao;
import com.bin.project.dao.SysRoleMenuDao;
import com.bin.project.dto.SysMenuParam;
import com.bin.project.pojo.SysMenu;
import com.bin.project.pojo.SysRoleMenu;
import com.bin.project.service.SysMenuService;
import com.bin.security.component.UserInfo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuDao sysMenuDao;

    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;


    @Override
    public List<SysMenuParam> getUserMenuInfo() {
        /*
            1.根据用户名 获取

         */
        UserInfo principal = null;
        try {
            principal = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //查询当前用户的0层数据
        List<SysMenuParam> list0 = sysMenuDao.getUserMenuInfoZero(principal.getUsername());
        //构建最终返回集合
        List<SysMenuParam> list = new ArrayList<>();
        //遍历最外层菜单
        for (SysMenuParam menuParam : list0) {
            SysMenuParam sysMenuParam = new SysMenuParam();
            BeanUtils.copyProperties(menuParam,sysMenuParam);
            //存储最外层children
            List<SysMenuParam> listOne = new ArrayList<>();
            for (SysMenuParam param : sysMenuDao.findMenuLevelByIdAndUsername(menuParam.getId(),principal.getUsername())) {
                SysMenuParam sysMenu = new SysMenuParam();
                BeanUtils.copyProperties(param,sysMenu);
                listOne.add(sysMenu);
            }

            sysMenuParam.setChildren(listOne);

            list.add(sysMenuParam);
        }

        return list;

/*

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
*/

    }

    @Override
    public List<SysMenuParam> findMenuListTree() {

        List<SysMenuParam> list = new ArrayList<>();

        //查询0层数据
        List<SysMenuParam> menuListZero = sysMenuDao.findMenuLevelZero();

        //遍历封装0层数据
        for (SysMenuParam menu : menuListZero) {
            //最外层list数据
            SysMenuParam sysMenuParam = new SysMenuParam();
            BeanUtils.copyProperties(menu,sysMenuParam);
            //用于存储最外层对象 children属性
            List<SysMenuParam> listOne = new ArrayList<>();
            //构建1层数据
            for (SysMenuParam param : sysMenuDao.findMenuLevelOne(menu.getId())) {
                SysMenuParam menuParam = new SysMenuParam();
                BeanUtils.copyProperties(param,menuParam);
                listOne.add(menuParam);
            }
            //为每个外层对象配置其对应的子菜单
            sysMenuParam.setChildren(listOne);
            //所有数据最终存放在总的list返回
            list.add(sysMenuParam);
        }

        return list;
    }

    @Override
    public List<SysMenu> findMenuListByRoleId(Long id) {
        return sysMenuDao.findMenuListByRoleId(id);
    }

    @Override
    public void updateRoleMenu(Long id, List<Long> menuIds) {
        //删除原有对应关系
        sysRoleMenuDao.deleteRoleMenuRelation(id);

        //重新构建对应关系
        if (!CollectionUtils.isEmpty(menuIds)) {
            List<SysRoleMenu> list = new ArrayList<>();
            for (Long menuId : menuIds) {
                //将permId插入关系表

                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setRoleId(id);
                sysRoleMenu.setMenuId(menuId);
                list.add(sysRoleMenu);
            }

            sysRoleMenuDao.insertList(list);
        }


    }

    @Override
    public PageResult findMenuList(PageQueryBean pageQueryBean) {
        Integer currentPage = pageQueryBean.getCurrentPage();
        Integer pageSize = pageQueryBean.getPageSize();
        String queryString = pageQueryBean.getQueryString();

        PageHelper.startPage(currentPage,pageSize);
        Page<SysMenu> page = sysMenuDao.findMenuList(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public SysMenu findMenuById(Long id) {


        return sysMenuDao.findMenuById(id);
    }

    @Override
    public void updateMenu(Long id, SysMenu sysMenu) {

        sysMenu.setId(id);
        sysMenuDao.updateMenu(sysMenu);

    }

    @Override
    public void addMenu(SysMenu sysMenu) {
        sysMenuDao.addMenu(sysMenu);
    }

}
