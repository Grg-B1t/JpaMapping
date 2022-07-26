package com.example.jpa_practice.Jpa;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.jpa_practice.Entity.City;

@Repository
public interface PaginationAndSortingInterface extends PagingAndSortingRepository<City, Long>{
    List<City> findByCountrycodeContaining(Sort sort, String countrycode);
    List<City> findByDistrictContaining(Sort sort, String district);
}
