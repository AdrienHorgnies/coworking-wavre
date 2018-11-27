package com.ifosup.coworking.repository;

import com.ifosup.coworking.domain.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the Space entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SpaceRepository extends JpaRepository<Space, Long>, JpaSpecificationExecutor<Space> {

    @Query("select distinct space from Space space left join fetch space.equipmentTypes")
    List<Space> findAllWithEagerRelationships();

    @Query("select space from Space space left join fetch space.equipmentTypes where space.id =:id")
    Space findOneWithEagerRelationships(@Param("id") Long id);

    @Query("select min(price) from Space")
    Integer findMinPrice();

    @Query("select max(price) from Space")
    Integer findMaxPrice();
}
