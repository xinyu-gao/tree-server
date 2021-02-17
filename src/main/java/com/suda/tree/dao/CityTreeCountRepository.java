package com.suda.tree.dao;

import com.suda.tree.entity.mysql.CityTreeCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityTreeCountRepository extends JpaRepository<CityTreeCount, String > {

    @Override
    <S extends CityTreeCount> List<S> saveAll(Iterable<S> iterable);

    @Query(nativeQuery = true, value ="select a.city , a.longitude, a.latitude, b.count " +
            "from city_coordinate a right join city_tree_count b " +
            "on  SUBSTRING(a.city, 1, 2) = SUBSTRING(b.city, 1, 2)")
    List<Object[]> findCityAndCoordinate();
}
