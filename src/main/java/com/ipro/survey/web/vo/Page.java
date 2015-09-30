package com.ipro.survey.web.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {
    public static final int DEFAULT_PAGE_SIZE = 20;
    private final int pageNo;
    private final int pageSize;
    private final int pageCount;
    private final int totalCount;
    private List<T> list;
    public Page() {
        this(1, 0, 0);
    }

    /**
     * 构造分页对象
     * @param pageNo 当前页码
     * @param totalCount 总记录数
     * @param pageSize 每页记录数
     */
    public Page(int pageNo, int totalCount, int pageSize) {
        this.pageNo = (pageNo <= 0 ? 1 : pageNo);
        this.pageSize = (pageSize <= 0 ? DEFAULT_PAGE_SIZE : pageSize);
        this.totalCount = (totalCount <= 0 ? 0 : totalCount);
        this.pageCount = (int)Math.ceil(new Double(this.totalCount).doubleValue() / new Double(this.pageSize).doubleValue());
        this.list = new ArrayList<T>(this.pageSize);
    }


    public int getTotalCount() {
        return totalCount;
    }

    public int getPageNo() {
        return this.pageNo;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getPageCount() {
        return this.pageCount;
    }

    public List<T> getList() {
        return this.list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
