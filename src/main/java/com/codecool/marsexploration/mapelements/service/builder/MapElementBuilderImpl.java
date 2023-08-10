package com.codecool.marsexploration.mapelements.service.builder;

import com.codecool.marsexploration.calculators.service.DimensionCalculator;
import com.codecool.marsexploration.mapelements.model.MapElement;

public class MapElementBuilderImpl implements MapElementBuilder {
    private final DimensionCalculator dimensionCalculator;

    public MapElementBuilderImpl(DimensionCalculator dimensionCalculator) {
        this.dimensionCalculator = dimensionCalculator;
    }

    @Override
    public MapElement build(int size, String symbol, String name, int dimensionGrowth, String preferredLocationSymbol) {
        int dimension = dimensionCalculator.calculateDimension(size, dimensionGrowth);
        String[][] representation = new String[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                representation[i][j] = symbol;
            }
        }

        return new MapElement(representation, name, dimension, preferredLocationSymbol);
    }
}