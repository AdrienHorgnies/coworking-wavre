package com.ifosup.coworking.api.resource;

import com.ifosup.coworking.domain.Building;
import com.ifosup.coworking.repository.BuildingRepository;
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

    @PostMapping("/add")
    Building newbuilding (@RequestBody Building newbuilding){
        return buildingRepository.save(newbuilding);
    }

    @PutMapping("/update/{id}")
        public ResponseEntity<Object> updateBuilding(@RequestBody Building building, @PathVariable long id){
        Optional<Building> buildingOptional = Optional.ofNullable(buildingRepository.findOne(id));
            if (!buildingOptional.isPresent())
                return ResponseEntity.notFound().build();
            building.setId(id);
            buildingRepository.save(building);
            return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Building> deleteCity(@PathVariable("id") int id){
        Building building = buildingRepository.findOne((long) id);
        if (building==null){
            System.out.println("Building avec id "+id+" non trouvée");
            return new ResponseEntity<Building>(HttpStatus.NOT_FOUND);
        }
        buildingRepository.delete((long) id);
        return new ResponseEntity<Building>(HttpStatus.NO_CONTENT);

    }

}
