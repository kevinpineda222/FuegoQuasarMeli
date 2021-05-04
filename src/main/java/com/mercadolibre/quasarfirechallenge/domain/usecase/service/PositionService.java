package com.mercadolibre.quasarfirechallenge.domain.usecase.service;

import com.mercadolibre.quasarfirechallenge.domain.usecase.dto.Satellite;
import com.mercadolibre.quasarfirechallenge.domain.usecase.satellites.DataPosition;
import com.mercadolibre.quasarfirechallenge.infrastructure.exception.MessagesException;
import com.mercadolibre.quasarfirechallenge.infrastructure.util.MessageFormater;
import com.mercadolibre.quasarfirechallenge.infrastructure.util.TrilaterationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

/**
 * Servicio que permite procesar la información de los satélites y obtener la posición de la fuente
 */
@Service
public class PositionService {

    @Autowired
    Environment environment;


    /**
     * Función que procesa la información de los Satélites para encontrar la posición de la fuente
     * @param request Información recibida de los satélites
     * @return Posición de la fuente
     * @throws MessagesException
     */
    public DataPosition decodingPosition(MessageFormater request) throws MessagesException {
        if (request.getSatellites().size() != 3) {
            throw new MessagesException("Se deben ingresar las distancias a los tres satélites");
        }
        fillPositions(request);
        double[] distances = request.getDistances();
        double[][] locations = request.getPositions();
        double[] point = TrilaterationUtil.getLocation(locations, distances);
        return new DataPosition(point[0], point[1]);
    }

    private void fillPositions(MessageFormater request) {
        for (Satellite sat : request.getSatellites()) {
            String[] strPos = Objects.requireNonNull(environment.getProperty("satellite." + sat.getName())).split(",");
            double[] position = Arrays.stream(strPos).mapToDouble(Double::parseDouble).toArray();
            sat.setDataPosition(new DataPosition(position[0], position[1]));
        }
    }

}
