package com.suda.tree.dao;

import com.suda.tree.entity.mysql.TreeInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TreeInfoRepository extends JpaRepository<TreeInfo, String> {

    TreeInfo save(TreeInfo tree);

    Optional<TreeInfo> findByTreeId(String treeId);

    @Query(nativeQuery = true, value = "select location_province as province, grade, count(*) as count from tree_info group by location_province, grade order by location_province")
    List<Object[]> getTreeGradeStatistic();

    Page<TreeInfo> findAll(Pageable pageable);

    @Override
    List<TreeInfo> findAll();

    @Query(nativeQuery = true,
            value = "select location_province, location_city " +
                    "from tree_info " +
                    "group by location_province, location_city " +
                    "ORDER BY location_province, location_city")
    List<Object[]> getExistedProvinceAndCityStatistic();


    List<TreeInfo> findTreeInfoByLocationProvinceContainingOrLocationCityContaining(String province, String city);

    List<TreeInfo> findTreeInfoByTreeIdContainingOrChineseNameContainingOrAliasContainingOrLatinNameContaining
            (String treeId, String chineseName, String alias, String latinName);

    @Query(nativeQuery = true,
            value = "SELECT location_province, location_city, count(tree_id) " +
                    "FROM `tree_info` " +
                    "group by location_province, location_city")
    List<Object[]> getProvinceAndCityTreeCount();

    Page<TreeInfo> findTreeInfoByTreeIdContaining(String treeId, Pageable pageable);

    Page<TreeInfo> findTreeInfoBySurveyNumberContaining(String surveyNumber, Pageable pageable);

    Page<TreeInfo> findTreeInfoByChineseNameContaining(String chineseName, Pageable pageable);

    Page<TreeInfo> findTreeInfoByAliasContaining(String alias, Pageable pageable);

    Page<TreeInfo> findTreeInfoByLatinNameContaining(String latinName, Pageable pageable);

    Page<TreeInfo> findTreeInfoByFamilyContainingOrGenusContainingOrSpeciesContaining
            (String family, String genus, String species, Pageable pageable);

    Page<TreeInfo> findTreeInfoByLocationProvinceContainingOrLocationCityContainingOrLocationDistrictContainingOrLocationDetailContaining
            (String locationProvince, String locationCity, String locationDistrict, String locationDetail, Pageable pageable);
}
