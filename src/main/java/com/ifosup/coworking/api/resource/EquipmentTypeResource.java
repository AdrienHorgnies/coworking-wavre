package com.ifosup.coworking.api.resource;

import com.ifosup.coworking.domain.EquipmentType;
import com.ifosup.coworking.dto.EquipmentTypeDto;
import com.ifosup.coworking.repository.EquipmentTypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipmenttype")
public class EquipmentTypeResource {

    private final EquipmentTypeRepository equipmentTypeRepository;

    public EquipmentTypeResource(EquipmentTypeRepository equipmentTypeRepository) {
        this.equipmentTypeRepository= equipmentTypeRepository;
    }

        @GetMapping("")
    public List<EquipmentType> getAllEquipmentType() {
        return equipmentTypeRepository.findAll();
    }

    @GetMapping("{id}")
    public EquipmentType findByOne(@PathVariable("id") long id) {
        return equipmentTypeRepository.findOne(id);}

    @GetMapping("/equipment/{name}")
    public List<EquipmentType> findByName(@PathVariable("name") String name) {
        return equipmentTypeRepository.findByName(name);}

    @PostMapping("")
    public EquipmentTypeDto newequipment (@RequestBody EquipmentTypeDto equipmentTypeDto){
        EquipmentTypeDto returnValue=new EquipmentTypeDto();
        EquipmentType equipmentType = new EquipmentType();
        BeanUtils.copyProperties(equipmentTypeDto, equipmentType, "id");
        equipmentTypeRepository.save(equipmentType);
        BeanUtils.copyProperties(equipmentTypeDto, returnValue);
        return returnValue; }

    @PutMapping("{id}")
        public ResponseEntity<Object> updateEquipmentType(@RequestBody EquipmentTypeDto equipmentTypeDto, @PathVariable long id){
        Optional<EquipmentType> equipmentTypeOptional = Optional.ofNullable(equipmentTypeRepository.findOne(id));
        EquipmentType equipmentType = new EquipmentType();
            if (!equipmentTypeOptional.isPresent())
                return ResponseEntity.notFound().build();
            equipmentTypeDto.id=id;
            BeanUtils.copyProperties(equipmentTypeDto, equipmentType);
            equipmentTypeRepository.save(equipmentType);
            return ResponseEntity.ok().body(equipmentType);  }

    @DeleteMapping("{id}")
        public ResponseEntity<EquipmentType> deleteEquipmentType(@PathVariable("id") int id){
        EquipmentType equipmentType = equipmentTypeRepository.findOne((long) id);
        if (equipmentType==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        equipmentTypeRepository.delete((long) id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
