package com.ifosup.coworking.api.resource;

import com.ifosup.coworking.api.util.HeaderUtil;
import com.ifosup.coworking.domain.City;
import com.ifosup.coworking.dto.CityDto;
import com.ifosup.coworking.repository.CityRepository;
import com.ifosup.coworking.service.mapper.CityMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityResource {

    private static final String ENTITY_NAME = "city";

    private final CityRepository cityRepository;

    private final CityMapper cityMapper;

    public CityResource(CityRepository cityRepository, CityMapper cityMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    @GetMapping("")
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<CityDto> findByOne(@PathVariable("id") long id) {
        City city = cityRepository.findOne(id);
        if (city.id == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cityMapper.toDto(city));
    }

    @GetMapping("/city/{name}")
    public List<City> findByNameCity(@PathVariable("name") String name) {
        return cityRepository.findByName(name);
    }

    @PostMapping("")
    public ResponseEntity<CityDto> newCity(@RequestBody @Valid CityDto cityDto) throws URISyntaxException {
        if (cityDto.id != null) {
            return ResponseEntity
                .badRequest()
                .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "id_exists", "A new city cannot already have an ID"))
                .build();
        }

        City city = cityRepository.save(cityMapper.toEntity(cityDto));
        return ResponseEntity
            .created(new URI("/api/cities/" + city.id.toString()))
            .body(cityMapper.toDto(city));
    }

    @PutMapping("")
    public ResponseEntity<CityDto> updateCity(@RequestBody @Valid CityDto cityDto) {
        City city = cityRepository.findOne(cityDto.id);
        if (city == null) {
            return ResponseEntity.notFound().build();
        }

        City savedCity = cityRepository.save(cityMapper.toEntity(cityDto));
        return ResponseEntity
            .ok()
            .body(cityMapper.toDto(savedCity));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteCity(@PathVariable("id") Long id) {
        City city = cityRepository.findOne(id);
        if (city == null) {
            return ResponseEntity.notFound().build();
        }

        cityRepository.delete(id);
        return ResponseEntity
            .noContent()
            .build();
    }

}
