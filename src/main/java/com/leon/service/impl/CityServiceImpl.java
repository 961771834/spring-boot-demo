package com.leon.service.impl;

import com.leon.dao.CityDao;
import com.leon.entity.City;
import com.leon.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CityServiceImpl implements CityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

    @Autowired
    private CityDao cityDao;

    @Autowired
    private RedisTemplate redisTemplate;

    /*
    * 获取城市逻辑：
    * 如果缓存存在，从缓存中获取城市信息
    * 如果缓存不存在，从 DB 中获取城市信息，然后插入缓存
    * */
    @Override
    public City findCityById(Long id) {
        // 从缓存中获取城市信息

        String key = "city_" + id;

        ValueOperations<String,City> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean haskey = redisTemplate.hasKey(key);

        if(haskey){
            City city = operations.get(key);

            LOGGER.info("CityServiceImpl.findCityById() : 从缓存中获取了城市 >>"+ city.toString());
            return city;
        }

        // 从DB中获取数据

        City city = cityDao.findCityById(id);

        // 插入缓存
        operations.set(key,city,10, TimeUnit.SECONDS);

        LOGGER.info("CityServiceImpl.findCityById() : 城市插入缓存 >> " + city.toString());

        return city;
    }

    @Override
    public List<City> findAllCity() {
        return cityDao.findAllCity();
    }

    @Override
    public Long saveCity(City city) {
        return cityDao.saveCity(city);
    }

    @Override
    public Long updateCity(City city) {
        Long ret = cityDao.updateCity(city);

        // 缓存存在，删除缓存
        String key = "city_" + city.getId();

        boolean haskey = redisTemplate.hasKey(key);
        if(haskey){
            redisTemplate.delete(key);

            LOGGER.info("CityServiceImpl.updateCity() : 从缓存中删除城市 >> " + city.toString());
        }

        return ret;
    }

    @Override
    public Long deleteCity(Long id) {

        Long ret = cityDao.deleteCity(id);

        // 缓存存在，删除缓存
        String key = "city_" + id;

        boolean haskey = redisTemplate.hasKey(key);
        if(haskey){
            redisTemplate.delete(key);

            LOGGER.info("CityServiceImpl.updateCity() : 从缓存中删除城市 id>> " + id);
        }

        return ret;
    }
}
