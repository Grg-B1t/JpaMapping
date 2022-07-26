package com.example.jpa_practice.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="city")
public class City {
    @Id
    private Long id;
    @NotBlank(message = "Name cannot be null!")
    private String name;
    @NotBlank(message = "Countrycode cannot be null!")
    @Size(min = 3, max = 3, message = "Country code must be in three letter!")
    private String countrycode;
    @NotBlank(message = "District cannot be null!")
    private String district;
    private Integer population;

    public City() {
    }

    public City(Long id, String name, String countrycode, String district, Integer population) {
        this.id = id;
        this.name = name;
        this.countrycode = countrycode;
        this.district = district;
        this.population = population;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCountrycode() {
        return countrycode;
    }
    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    public Integer getPopulation() {
        return population;
    }
    public void setPopulation(Integer population) {
        this.population = population;
    }

    
}
