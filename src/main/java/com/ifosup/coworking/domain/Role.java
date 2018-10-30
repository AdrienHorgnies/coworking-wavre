package com.ifosup.coworking.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;

    @ManyToMany(mappedBy = "roles")
    public Set<User> users;
}
