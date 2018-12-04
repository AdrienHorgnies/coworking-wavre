package com.ifosup.coworking.repository;

import com.ifosup.coworking.domain.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data JPA repository for the Building entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {

    @Query("select distinct building from Building building join building.spaces space")
    List<Building> findWithSpaces();
}
