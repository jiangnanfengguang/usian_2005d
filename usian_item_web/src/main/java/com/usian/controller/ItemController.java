package com.usian.controller;

import com.usian.feign.ItemServiceFeign;
import com.usian.pojo.TbItem;
import com.usian.utils.PageResult;
import com.usian.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("backend/item")
public class ItemController {
    @Autowired
    private ItemServiceFeign itemServiceFeign;

   /* @RequestMapping("selectItemInfo")
    public Result selectItemInfo(long itemId){
        TbItem tbItem=itemServiceFeign.selectItemInfo(itemId);
        if(tbItem==null){
            return Result.error("兄弟，你请求有问题，检查一下肾。。。");
        }else {
            return Result.ok(tbItem);
        }
        }*/
   @RequestMapping("selectTbItemAllByPage")
    public Result selectTbItemAllByPage(@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "2")Integer rows){
       PageResult pageResult= itemServiceFeign.selectTbItemAllByPage(page,rows);
       if(pageResult.getResult()!=null && pageResult.getResult().size()>0){
           return Result.ok(pageResult);
       }else {
           return Result.error("查无结果");
       }
   }
   @RequestMapping("insertTbItem")
   public Result insertTbItem(TbItem tbItem,String desc,String itemParams) {
       Integer insertTbItem = itemServiceFeign.insertTbItem(tbItem, desc, itemParams);
       if (insertTbItem == 3) {
           return Result.ok();
       } else {
           return Result.error("添加失败");
       }

   }
    @RequestMapping("deleteItemById")
    public Result deleteItemById(Long itemId){
        itemServiceFeign.deleteItemById(itemId);
        return Result.ok();
       /* if(){
            return Result.ok(map);
        }else {
            return Result.error("查无结果");
        }*/
    }
    @RequestMapping("preUpdateItem")
    public Result preUpdateItem(@RequestParam Long itemId){
        Map<String,Object> map=itemServiceFeign.preUpdateItem(itemId);
        if(map.size()>0){
            return Result.ok(map);
        }else {
            return Result.error("查无结果");
        }
    }
    @RequestMapping("updateTbItem")
    public Result updateTbItem(TbItem tbItem,String desc,String itemParams) {
        Integer insertTbItem = itemServiceFeign.updateTbItem(tbItem, desc, itemParams);
        if (3 == 3) {
            return Result.ok();
        } else {
            return Result.error("修改失败");
        }

    }

}
