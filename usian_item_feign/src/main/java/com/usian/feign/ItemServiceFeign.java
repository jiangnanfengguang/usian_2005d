package com.usian.feign;

import com.usian.pojo.TbItem;
import com.usian.pojo.TbItemCat;
import com.usian.pojo.TbItemParam;
import com.usian.pojo.TbItemParamItem;
import com.usian.utils.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient("usian-item-service")
public interface ItemServiceFeign {

    /*@RequestMapping("service/item/selectItemInfo")
    TbItem selectItemInfo(@RequestParam long itemId);*/

    @RequestMapping("service/item/selectTbItemAllByPage")
    PageResult selectTbItemAllByPage(@RequestParam Integer page,@RequestParam Integer rows);

    @RequestMapping("service/item/selectItemCategoryByParentId")
    List<TbItemCat> selectItemCategoryByParentId(@RequestParam Long id);
    @RequestMapping("service/item/selectItemParamByItemCatId/{itemCatId}")
    TbItemParam selectItemParamByItemCatId(@PathVariable Long itemCatId);
    @RequestMapping("service/item/insertTbItem")
    Integer insertTbItem(@RequestBody TbItem tbItem,
                         @RequestParam String desc,
                         @RequestParam String itemParams);
    @RequestMapping("service/item/preUpdateItem")
    Map<String, Object> preUpdateItem(@RequestParam Long itemId);
    @RequestMapping("service/item/deleteItemById")
    void deleteItemById(@RequestParam Long itemId);
    @RequestMapping("service/item/updateTbItem")
    Integer updateTbItem(@RequestBody TbItem tbItem,
                         @RequestParam String desc,
                         @RequestParam String itemParams);
    @RequestMapping("service/item/selectItemParamAll")
    PageResult selectItemParamAll();
    @RequestMapping("service/item/insertItemParam")
    Integer insertItemParam(@RequestParam Long itemCatId,@RequestParam String paramData);
    @RequestMapping("service/item/deleteItemParamById")
    Integer deleteItemParamById(@RequestParam Long id);
}
