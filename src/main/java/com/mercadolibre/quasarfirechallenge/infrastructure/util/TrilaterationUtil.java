package com.mercadolibre.quasarfirechallenge.infrastructure.util;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;

/**
 * Utilidad para calcular posición a partir de trilateración
 */
public class TrilaterationUtil {

    /**
     * Procesa los datos recibidos y calcula la posición de la fuente
     * @param locations Posiciónes de los satélites
     * @param distances Distancias al emisor
     * @return Coordenadas x e y del emisor del mensaje (fuente)
     */
    public static double[] getLocation(double[][] locations, double[] distances) {

        TrilaterationFunction trilaterationFunction = new TrilaterationFunction(locations, distances);
        NonLinearLeastSquaresSolver nSolver = new NonLinearLeastSquaresSolver(trilaterationFunction, new LevenbergMarquardtOptimizer());

        return nSolver.solve().getPoint().toArray();
    }

}
