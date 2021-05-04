package com.mercadolibre.quasarfirechallenge.domain.usecase.satellites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataPosition {

    private double x;
    private double y;

    @JsonIgnore
    public double[] getPoint() {
        return new double[]{x, y};
    }

}
