package com.ifosup.coworking.api.resource;

import com.ifosup.coworking.domain.Building;
import com.ifosup.coworking.dto.BuildingDto;
import com.ifosup.coworking.repository.BuildingRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/buildings")
public class BuildingResource {

    private final BuildingRepository buildingRepository;

    public BuildingResource(BuildingRepository buildingRepository) {
        this.buildingRepository=buildingRepository;
    }

    @GetMapping("")
    public List<Building> getAllBuildings() {
        return buildingRepository.findAll();
    }

    @GetMapping("/code/{code}")
    public List<Building> findByCpCity(@PathVariable("code") int code) {
        return buildingRepository.findByCity(code);}

    @PostMapping("")
    public BuildingDto newbuilding (@RequestBody BuildingDto buildingDto){
        BuildingDto returnValue= new BuildingDto();
        Building building = new Building();
        BeanUtils.copyProperties(buildingDto, building,"id");
        buildingRepository.save(building);
        BeanUtils.copyProperties(building,returnValue);
        return returnValue;
    }

    @PutMapping("{id}")
        public ResponseEntity<Object> updateBuilding(@RequestBody BuildingDto buildingDto, @PathVariable long id){
        Optional<Building> buildingOptional = Optional.ofNullable(buildingRepository.findOne(id));
        Building building= new Building();
            if (!buildingOptional.isPresent())
                return ResponseEntity.notFound().build();
            buildingDto.setId(id);
            BeanUtils.copyProperties(buildingDto, building);
            buildingRepository.save(building);
            return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Building> deleteBuilding(@PathVariable("id") int id){
        Building building = buildingRepository.findOne((long) id);
        if (building==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        buildingRepository.delete((long) id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
