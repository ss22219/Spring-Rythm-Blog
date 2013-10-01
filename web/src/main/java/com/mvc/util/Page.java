package com.mvc.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.lang.Math;

public class Page<T> implements Iterable<T> {
    public Page(List<T> list, int pageIndex, int pageSize, int totalCount, int labelCount) {
        this.setPageLabelCount(labelCount);
        this.setList(list);
        this.setPageIndex(pageIndex);
        this.setPageSize(pageSize);
        this.setTotalCount(totalCount);
    }

    private int pageSize = 1;
    private int pageIndex = 1;
    private int totalCount = 1;
    private List<T> list = null;
    private int pageLabelCount = 4;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPageLabelCount() {
        return pageLabelCount;
    }

    public void setPageLabelCount(int pageLabelCount) {
        this.pageLabelCount = pageLabelCount;
    }

    public int getTotalPage() {
        return (int) Math.ceil(getTotalCount() / (double) getPageSize()) <= 0 ? 1 : (int) Math.ceil(getTotalCount() / (double) getPageSize());
    }

    public Integer[] getPageLabels() {
        List<Integer> list = new ArrayList<Integer>();

        setPageIndex(getPageIndex() > getTotalPage() ? getTotalPage() : getPageIndex());

        if (pageLabelCount == 1) {
            return new Integer[]{getPageIndex()};
        }

        int startPage = 0;
        int endPage = 0;

        if (getPageLabelCount() == 2) {
            startPage = getPageIndex() == getTotalPage() ? getPageIndex() - 1 : getPageIndex();
            startPage = startPage < 1 ? 1 : startPage;

            endPage = startPage + 1;
            endPage = endPage > getTotalPage() ? getTotalPage() : endPage;
        } else if (getPageLabelCount() % 2 == 0) {
            int labelPage = (int) Math.floor((this.getPageIndex() - 2) / (double) (this.getPageLabelCount() - 2)) + 1;

            startPage = (labelPage - 1) * (this.getPageLabelCount() - 2) + 1;

            startPage = startPage < 1 ? 1 : startPage;
            startPage = startPage > getTotalPage() ? getTotalPage() : startPage;

            endPage = startPage + getPageLabelCount() - 1;
            endPage = endPage > getTotalPage() ? getTotalPage() : endPage;
            endPage = endPage < 1 ? 1 : endPage;
        } else {
            startPage = getPageIndex() - ((getPageLabelCount() - 1) / 2);
            startPage = startPage < 1 ? 1 : startPage;
            startPage = startPage > getTotalPage() ? getTotalPage() : startPage;

            endPage = startPage + getPageLabelCount() - 1;
            endPage = endPage > getTotalPage() ? getTotalPage() : endPage;
            endPage = endPage < 1 ? 1 : endPage;
        }

        for (int i = startPage; i <= endPage; i++) {
            list.add(i);
        }

        Integer[] intList = new Integer[this.list.size()];
        return list.toArray(intList);
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}