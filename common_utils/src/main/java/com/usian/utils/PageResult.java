package com.usian.utils;

import java.util.List;

public class PageResult {
    private Integer pageIndex; //当前页
    private Long totalPage; //总页数
    private List result; //结果集



    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public List getResult() {
        return result;
    }

    public void setResult(List result) {
        this.result = result;
    }

    public PageResult(Integer pageIndex, Long totalPage, List result) {
        this.pageIndex = pageIndex;
        this.totalPage = totalPage;
        this.result = result;
    }

    public PageResult() {
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "pageIndex=" + pageIndex +
                ", totalPage=" + totalPage +
                ", result=" + result +
                '}';
    }
}
