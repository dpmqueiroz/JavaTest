package com.sigabem.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sigabem.domain.model.ServicoFrete;

@Repository
public interface PrecoFreteRepository extends JpaRepository<ServicoFrete, Long> {

}
