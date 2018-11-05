package com.ifosup.coworking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
//@NamedQueries({
//    @NamedQuery(
//        name = "findByCode",
//        query = "from cp c where c.code = :code"
//    ),
//    })
@JsonIgnoreProperties(value = "id")

public class Cp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public int code;

    public String city;

    @OneToMany(mappedBy = "cp")
    @JsonIgnore
    public Set<Space> spaces = new HashSet<>();

    //Cp findByCode(int code){

    //}



    }

