package com.ifosup.coworking.repository;


import com.ifosup.coworking.domain.EquipmentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentTypeRepository extends JpaRepository<EquipmentType, Long> {

    List<EquipmentType> findByName(String name);
}
