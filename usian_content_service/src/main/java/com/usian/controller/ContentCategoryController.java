package com.usian.controller;

import com.usian.pojo.TbContent;
import com.usian.pojo.TbContentCategory;
import com.usian.service.ContentCategoryService;
import com.usian.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("content/service")
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("selectContentCategoryByParentId")
    public List<TbContentCategory> selectContentCategoryByParentId(@RequestParam Long id){
        return contentCategoryService.selectContentCategoryByParentId(id);
    }
    @RequestMapping("insertContentCategory")
    public Integer insertContentCategory(@RequestParam Long parentId,@RequestParam String name){
        return contentCategoryService.insertContentCategory(parentId,name);
    }
    @RequestMapping("deleteContentCategoryById")
    public Integer deleteContentCategoryById(@RequestParam Long categoryId){
        return contentCategoryService.deleteContentCategoryById(categoryId);
    }
    @RequestMapping("updateContentCategory")
    public Integer updateContentCategory(@RequestBody TbContentCategory tbContentCategory){
        return contentCategoryService.updateContentCategory(tbContentCategory);
    }
    @RequestMapping("selectTbContentAllByCategoryId")
    public PageResult selectTbContentAllByCategoryId(@RequestParam Long categoryId){
        return contentCategoryService.selectTbContentAllByCategoryId(categoryId);
    }
    @RequestMapping("insertTbContent")
    public Integer insertTbContent(@RequestBody TbContent tbContent){
        return contentCategoryService.insertTbContent(tbContent);
    }

    @RequestMapping("deleteContentByIds")
    public Integer deleteContentByIds(@RequestParam Long ids){
        return contentCategoryService.deleteContentByIds(ids);
    }
}
