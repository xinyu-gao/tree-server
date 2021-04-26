package com.suda.tree.service;

import com.suda.tree.dto.result.PageResult;
import com.suda.tree.entity.mysql.CityTreeCount;
import com.suda.tree.entity.mysql.TreeGradeStatistic;
import com.suda.tree.entity.mysql.TreeInfo;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface TreeInfoService {

    /**
     * 通过 数目编号 查询树木信息
     */
    TreeInfo getTreeInfoByTreeId(String treeId);

    /**
     * 存储树木信息（insert and update）
     * @param treeInfo 树木信息
     * @param username 上传信息人姓名
     */
    TreeInfo saveTreeInfo(TreeInfo treeInfo, String username);

    /**
     * 计算树木等级分类
     */
    List<TreeGradeStatistic> calculateGradeStatistic();

    PageResult<TreeInfo> getTreeList(int page, int size,int desc);

    PageResult<TreeInfo> getTreeListSorted(int page, int size, String[] keys, int asc);

    List<TreeInfo> getTreeList(String city);

    List<TreeInfo> getTreeListAll();

    List<CityTreeCount> genProvinceAndCityTreeCount();

    List<TreeInfo> getInfosFuzzyQuery(String data);

    PageResult<TreeInfo> getTreeListBySearch(String searchField, String value, int page, int size) throws Exception;

    Boolean setImsiForTree(String treeId, String imsi);

    String findImsiForTree(String treeId);
}
