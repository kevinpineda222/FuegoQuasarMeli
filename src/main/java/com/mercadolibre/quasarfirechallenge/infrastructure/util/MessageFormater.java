package com.mercadolibre.quasarfirechallenge.infrastructure.util;

import com.mercadolibre.quasarfirechallenge.domain.usecase.dto.Satellite;
import com.mercadolibre.quasarfirechallenge.domain.usecase.satellites.DataPosition;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageFormater {

    @ApiModelProperty(notes = "Información recibida de los satélites",name="satellites",required=true)
    private List<Satellite> satellites;

    public double[][] getPositions() {
        List<DataPosition> lsDataPositions = satellites.stream().map(Satellite::getDataPosition).collect(Collectors.toList());
        double[][] points = new double[lsDataPositions.size()][];
        for (int i = 0; i < lsDataPositions.size(); i++) {
            points[i] = lsDataPositions.get(i).getPoint();
        }
        return points;
    }

    public double[] getDistances() {
        List<Double> distancesD = satellites.stream().map(Satellite::getDistance).collect(Collectors.toList());
        return distancesD.stream().mapToDouble(d -> d).toArray();
    }

    public List<List<String>> getMessages() {
        return satellites.stream().map(Satellite::getMessage).collect(Collectors.toList());
    }
}
