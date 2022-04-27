package com.firstproject.firstproject.repositories;

import com.firstproject.firstproject.domain.Roles;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles, Integer>{
    
}
