package com.suda.tree.util;

import com.suda.tree.dto.PageResult;
import org.springframework.data.domain.Page;

import java.util.List;

public class PageUtil {

    /**
     * 重新构建分页结果，示例如下：
     * {
     * "total": 4,
     * "list": [
     * {
     * "id": 2,
     * "name": null,
     * },
     * ],
     * "currentPage": 1
     * }
     *
     * @param page 原来的分页结果
     * @param <T>  泛型类
     * @return 构建后的结果
     */
    public static <T> PageResult<T> setResult(Page<T> page) {
        return new PageResult<T>()
                .setTotal(page.getTotalElements())
                .setList(page.getContent())
                .setHasNext(page.hasNext());
    }

    public static <T> PageResult<T> setResult(long total, List<T> content, boolean hasNext) {
        return new PageResult<T>()
                .setTotal(total)
                .setList(content)
                .setHasNext(hasNext);
    }
}
