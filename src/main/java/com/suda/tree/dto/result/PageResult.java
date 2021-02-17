package com.suda.tree.dto.result;

import lombok.Data;

import java.util.List;

/*
 * mysql 分页请求处理
 */
@Data
public class PageResult<T> {

    /**
     * 总共有多少数据
     */
    private Long total;

    /**
     * 当前查询的结果列表
     */
    private List<T> list;

    /**
     * 是否还有下一页数据
     */
    private boolean hasNext;


    public PageResult<T> setTotal(Long total) {
        this.total = total;
        return this;
    }

    public PageResult<T> setList(List<T> list) {
        this.list = list;
        return this;
    }

    public PageResult<T> setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
        return this;
    }
}
