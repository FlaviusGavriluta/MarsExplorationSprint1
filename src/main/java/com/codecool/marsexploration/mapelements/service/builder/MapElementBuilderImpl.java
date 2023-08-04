package com.codecool.marsexploration.mapelements.service.builder;

import com.codecool.marsexploration.calculators.service.DimensionCalculatorImpl;
import com.codecool.marsexploration.mapelements.model.MapElement;

import java.util.Arrays;

public class MapElementBuilderImpl implements MapElementBuilder{
    @Override
    public MapElement build(int size, String symbol, String name, int dimensionGrowth, String preferredLocationSymbol) {
        DimensionCalculatorImpl dimensionCalculator = new DimensionCalculatorImpl();
        int dimension = dimensionCalculator.calculateDimension(size, dimensionGrowth);
        String[][] representation = representation(symbol, dimension);

        return new MapElement(representation, name, dimension, preferredLocationSymbol);
    }

    private String[][] representation(String symbol, int dimension) {
        String[][] representation = new String[dimension][dimension];
        for (int i = 0; i <= dimension - 1; i++) {
            for (int j = 0; j <= dimension - 1; j++) {
                representation[i][j] = symbol;
            }
        }
        return representation;
    }
}
