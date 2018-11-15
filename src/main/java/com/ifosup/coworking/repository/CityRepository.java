package com.ifosup.coworking.repository;

import com.ifosup.coworking.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    public List<City> findByCpCity(int cpCity);

    public List<City> findByNameCity(String nameCity);

    //public City addCity(City nameCity);

    void deleteCityBy(Long id);
}
