package com.usian.controller;

import com.github.pagehelper.Page;
import com.usian.pojo.TbItem;
import com.usian.pojo.TbItemCat;
import com.usian.pojo.TbItemParam;
import com.usian.pojo.TbItemParamItem;
import com.usian.service.ItemService;
import com.usian.utils.PageResult;
import com.usian.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("service/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

   /* @RequestMapping("selectItemInfo")
    public TbItem selectItemInfo(long itemId){
        return itemService.getByid(itemId);
    }*/
   @RequestMapping("selectTbItemAllByPage")
    public PageResult selectTbItemAllByPage(@RequestParam Integer page,@RequestParam Integer rows){
       return itemService.selectTbItemAllByPage(page,rows);
   }

   @RequestMapping("selectItemCategoryByParentId")
    public List<TbItemCat> selectItemCategoryByParentId(Long id){
       return itemService.selectItemCategoryByParentId(id);
   }
    @RequestMapping("selectItemParamByItemCatId/{itemCatId}")
    public TbItemParam selectItemParamByItemCatId(@PathVariable Long itemCatId){
        return itemService.selectItemParamByItemCatId(itemCatId);
    }
    @RequestMapping("insertTbItem")
    public Integer insertTbItem(@RequestBody TbItem tbItem,
                                @RequestParam String desc,
                                @RequestParam String itemParams){
        return itemService.insertTbItem(tbItem,desc,itemParams);
    }
    @RequestMapping("preUpdateItem")
    public Map<String,Object> preUpdateItem(@RequestParam Long itemId){
        return itemService.preUpdateItem(itemId);
    }
    @RequestMapping("deleteItemById")
    public void deleteItemById(@RequestParam Long itemId){
        itemService.deleteItemById(itemId);
    }
    @RequestMapping("updateTbItem")
    public Integer updateTbItem(@RequestBody TbItem tbItem,
                                @RequestParam String desc,
                                @RequestParam String itemParams){
        return itemService.updateTbItem(tbItem,desc,itemParams);
    }
    @RequestMapping("selectItemParamAll")
    public PageResult selectItemParamAll(){
        return itemService.selectItemParamAll();
    }
    @RequestMapping("insertItemParam")
    public Integer insertItemParam(@RequestParam Long itemCatId,@RequestParam String paramData){
         return itemService.insertItemParam(itemCatId,paramData);
    }
    @RequestMapping("deleteItemParamById")
    public Integer deleteItemParamById(@RequestParam Long id){
        return itemService.deleteItemParamById(id);
    }
}
