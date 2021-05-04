package com.mercadolibre.quasarfirechallenge.domain.usecase.satellites;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SATELLITES")
@Data
public class SatelliteBDD {

    @Id
    private String name;
    private Double distance;
    private Double x;
    private Double y;
    private String message;

}
