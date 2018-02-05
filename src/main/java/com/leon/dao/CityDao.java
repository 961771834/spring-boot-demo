package com.leon.dao;

import com.leon.entity.City;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CityDao {

    List<City> findAllCity();

    City findCityById(@Param("id") Long id);

    Long saveCity(City city);

    Long updateCity(City city);

    Long deleteCity(Long id);
}
