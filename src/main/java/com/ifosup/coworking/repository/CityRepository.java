package com.ifosup.coworking.repository;

import com.ifosup.coworking.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findByCpCity(int cpCity);

    List<City> findByName(String name);

    void deleteCityBy(Long id);

}
