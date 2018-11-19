package com.ifosup.coworking.api.resource;

import com.ifosup.coworking.domain.City;
import com.ifosup.coworking.dto.CityDto;
import com.ifosup.coworking.repository.CityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cities")
public class CityResource {

    private final CityRepository cityRepository;

    public CityResource(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @GetMapping("")
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @GetMapping("{id}")
    public City findByOne(@PathVariable("id") long id) {
        return cityRepository.findOne(id);
    }

    @GetMapping("/city/{name}")
    public List<City> findByNameCity(@PathVariable("name") String name) {
        return cityRepository.findByName(name);
    }

    @PostMapping("")
    public CityDto newCity(@RequestBody CityDto cityDto) {
        CityDto returnValue = new CityDto();
        City city = new City();
        BeanUtils.copyProperties(cityDto, city, "id");
        cityRepository.save(city);
        BeanUtils.copyProperties(city, returnValue);
        return returnValue;
    }

    @PutMapping("{id}")
    public ResponseEntity updateCity(@RequestBody CityDto cityDto, @PathVariable long id) {
        Optional<City> cityOptional = Optional.ofNullable(cityRepository.findOne(id));
        City city = new City();
        if (!cityOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        cityDto.id = id;
        BeanUtils.copyProperties(cityDto, city);
        cityRepository.save(city);
        return ResponseEntity.ok().body(city);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteCity(@PathVariable("id") long id) {
        City city = cityRepository.findOne(id);
        if (city == null) {
            return ResponseEntity.notFound().build();
        }

        cityRepository.delete(id);
        return ResponseEntity.noContent().build();
    }

}
