package com.ifosup.coworking.repository;

import com.ifosup.coworking.domain.Cp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CpRepository extends JpaRepository<Cp,Long>{

    public List<Cp> findByCode(int code);
    public List<Cp> findByCity(String city);
    //@Query("select c from Cp c where c.code= :code")
    //public List<Cp> finByCode(@Param("code") int code);

    }



