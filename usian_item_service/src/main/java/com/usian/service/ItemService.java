package com.usian.service;

import com.usian.mapper.TbItemMapper;
import com.usian.pojo.TbItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    private TbItemMapper tbItemMapper;


    public TbItem getByid(long itemId) {
        return tbItemMapper.selectByPrimaryKey(itemId);
    }
}
