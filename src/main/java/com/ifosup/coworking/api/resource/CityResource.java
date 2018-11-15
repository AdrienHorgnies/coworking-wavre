package com.ifosup.coworking.api.resource;

import com.ifosup.coworking.domain.City;
import com.ifosup.coworking.dto.CityDto;
import com.ifosup.coworking.repository.CityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/code/{code}")
    public List<City> findByCpCity(@PathVariable("code") int code) {
        return cityRepository.findByCpCity(code);}

    @GetMapping("/city/{name_city}")
    public List<City> findByNameCity(@PathVariable("name_city") String name_city) {
        return cityRepository.findByNameCity(name_city);}

    @PostMapping("/add/{nameCity,cpCity}")
    public CityDto createCity(@RequestBody CityDto cityDto){
        City city = convertToEntity(cityDto);
        City cityCreated = cityRepository.creatCity(city);
        return convertToDto(cityCreated);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<City> deleteCity(@PathVariable("id") int id){
        City city = cityRepository.findOne((long) id);
        if (city==null){
            System.out.println("Ville avec id "+id+" non trouvée");
            return new ResponseEntity<City>(HttpStatus.NOT_FOUND);
        }
        cityRepository.delete((long) id);
        return new ResponseEntity<City>(HttpStatus.NO_CONTENT);

    }

}
