package com.example.jpa_practice.Service;

import java.util.List;
import com.example.jpa_practice.Entity.City;
import com.example.jpa_practice.Entity.CityData;
import com.example.jpa_practice.Entity.DistrictData;

public interface CityServiceInterface {
    List<City> getAllCities();
    City addNewCity(City newCity);
    City updateCity(City updatedCity);
    String deleteCity(City deleteCity);
    List<City> getCitiesByJPAQuery(String countrycode, String district);
    List<City> getCitiesByCountrycodeJPA(String countrycode);
    List<DistrictData> getDistrcitByCountrycodeJPA(String countrycode);
    List<String> getDistrictsNames(String countrycode);
    List<CityData> getCityDataByCountrycode(String countrycode);
    Integer removeCityByCountrycode(String countrycode);
}
