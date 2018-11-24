package com.ifosup.coworking.repository;

import com.ifosup.coworking.domain.EquipmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the EquipmentType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EquipmentTypeRepository extends JpaRepository<EquipmentType, Long> {

}
