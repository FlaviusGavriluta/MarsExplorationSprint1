package com.codecool.marsexploration.mapelements.service.builder;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.calculators.service.CoordinateCalculatorImpl;
import com.codecool.marsexploration.calculators.service.DimensionCalculator;
import com.codecool.marsexploration.calculators.service.DimensionCalculatorImpl;
import com.codecool.marsexploration.mapelements.model.MapElement;

public class MapElementBuilderImpl implements MapElementBuilder {
    private final DimensionCalculator dimensionCalculator;
    private final CoordinateCalculator coordinateCalculator;

    public MapElementBuilderImpl() {
        this.dimensionCalculator = new DimensionCalculatorImpl();
        this.coordinateCalculator = new CoordinateCalculatorImpl();
    }

    @Override
    public MapElement build(int size, String symbol, String name, int dimensionGrowth, String preferredLocationSymbol) {
        int dimension = dimensionCalculator.calculateDimension(size, dimensionGrowth);
        String[][] representation = generateRepresentation(size, symbol, dimension);

        return new MapElement(representation, name, dimension, preferredLocationSymbol);
    }

    private String[][] generateRepresentation(int size, String symbol, int dimension) {
        String[][] representation = new String[dimension][dimension];

        for (int i = 0; i <= size; i++) {
            Coordinate randomCoordinate = coordinateCalculator.getRandomCoordinate(dimension);
            int x = randomCoordinate.x();
            int y = randomCoordinate.y();

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
