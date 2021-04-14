package com.usian.controller;

import com.usian.feign.ContentServiceFeign;
import com.usian.pojo.TbContent;
import com.usian.pojo.TbContentCategory;
import com.usian.utils.PageResult;
import com.usian.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("content")
public class ContentCategoryController {
    @Autowired
    private ContentServiceFeign contentServiceFeign;

    @RequestMapping("selectContentCategoryByParentId")
    public Result selectContentCategoryByParentId(@RequestParam(defaultValue = "0") Long id){
        List<TbContentCategory> list=contentServiceFeign.selectContentCategoryByParentId(id);
        if(list!=null && list.size()>0){
            return Result.ok(list);
        }
        return Result.error("查询分类失败");
    }
    @RequestMapping("insertContentCategory")
    public Result insertContentCategory(@RequestParam Long parentId,@RequestParam String name){
        Integer num=contentServiceFeign.insertContentCategory(parentId,name);
        if(num!=0){
            return Result.ok();
        }

        return Result.error("添加分类失败");
    }

    @RequestMapping("deleteContentCategoryById")
    public Result deleteContentCategoryById(@RequestParam Long categoryId){
        Integer num=contentServiceFeign.deleteContentCategoryById(categoryId);
        if(num!=0){
            return Result.ok();
        }

        return Result.error("删除分类失败");
    }
    @RequestMapping("updateContentCategory")
    public Result updateContentCategory(TbContentCategory tbContentCategory){
        Integer num=contentServiceFeign.updateContentCategory(tbContentCategory);
        if(num!=0){
            return Result.ok();
        }

        return Result.error("修改分类失败");
    }


    @RequestMapping("selectTbContentAllByCategoryId")
    public Result selectTbContentAllByCategoryId(@RequestParam(defaultValue = "0") Long categoryId){
        PageResult pageResult =contentServiceFeign.selectTbContentAllByCategoryId(categoryId);
        if(pageResult!=null && pageResult.getResult().size()>0){
            return Result.ok(pageResult);
        }
        return Result.error("查询管理失败");
    }

    @RequestMapping("insertTbContent")
    public Result insertTbContent(TbContent TbContent){
        Integer num=contentServiceFeign.insertTbContent(TbContent);
        if(num!=0){
            return Result.ok();
        }

        return Result.error("添加管理失败");
    }

    @RequestMapping("deleteContentByIds")
    public Result deleteContentByIds(@RequestParam Long ids){
        Integer num=contentServiceFeign.deleteContentByIds(ids);
        if(num!=0){
            return Result.ok();
        }

        return Result.error("删除分类失败");
    }

}
