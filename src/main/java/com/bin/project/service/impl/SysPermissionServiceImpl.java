package com.bin.project.service.impl;

import com.bin.common.PageQueryBean;
import com.bin.common.PageResult;
import com.bin.project.dao.SysPermissionDao;
import com.bin.project.dao.SysRolePermissionDao;
import com.bin.project.dto.SysPermissionParam;
import com.bin.project.pojo.SysPermission;
import com.bin.project.pojo.SysRolePermission;
import com.bin.project.service.SysPermissionService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {


    @Autowired
    private SysPermissionDao sysPermissionDao;

    @Autowired
    private SysRolePermissionDao sysRolePermissionDao;


    @Override
    public List<SysPermission> findPermListInfo() {
        return sysPermissionDao.findPermListInfo();
    }

    @Override
    public List<SysPermissionParam> findPermListTree() {
        //外层数据
        List<SysPermissionParam> outerList = sysPermissionDao.findPermListTreeOuter();

        List<SysPermissionParam> list = new ArrayList<>();

        for (SysPermissionParam perm : outerList) {

            SysPermissionParam sysPermission = new SysPermissionParam();
            BeanUtils.copyProperties(perm, sysPermission);

            List<SysPermissionParam> list2 = new ArrayList<>();
            //内层数据
            List<SysPermissionParam> permListTreeInner = sysPermissionDao.findPermListTreeInner(perm.getId());
            for (SysPermissionParam param : permListTreeInner) {
                SysPermissionParam sysPermissionParam = new SysPermissionParam();
                BeanUtils.copyProperties(param, sysPermissionParam);
                list2.add(sysPermissionParam);
            }
            //存储List到对象
            sysPermission.setChildren(list2);
            list.add(sysPermission);
        }

        return list;
    }

    @Override
    public List<SysPermission> findPermListByRoleId(Long id) {
        return sysPermissionDao.findPermListByRoleId(id);
    }

    @Override
    public void updateRolePerm(Long id, List<Long> permIds) {
        //删除原有的关系
        sysRolePermissionDao.deleteRolePermRelation(id);
        //建立新的对应关系

        if (!CollectionUtils.isEmpty(permIds)) {
            List<SysRolePermission> list = new ArrayList<>();
            for (Long permId : permIds) {
                //将permId插入关系表
                SysRolePermission sysRolePermission = new SysRolePermission();
                sysRolePermission.setRoleId(id);
                sysRolePermission.setPermissionId(permId);
                list.add(sysRolePermission);
            }

            sysRolePermissionDao.insertList(list);
        }

    }

    @Override
    public PageResult findPermList(PageQueryBean pageQueryBean) {
        Integer currentPage = pageQueryBean.getCurrentPage();
        Integer pageSize = pageQueryBean.getPageSize();
        String queryString = pageQueryBean.getQueryString();

        PageHelper.startPage(currentPage, pageSize);
        Page<SysPermission> page = sysPermissionDao.findPermList(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public SysPermission findPermById(Long id) {

        return sysPermissionDao.findPermById(id);
    }

    @Override
    public void addPerm(SysPermission sysPermission) {
        sysPermission.setCreateTime(new Date());
        sysPermissionDao.addPerm(sysPermission);
    }

    @Override
    public void updatePerm(Long id, SysPermission sysPermission) {
        sysPermission.setId(id);
        sysPermissionDao.updatePerm(sysPermission);

    }
}
