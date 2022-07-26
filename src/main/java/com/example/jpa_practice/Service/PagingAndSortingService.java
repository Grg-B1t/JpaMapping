package com.example.jpa_practice.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;

import com.example.jpa_practice.Entity.City;
import com.example.jpa_practice.Jpa.PaginationAndSortingInterface;
import com.example.jpa_practice.Jpa.repo;

@Service
public class PagingAndSortingService implements pagingAndSortingServiceInterface {

    PaginationAndSortingInterface pgs;
    repo rp;
    
    @Autowired
    public PagingAndSortingService(PaginationAndSortingInterface pgs, repo rp) {
        this.pgs = pgs;
        this.rp = rp;
    }


    @Override
    public List<City> pagingCities(Integer offset, Integer size) {
        Pageable pageable = PageRequest.of(offset, size);
        List<City> cities = pgs.findAll(pageable).getContent();

        return cities;
    }


    @Override
    public List<City> sortCitiesByCountrycode(String countrycode) {
        Sort sort = Sort.by("countrycode");

        // List<City> cityList = new ArrayList<>();
        // Iterable<City> cities = pgs.findAll(sort);

        // while(cities.iterator().hasNext()){
        //     cityList.add(cities.iterator().next());
        // }

        return pgs.findByCountrycodeContaining(sort, countrycode);
    }


    @Override
    public List<City> sortCitiesByField(String field, Integer start, Integer end) {
        List<City> cityList = rp.findAll();
        List<City> indexedCityList = new ArrayList<>();
        

        for (int i = start -1 ; i < end; i++) {
            if(i >= cityList.size()){
                break;
            }
            indexedCityList.add(cityList.get(i));

        }
        if(field.equals("id")){
            return indexedCityList.stream().sorted((a, b) -> b.getId().compareTo(a.getId())).collect(Collectors.toList());
        }
        else if(field.equals("name")){
            return indexedCityList.stream().sorted((a, b) -> a.getName().compareTo(b.getName())).collect(Collectors.toList());
        }
        
        else if(field.equals("countrycode")){
            return indexedCityList.stream().sorted((a, b) -> a.getCountrycode().compareTo(b.getCountrycode())).collect(Collectors.toList());
        }
        
        else if(field.equals("district")){
            return indexedCityList.stream().sorted((a, b) -> a.getDistrict().compareTo(b.getDistrict())).collect(Collectors.toList());
        }
        
        else if(field.equals("population")){
            return indexedCityList.stream().sorted((a, b) -> a.getPopulation().compareTo(b.getPopulation())).collect(Collectors.toList());
        }
        
        return null;
        
    }


    @Override
    public List<City> sortCitiesByDsitrcit(String district) {
        Sort sort = Sort.by(Sort.DEFAULT_DIRECTION.DESC, "population");
        return pgs.findByDistrictContaining(sort, district);
    }


  

    
    // @Override
    // public List<City> sortCitiesByPopulation(Integer population) {
    //     Sort sort = Sort.by(Sort.DEFAULT_DIRECTION.DESC,"population");

    //     return pgs.findByPopulationContaining(sort, population);
    // }
}
