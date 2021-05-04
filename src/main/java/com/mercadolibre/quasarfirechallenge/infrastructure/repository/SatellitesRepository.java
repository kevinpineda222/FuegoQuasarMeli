package com.mercadolibre.quasarfirechallenge.infrastructure.repository;

import com.mercadolibre.quasarfirechallenge.domain.usecase.satellites.SatelliteBDD;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SatellitesRepository extends JpaRepository<SatelliteBDD, String> {
}
