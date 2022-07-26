package com.example.jpa_practice.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jpa_practice.Entity.City;
import com.example.jpa_practice.Entity.CityData;
import com.example.jpa_practice.Entity.DistrictData;
import com.example.jpa_practice.Jpa.repo;


@Service
public class CityService implements CityServiceInterface{

    @Autowired
    repo cr;

    @Override
    public List<City> getAllCities() {
        return cr.findAll();
    }

    @Override
    public City addNewCity(City newCity) {
        City city = new City();

        Long count = cr.findAll().stream().count();
        newCity.setId(count + 2);

        System.out.println(count);
        
        city.setId(newCity.getId());
        city.setName(newCity.getName());
        city.setCountrycode(newCity.getCountrycode());
        city.setDistrict(newCity.getDistrict());
        city.setPopulation(newCity.getPopulation());

        cr.save(newCity);
        return city;
    }

    @Override
    public City updateCity(City updatedCity) {
        City city = cr.findAll().stream().filter(a -> a.getId().equals(updatedCity.getId())).findFirst().get();
        city.setName(updatedCity.getName());
        city.setCountrycode(updatedCity.getCountrycode());
        city.setDistrict(updatedCity.getDistrict());
        city.setPopulation(updatedCity.getPopulation());

        return cr.save(city);
    }

    @Override
    public String deleteCity(City deleteCity) {
        City city = cr.findAll().stream().filter(a -> a.getId().equals(deleteCity.getId())).findFirst().get();        
        cr.delete(city);

        return "City is removed from the Database!";
    }

    @Override
    public List<City> getCitiesByJPAQuery(String countrycode, String district) {
        return cr.findCity(countrycode, district);
    }

    @Override
    public List<City> getCitiesByCountrycodeJPA(String countrycode) {
        return cr.findALLCitiesByCountrycode(countrycode);
    }

    @Override
    public List<DistrictData> getDistrcitByCountrycodeJPA(String countrycode) {
         //List<City> cityList = cr.findDsitrictByCountryCodeJPA(countrycode);
         List<City> cityList = cr.findALLCitiesByCountrycode(countrycode);
         List<DistrictData> districtList = new ArrayList<>();

         for (City city : cityList) {
            districtList.add(new DistrictData(city.getId(), city.getDistrict()));
         }
         return districtList;
        }

    @Override
    public List<String> getDistrictsNames(String countrycode) {

        List<String> districts = cr.findALLCitiesByCountrycode(countrycode)
         .stream()
         .map(a -> a.getDistrict())
         .distinct()
         .collect(Collectors.toList());

        return districts;
    }

    @Override
    public List<CityData> getCityDataByCountrycode(String countrycode) {
        List<CityData> cityList = cr.findALLCitiesByCountrycode(countrycode)
        .stream()
        .map(a -> new CityData(a.getName(), a.getDistrict(), a.getPopulation()))
        .collect(Collectors.toList());

        return cityList;
    }

    @Override
    public Integer removeCityByCountrycode(String countrycode) {
        return cr.deleteCityByCountrycode(countrycode);
    }
    

}
