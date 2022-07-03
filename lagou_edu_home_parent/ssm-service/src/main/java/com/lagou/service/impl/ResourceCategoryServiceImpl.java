package com.lagou.service.impl;

import com.lagou.dao.ResourceCategoryMapper;
import com.lagou.domain.ResourceCategory;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResourceCategoryServiceImpl implements ResourceCategoryService {

    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;

    @Override
    public List<ResourceCategory> findAllResourceCategory() {

        List<ResourceCategory> allResourceCategory = resourceCategoryMapper.findAllResourceCategory();

        return allResourceCategory;
    }


    /**
     * 添加资源分类
     */
    @Override
    public void saveResourceCategory(ResourceCategory resourceCategory) {

        //1.补全信息
        Date date = new Date();
        resourceCategory.setCreatedTime(date);
        resourceCategory.setUpdatedTime(date);
        resourceCategory.setCreatedBy("system");
        resourceCategory.setUpdatedBy("system");

        //2.调用方法
        resourceCategoryMapper.saveResourceCategory(resourceCategory);

    }

    /**
     * 修改资源分类
     */
    @Override
    public void updateResourceCategory(ResourceCategory resourceCategory) {

        //1.补全信息
        resourceCategory.setUpdatedTime(new Date());
        resourceCategory.setUpdatedBy("system");
        //2.调用方法
        resourceCategoryMapper.updateResourceCategory(resourceCategory);

    }

    /**
     * 删除资源分类
     * @param id
     */
    @Override
    public void deleteResourceCategory(Integer id) {

        //调用方法
        resourceCategoryMapper.deleteResourceCategory(id);

    }

}
