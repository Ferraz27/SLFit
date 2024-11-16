package com.gymbd.repository;

import com.gymbd.model.Plano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlanoRepository extends JpaRepository<Plano, Integer> {

    
}
