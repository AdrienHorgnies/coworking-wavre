package com.ifosup.coworking.repository;

import com.ifosup.coworking.domain.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {

    //@Query("select b from building b join b.cities c where c.cpCity=:code")
    public List<Building> findByCity(@Param("code") int code);


}
