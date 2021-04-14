package com.usian.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.usian.mapper.*;
import com.usian.pojo.*;
import com.usian.utils.IDUtils;
import com.usian.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemService {
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemCatMapper tbItemCatMapper;
    @Autowired
    private TbItemParamMapper tbItemParamMapper;
    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;


   /* public TbItem getByid(long itemId) {
        return tbItemMapper.selectByPrimaryKey(itemId);
    }*/

    public PageResult selectTbItemAllByPage(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        TbItemExample tbItemExample=new TbItemExample();
        tbItemExample.setOrderByClause("updated DESC");
        TbItemExample.Criteria criteria = tbItemExample.createCriteria();
        criteria.andStatusEqualTo((byte)1);
        List<TbItem> tbItemList = tbItemMapper.selectByExample(tbItemExample);
       /* for (int i = 0; i < tbItemList.size(); i++) {
            TbItem tbItem = tbItemList.get(i);
            tbItem.setPrice(tbItem.getPrice()/100);
        }*/

        PageInfo<TbItem> pageInfo = new PageInfo<>(tbItemList);
        return new PageResult(page,pageInfo.getTotal(),pageInfo.getList());


    }

    public List<TbItemCat> selectItemCategoryByParentId(Long id) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(id);
        criteria.andStatusEqualTo(1);
        List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
        return list;
    }

    public TbItemParam selectItemParamByItemCatId(Long itemCatId) {
        TbItemParamExample example = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(itemCatId);
        List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
        if(list!=null&&list.size()>0){
            TbItemParam tbItemParam = list.get(0);
            return tbItemParam;
        }
        return null;
    }

    public Integer insertTbItem(TbItem tbItem, String desc, String itemParams) {
        long id = IDUtils.genItemId();
        Date date = new Date();
        tbItem.setId(id);
        tbItem.setStatus((byte)1);
        tbItem.setUpdated(date);
        tbItem.setCreated(date);
      //  tbItem.setPrice(tbItem.getPrice()*100);
        Integer tbItemNum=tbItemMapper.insertSelective(tbItem);

        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);
        tbItemDesc.setItemId(id);
        tbItemDesc.setItemDesc(desc);
        Integer tbItemDescNum=tbItemDescMapper.insertSelective(tbItemDesc);

        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setItemId(id);
        tbItemParamItem.setParamData(itemParams);
        tbItemParamItem.setUpdated(date);
        tbItemParamItem.setCreated(date);
        Integer tbItemParamItemNum=tbItemParamItemMapper.insertSelective(tbItemParamItem);

        return tbItemDescNum+tbItemParamItemNum+tbItemNum;
    }

    public Map<String, Object> preUpdateItem(Long itemId) {
        Map<String, Object> map=new HashMap<>();
        TbItem tbItem = tbItemMapper.selectByPrimaryKey(itemId);
        map.put("item",tbItem);
        TbItemCat tbItemCat = tbItemCatMapper.selectByPrimaryKey(tbItem.getCid());
        map.put("itemCat",tbItemCat.getName());
        TbItemDesc tbItemDesc = tbItemDescMapper.selectByPrimaryKey(itemId);
        map.put("itemDesc",tbItemDesc.getItemDesc());

        TbItemParamItemExample tbItemParamItemExample = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = tbItemParamItemExample.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(tbItemParamItemExample);
        if(list!=null &&list.size()>0){
            map.put("itemParamItem",list.get(0));
        }

        return map;
    }

    public void deleteItemById(Long itemId) {
        tbItemMapper.deleteByPrimaryKey(itemId);
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        tbItemParamItemMapper.deleteByExample(example);
        tbItemDescMapper.deleteByPrimaryKey(itemId);


    }

    public Integer updateTbItem(TbItem tbItem, String desc, String itemParams) {

        tbItem.setStatus((byte)1);
        Date date = new Date();
        tbItem.setUpdated(date);
       // tbItem.setCreated(date);
      //  tbItem.setPrice(tbItem.getPrice()*100);
        tbItemMapper.updateByPrimaryKeySelective(tbItem);

        TbItemDesc tbItemDesc = new TbItemDesc();
        //tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);
        tbItemDesc.setItemId(tbItem.getId());
        tbItemDesc.setItemDesc(desc);
        tbItemDescMapper.updateByPrimaryKeySelective(tbItemDesc);


        /////
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(tbItem.getId());
        List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
        TbItemParamItem tbItemParamItem1 = list.get(0);

        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setId(tbItemParamItem1.getId());
        tbItemParamItem.setItemId(tbItem.getId());
        tbItemParamItem.setParamData(itemParams);
        tbItemParamItem.setUpdated(date);
       // tbItemParamItem.setCreated(date);
        tbItemParamItemMapper.updateByPrimaryKeyWithBLOBs(tbItemParamItem);



        return 0;
    }

    public PageResult selectItemParamAll() {
        PageHelper.startPage(1,20);
        List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(new TbItemParamExample());
        PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
        return new PageResult(pageInfo.getPageNum(),pageInfo.getTotal(),pageInfo.getList());
    }

    public Integer insertItemParam(Long itemCatId, String paramData) {
        List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(new TbItemParamExample());
        for (int i = 0; i < list.size(); i++) {
            TbItemParam tbItemParam1 = list.get(i);
            if(tbItemParam1.getItemCatId() == itemCatId){
                return 0;
            }
        }

        Date date = new Date();
        TbItemParam tbItemParam = new TbItemParam();
        tbItemParam.setParamData(paramData);
        tbItemParam.setItemCatId(itemCatId);
        tbItemParam.setCreated(date);
        tbItemParam.setUpdated(date);
        tbItemParamMapper.insertSelective(tbItemParam);
        return 2;
    }

    public Integer deleteItemParamById(Long id) {
        //得出要删除的表中item_cat_id
        TbItemParam tbItemParam = tbItemParamMapper.selectByPrimaryKey(id);
        List<TbItem> tbItemList = tbItemMapper.selectByExample(new TbItemExample());
        for (int i = 0; i < tbItemList.size(); i++) {
            TbItem tbItem = tbItemList.get(i);
            //如果item_cat_id与列表的cid有关联 那不能删除
            if(tbItem.getCid()==tbItemParam.getItemCatId()){
                return 0;
            }
        }
        //如果不，那就删
        int i = tbItemParamMapper.deleteByPrimaryKey(id);
        return i;
    }
}
