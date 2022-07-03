package com.lagou.dao;

import com.lagou.domain.ResourceCategory;

import java.util.List;

public interface ResourceCategoryMapper {

    /**
     * 查询所有资源分类
     */
    public List<ResourceCategory> findAllResourceCategory();

    /**
     * 添加资源分类
     */
    public void saveResourceCategory(ResourceCategory resourceCategory);

    /**
     * 修改资源分类
     */
    public void updateResourceCategory(ResourceCategory resourceCategory);

    /**
     * 删除资源分类
     */
    public void deleteResourceCategory(Integer id);

}
