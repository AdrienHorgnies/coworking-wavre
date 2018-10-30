package com.ifosup.coworking.repository;

import com.ifosup.coworking.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
