package com.ifosup.coworking.repository;

import com.ifosup.coworking.domain.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceRepository extends JpaRepository<Space,Long>{

    //@Query("select s from space inner join cp on cp.id=space.cp_id where cp.code= :code")
    //public List<Cp> finByCode(@Param("code") int code);
    @Query("select s from Space s join s.cp p where p.code=:code")
    public List<Space> findByCode(@Param("code") int code);
}
