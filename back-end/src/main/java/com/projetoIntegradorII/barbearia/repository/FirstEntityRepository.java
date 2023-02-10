package com.projetoIntegradorII.barbearia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projetoIntegradorII.barbearia.entity.FirstEntity;

import java.util.List;
import java.util.Optional;

public interface FirstEntityRepository extends JpaRepository<FirstEntity, Long> {

   Optional<FirstEntity> findById(Long id);

   List<FirstEntity> findAllByNameIsNotNull();

}
