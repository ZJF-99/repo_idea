package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.*;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRole(Role role) {

        List<Role> allRole = roleMapper.findAllRole(role);
        return allRole;
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer roleId) {

        List<Integer> menuByRoleId = roleMapper.findMenuByRoleId(roleId);
        return menuByRoleId;
    }

    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo) {

        //1. 清空中间表的关联关系
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());

        //2. 为角色分配菜单
        for (Integer mid : roleMenuVo.getMenuIdList()) {

            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());

            //封装数据
            Date date = new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);

            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");

            roleMapper.roleContextMenu(role_menu_relation);

        }

    }


    @Override
    public void deleteRole(Integer roleId) {

        //调用根据roleid清空中间表关联关系
        roleMapper.deleteRoleContextMenu(roleId);

        roleMapper.deleteRole(roleId);


    }


    /**
     * 添加& 修改资源分类接口（获取当前角色拥有的 资源信息）
     */
    @Override
    public ResponseResult findResourceListByRoleId(Integer roleId) {

        //1.查询当前角色拥有的资源分类信息
        List<ResourceCategory> category = roleMapper.findResourceCategoryListByRoleId(roleId);

        //2.查询封装资源分类信息关联的资源信息
        for (ResourceCategory resourceCategory : category) {
            List<Resource> resource = roleMapper.findResourceByCategoryId(resourceCategory.getId());
            resourceCategory.setResourceList(resource);
        }


        return new ResponseResult(true,200,"获取当前角色拥有的资源信息成功",category);
    }


    /**
     * 为角色分配资源
     */
    @Override
    public void roleContextResource(RoleResourceVo roleResourceVo) {

        //1. 根据角色ID清空中间表关联关系
        roleMapper.deleteRoleResourceRelation(roleResourceVo.getRoleId());

        //2.再重新建立关联关系
        for (Integer resourceId : roleResourceVo.getResourceIdList()) {

            //封装数据
            RoleResourceRelation roleResourceRelation = new RoleResourceRelation();
            roleResourceRelation.setRoleId(roleResourceVo.getRoleId());
            roleResourceRelation.setResourceId(resourceId);

            Date date = new Date();
            roleResourceRelation.setCreatedTime(date);
            roleResourceRelation.setUpdatedTime(date);

            roleResourceRelation.setCreatedBy("system");
            roleResourceRelation.setUpdatedBy("system");

            roleMapper.roleContextResource(roleResourceRelation);
        }

    }

}
