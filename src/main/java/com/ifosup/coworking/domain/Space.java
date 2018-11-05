package com.ifosup.coworking.domain;

import javax.persistence.*;

@Entity

public class Space {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;


    public int area;

    @Column(length = 100)
    public String location;

    @ManyToOne
    public Cp cp;

    @Override
    public String toString() {
        return "Space{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", area='" + area + '\'' +
            ", location='" + location + '\'' +
            //", city='" + city + '\'' +
            '}';
    }
}

