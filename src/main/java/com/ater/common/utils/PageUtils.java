package com.ater.common.utils;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 *
 * @author
 * @create 2017-04-01
 **/
public class PageUtils implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 总记录数
     */
    private int totalCount;
    /**
     * 每页记录数
     */
    private int pageSize;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 当前页数
     */
    private int currPage;

    private int  notSubmitTotal;

    private int   submitTotal;
    /**
     * 列表数据
     */
    private List<?> list;

    /**
     * 分页
     *
     * @param list       列表数据
     * @param totalCount 总记录数
     * @param pageSize   每页记录数
     * @param currPage   当前页数
     */
    public PageUtils(List<?> list, int totalCount, int pageSize, int currPage) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
    }
    public PageUtils(List<?> list, int totalCount, int notSubmitTotal,int submitTotal,int pageSize, int currPage) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.notSubmitTotal=notSubmitTotal;
        this.submitTotal=submitTotal;
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
    }
    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getNotSubmitTotal() {
        return notSubmitTotal;
    }

    public void setNotSubmitTotal(int notSubmitTotal) {
        this.notSubmitTotal = notSubmitTotal;
    }

    public int getSubmitTotal() {
        return submitTotal;
    }

    public void setSubmitTotal(int submitTotal) {
        this.submitTotal = submitTotal;
    }
}
