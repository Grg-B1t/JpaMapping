package com.example.jpa_practice.Service;

import java.util.List;

import com.example.jpa_practice.Entity.City;

public interface pagingAndSortingServiceInterface {
    List<City> pagingCities(Integer offset, Integer size);
    List<City> sortCitiesByCountrycode(String countrycode);
    List<City> sortCitiesByField(String field, Integer start, Integer end);
    //List<City> sortCitiesByPopulation(Integer population);
    List<City> sortCitiesByDsitrcit(String district);
}
