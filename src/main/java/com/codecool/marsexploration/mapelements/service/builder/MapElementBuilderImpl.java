package com.codecool.marsexploration.mapelements.service.builder;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.calculators.service.CoordinateCalculatorImpl;
import com.codecool.marsexploration.calculators.service.DimensionCalculator;
import com.codecool.marsexploration.calculators.service.DimensionCalculatorImpl;
import com.codecool.marsexploration.mapelements.model.MapElement;

import java.util.Arrays;

public class MapElementBuilderImpl implements MapElementBuilder{
    @Override
    public MapElement build(int size, String symbol, String name, int dimensionGrowth, String preferredLocationSymbol) {
        DimensionCalculator dimensionCalculator = new DimensionCalculatorImpl();

        int dimension = dimensionCalculator.calculateDimension(size, dimensionGrowth);
        String[][] representation = representation(size, symbol, dimension);

        return new MapElement(representation, name, dimension, preferredLocationSymbol);
    }

    private String[][] representation(int size, String symbol, int dimension) {
        String[][] representation = new String[dimension][dimension];
        CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl();

        for (int i = 0; i <= size; i++) {
            Coordinate randomCoordinate = coordinateCalculator.getRandomCoordinate(dimension);
            int x = randomCoordinate.getX();
            int y = randomCoordinate.getY();

            representation[x][y] = symbol;
        }

        for (int i = 0; i < representation.length; i++) {
            for (int j = 0; j < representation[0].length; j++) {
                if (representation[i][j] == null) {
                    representation[i][j] = " ";
                }
            }
        }
        return representation;
    }
}
