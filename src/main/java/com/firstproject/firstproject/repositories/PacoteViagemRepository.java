package com.firstproject.firstproject.repositories;

import com.firstproject.firstproject.domain.PacoteViagem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacoteViagemRepository extends JpaRepository<PacoteViagem, Integer>{
    
    

}
