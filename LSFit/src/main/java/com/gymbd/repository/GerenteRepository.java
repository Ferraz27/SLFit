package com.gymbd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gymbd.model.Gerente;

@Repository
public interface GerenteRepository extends JpaRepository<Gerente, Integer> {
    // Você pode adicionar métodos personalizados, caso necessário.
}
