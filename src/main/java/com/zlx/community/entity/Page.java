package com.zlx.community.entity;


/**
 * 后端分页组件
 */
public class Page {
    //前端传递过来的参数：当前页码和页面大小
    private int currentPageNum = 1;
    private int limit = 10;
    // 帖子总数(用于计算总页数)
    private int rows;
    //分页查询时的请求路径
    private String path;

    //根据当前页码和页面大小计算当前页起始行号
    public int getOffset(){
        return (currentPageNum - 1) * limit;
    }

    //计算总页数
    public int getTotalPages(){
        if(rows % limit == 0) return rows / limit;
        return rows / limit + 1;
    }

    //获得当前页的前2页，即前端页码展示的开始页码
    public int getFromPage(){
        return Math.max(currentPageNum - 2, 1);
    }

    //获得当前页的后2页，即前端页码展示的结束页码
    public int getToPage(){
        return Math.min(currentPageNum + 2, getTotalPages());
    }

    public int getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(int currentPageNum) {
        if (currentPageNum > 0) this.currentPageNum = currentPageNum;//安全控制，保证页码大于0
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (limit > 0) this.limit = limit;//安全控制，保证页面大小大于0
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
