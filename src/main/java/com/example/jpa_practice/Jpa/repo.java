package com.example.jpa_practice.Jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpa_practice.Entity.City;
import com.example.jpa_practice.Entity.DistrictData;

@Repository
public interface repo extends JpaRepository<City, Long>{
    @Query("from City where countrycode=:countrycode and district=:district")
    List<City> findCity(@Param("countrycode") String countrycode, @Param("district") String district);

    @Query("from City where countrycode=:countrycode order by id desc")
    List<City> findALLCitiesByCountrycode(@Param("countrycode") String countrycode);

    @Transactional
    @Modifying
    @Query("delete from City where countrycode=:countrycode")
    Integer deleteCityByCountrycode(@Param("countrycode") String countrycode);

    // @Query(value = "select * from city where countrycode =:countrycode", nativeQuery = true)
    // List<City> findDsitrictByCountryCodeJPA(@Param("countrycode") String countrycode);

}
