package com.lagou.service;

import com.lagou.domain.*;

import java.util.List;

public interface RoleService {

    /**
     * 查询所有角色（条件）
     */
    public List<Role> findAllRole(Role role);

    /**
     * 根据角色ID查询角色关联的菜单信息ID【1，2，3，5】
     */
    public List<Integer> findMenuByRoleId(Integer roleId);

    /**
     * 为角色分配菜单
     */
    public void roleContextMenu(RoleMenuVo roleMenuVo);

    /**
     * 删除角色
     */
    public void deleteRole(Integer roleId);

    /**
     * 添加& 修改资源分类接口（获取当前角色拥有的 资源信息）
     */
    public ResponseResult findResourceListByRoleId(Integer roleId);

    /**
     * 为角色分配资源
     */
    public void roleContextResource(RoleResourceVo roleResourceVo);


}
