package com.mercadolibre.quasarfirechallenge.domain.usecase.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mercadolibre.quasarfirechallenge.domain.usecase.satellites.DataPosition;
import com.mercadolibre.quasarfirechallenge.domain.usecase.satellites.SatelliteBDD;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Satellite {

    private DataPosition dataPosition;
    private String name;
    private List<String> message;
    private double distance;

    @JsonIgnore
    public Satellite(SatelliteBDD satelliteBDD) {
        this.dataPosition = new DataPosition(satelliteBDD.getX(), satelliteBDD.getY());
        this.name = satelliteBDD.getName();
        this.message = Arrays.asList(satelliteBDD.getMessage() != null ? satelliteBDD.getMessage().split(",", -1) : new String[]{});
        this.distance = satelliteBDD.getDistance() != null ? satelliteBDD.getDistance() : 0;
    }

}
