package com.usian.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.usian.mapper.TbContentCategoryMapper;
import com.usian.mapper.TbContentMapper;
import com.usian.pojo.TbContent;
import com.usian.pojo.TbContentCategory;
import com.usian.pojo.TbContentCategoryExample;
import com.usian.pojo.TbContentExample;
import com.usian.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;
    @Autowired
    private TbContentMapper tbContentMapper;

    public List<TbContentCategory> selectContentCategoryByParentId(Long id) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(id);
        criteria.andStatusEqualTo(1);
        List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
        return list;
    }

    public Integer insertContentCategory(Long parentId, String name) {
        TbContentCategory category = new TbContentCategory();
        Date date = new Date();
        category.setName(name);
        category.setParentId(parentId);
        category.setStatus(1);
        category.setUpdated(date);
        category.setCreated(date);
        category.setIsParent(false);
        category.setSortOrder(1);
        int insert = tbContentCategoryMapper.insert(category);

        TbContentCategory tbContentCategory = tbContentCategoryMapper.selectByPrimaryKey(category.getParentId());
        if(!tbContentCategory.getIsParent()){
            tbContentCategory.setIsParent(true);
            tbContentCategory.setUpdated(date);
            int i = tbContentCategoryMapper.updateByPrimaryKey(tbContentCategory);
        }
        return insert;

    }

    public Integer deleteContentCategoryById(Long categoryId) {

        //获取要删除的类目
        TbContentCategory tbContentCategory = tbContentCategoryMapper.selectByPrimaryKey(categoryId);
        //判断是否为父类目 是， 就不删
        if(tbContentCategory.getIsParent()) {
            return 0;
        }
        //删除
        int i = tbContentCategoryMapper.deleteByPrimaryKey(categoryId);
        //查询有没兄弟姐妹 没就改父类为子类
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(tbContentCategory.getParentId());
        List<TbContentCategory> boyGirl = tbContentCategoryMapper.selectByExample(example);
        if(boyGirl==null || boyGirl.size()<=0){
            //父类
            TbContentCategory tb = tbContentCategoryMapper.selectByPrimaryKey(tbContentCategory.getParentId());
            tb.setUpdated(new Date());
            tb.setIsParent(false);
            tbContentCategoryMapper.updateByPrimaryKeySelective(tb);
        }
        return i;
    }

    public Integer updateContentCategory(TbContentCategory tbContentCategory) {
        tbContentCategory.setUpdated(new Date());
        tbContentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory);

        return 11;
    }

    public PageResult selectTbContentAllByCategoryId(Long categoryId) {
        PageHelper.startPage(1,20);
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
        PageInfo<TbContent> pageInfo = new PageInfo<>(list);
        return new PageResult(pageInfo.getPageNum(),pageInfo.getTotal(),pageInfo.getList());

    }

    public Integer insertTbContent(TbContent tbContent) {
        Date date = new Date();
        tbContent.setCreated(date);
        tbContent.setUpdated(date);
        tbContent.setPic2(tbContent.getPic());
        tbContent.setTitleDesc(tbContent.getSubTitle());
        tbContentMapper.insert(tbContent);

        return 22;
    }

    public Integer deleteContentByIds(Long ids) {

        tbContentMapper.deleteByPrimaryKey(ids);
        return 2;
    }
}
