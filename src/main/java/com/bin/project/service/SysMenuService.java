package com.bin.project.service;

import com.bin.project.dto.SysMenuParam;

import java.util.List;

public interface SysMenuService {
    /**
     * 用于封装菜单
     * @return
     */
    List<SysMenuParam> getUserMenuInfo();
}
