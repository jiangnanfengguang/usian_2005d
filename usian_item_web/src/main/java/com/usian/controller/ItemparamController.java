package com.usian.controller;

import com.usian.feign.ItemServiceFeign;
import com.usian.pojo.TbItemParam;
import com.usian.pojo.TbItemParamItem;
import com.usian.utils.PageResult;
import com.usian.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("backend/itemParam")
public class ItemparamController {
    @Autowired
    private ItemServiceFeign itemServiceFeign;
    @RequestMapping("selectItemParamByItemCatId/{itemCatId}")
    public Result selectItemParamByItemCatId(@PathVariable Long itemCatId){
        TbItemParam tbItemParam=itemServiceFeign.selectItemParamByItemCatId(itemCatId);
        if(tbItemParam!=null){
            return Result.ok(tbItemParam);
        }else {
            return Result.error("没有 您失败了");
        }
    }

    @RequestMapping("selectItemParamAll")
    public Result selectItemParamAll(){
        PageResult pageResult =itemServiceFeign.selectItemParamAll();
        if(pageResult!=null && pageResult.getResult().size()>0){
            return Result.ok(pageResult);
        }

        return Result.error("模板查询失败");
    }

    @RequestMapping("insertItemParam")
    public Result insertItemParam(@RequestParam Long itemCatId,@RequestParam String paramData){
        Integer num=itemServiceFeign.insertItemParam(itemCatId,paramData);
        if(num==0){
            return Result.error("添加失败，或已有");
        }else {
            return Result.ok();
        }
    }


    @RequestMapping("deleteItemParamById")
    public Result deleteItemParamById(@RequestParam Long id){
        Integer num=itemServiceFeign.deleteItemParamById(id);
        if(num==0){
            return Result.error("不能删除");
        }else {
            return Result.ok();
        }
    }
}
