package com.example.jpa_practice.Controller;

import java.util.List;

import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa_practice.Entity.City;
import com.example.jpa_practice.Entity.CityData;
import com.example.jpa_practice.Entity.DistrictData;
import com.example.jpa_practice.Jpa.PaginationAndSortingInterface;
import com.example.jpa_practice.Service.CityService;
import com.example.jpa_practice.Service.PagingAndSortingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(value = "Api output", description = "dscription of the API", produces = "application/json")
public class Endpoints {

    CityService cs;
    PagingAndSortingService pgss;


    @Autowired
    public Endpoints(CityService cs, PagingAndSortingService pgss) {
        this.cs = cs;
        this.pgss = pgss;
    }

    @ApiOperation(value = "Get all Cities")
    @GetMapping(path = "/getAllCities", produces = {"application/json", "application/xml"})
    public ResponseEntity<List<City>> getAllCities(){
        try {
            return new ResponseEntity<>(cs.getAllCities(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "add new city")
    @PostMapping(path = "/addNewCity", consumes = {"application/xml", "application/json"}, produces = {"application/xml", "application/json"})
    public ResponseEntity<City> addNewCity(@RequestBody City newCity){
        try {
            return new ResponseEntity<>(cs.addNewCity(newCity), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<City>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @ApiOperation(value = "update city", produces = "application/json")
    @PutMapping("/updateCityById")
    public ResponseEntity<City> updateCity(@RequestBody City updatedCity){
        try {
            return new ResponseEntity<City>(cs.updateCity(updatedCity), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<City>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @ApiOperation(value = "delete city by id", produces = "application/json")
    @DeleteMapping("/deleteCityById")
    public ResponseEntity<String> deleteCity(@RequestBody City deleteCity){
        try {
            return new ResponseEntity<>(cs.deleteCity(deleteCity), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @ApiOperation(value = "pagination of cities", produces = "application/json")
    @GetMapping("/pagedCities")
    public ResponseEntity<List<City>> pagedCities(@RequestParam(value = "offset", defaultValue = "0", required = false) Integer offset,
     @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize){
        try {
            return new ResponseEntity<>(pgss.pagingCities(offset, pageSize), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
     }

     @ApiOperation(value = "sorted cities", produces = "application/json")
     @GetMapping("/sortedCities")
     public ResponseEntity<List<City>> sortedCities(@RequestParam String countrycode){
     try {
             return new ResponseEntity<>(pgss.sortCitiesByCountrycode(countrycode), HttpStatus.FOUND);
         } catch (Exception e) {
            System.out.println(e);
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
      }

      @ApiOperation(value = "sorted cities by field", produces = "application/json")
      @GetMapping("/sortedCitiesByField/{field}")
     public ResponseEntity<List<City>> sortedCitiesByField
     (@PathVariable String field,
        @RequestParam(value = "start", defaultValue = "1", required = false) Integer start,
     @RequestParam(value = "end", defaultValue = "10", required = false) Integer end){
     try {
             return new ResponseEntity<>(pgss.sortCitiesByField(field, start, end), HttpStatus.FOUND);
         } catch (Exception e) {
            System.out.println(e);
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
      }

      @ApiOperation(value = "sorted cities by district", produces = "application/json")
      @GetMapping("/sortedCitiesByDistrict/{district}")
      public ResponseEntity<List<City>> sortedCitiesByPopulation(@PathVariable String district){
        try {
            return new ResponseEntity<>(pgss.sortCitiesByDsitrcit(district), HttpStatus.FOUND);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "get all cities using JPA Query", produces = "application/json")
        @GetMapping("/getCities")
        public ResponseEntity<List<City>> getCitiesByJPAQuery(
            @RequestParam String countrycode,
            @RequestParam String district){
                try {
                    return new ResponseEntity<>(cs.getCitiesByJPAQuery(countrycode, district), HttpStatus.FOUND);
                } catch (Exception e) {
                    System.out.println(e);
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }

            @ApiOperation(value = "get cities by countrycode", produces = "application/json")
            @GetMapping("/getCitiesByCountrycode/{countrycode}")
            public ResponseEntity<List<City>>  getCititesByCountrycodeJPA(@PathVariable String countrycode){
                try {
                    return new ResponseEntity<>(cs.getCitiesByCountrycodeJPA(countrycode), HttpStatus.FOUND);
                } catch (Exception e) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }

            @ApiOperation(value = "get districts by countrycode", produces = "application/json")
            @GetMapping("/getDistrictsByCountrycode/{countrycode}")
            public ResponseEntity<List<DistrictData>>  getDistrictsByCountrycodeJPA(@PathVariable String countrycode){
                try {
                    return new ResponseEntity<>(cs.getDistrcitByCountrycodeJPA(countrycode), HttpStatus.FOUND);
                } catch (Exception e) {
                    System.out.println(e);
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }

            @ApiOperation(value = "get districts in a country", produces = "application/json")
            @GetMapping("/getDistrictsInCountry/{countrycode}")
            public ResponseEntity<List<String>> getDistrictsInCountry(@PathVariable String countrycode){
                try {
                    return new ResponseEntity<>(cs.getDistrictsNames(countrycode), HttpStatus.FOUND);
                } catch (Exception e) {
                    System.out.println(e);
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }

            @ApiOperation(value = "get cities in a country", produces = "application/json")
            @GetMapping("/getCityDataByCountry/{countrycode}")
            public ResponseEntity<List<CityData>> getCityDataByCountry(@PathVariable String countrycode){
                try {
                    return new ResponseEntity<>(cs.getCityDataByCountrycode(countrycode), HttpStatus.FOUND);
                } catch (Exception e) {
                    System.out.println(e);
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }

            @ApiOperation(value = "remove cities in a country")
            @DeleteMapping("/removeCitiesByCountry/{countrycode}")
            public ResponseEntity<Integer> removeCityByCountry(@PathVariable String countrycode){
                try {
                    return new ResponseEntity<>(cs.removeCityByCountrycode(countrycode), HttpStatus.FOUND);
                } catch (Exception e) {
                    System.out.println(e);
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }



      }







