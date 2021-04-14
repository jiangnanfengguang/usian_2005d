package com.usian.feign;

import com.usian.pojo.TbContent;
import com.usian.pojo.TbContentCategory;
import com.usian.utils.PageResult;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("usian-content-service")
public interface ContentServiceFeign {


    @RequestMapping("content/service/selectContentCategoryByParentId")
    List<TbContentCategory> selectContentCategoryByParentId(@RequestParam Long id);
    @RequestMapping("content/service/insertContentCategory")
    Integer insertContentCategory(@RequestParam Long parentId,@RequestParam String name);
    @RequestMapping("content/service/deleteContentCategoryById")
    Integer deleteContentCategoryById(@RequestParam Long categoryId);
    @RequestMapping("content/service/updateContentCategory")
    Integer updateContentCategory(@RequestBody TbContentCategory tbContentCategory);
    @RequestMapping("content/service/selectTbContentAllByCategoryId")
    PageResult selectTbContentAllByCategoryId(@RequestParam Long categoryId);
    @PostMapping("content/service/insertTbContent")
    Integer insertTbContent(@RequestBody TbContent tbContent);
    @RequestMapping("content/service/deleteContentByIds")
    Integer deleteContentByIds(@RequestParam Long ids);
}
