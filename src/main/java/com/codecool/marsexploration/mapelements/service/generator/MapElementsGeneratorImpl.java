package com.codecool.marsexploration.mapelements.service.generator;

import com.codecool.marsexploration.calculators.service.DimensionCalculator;
import com.codecool.marsexploration.calculators.service.DimensionCalculatorImpl;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;
import com.codecool.marsexploration.mapelements.model.MapElement;

import java.util.ArrayList;
import java.util.List;

public class MapElementsGeneratorImpl implements MapElementsGenerator{
    @Override
    public Iterable<MapElement> createAll(MapConfiguration mapConfig) {
        List<MapElement> generatedElements = new ArrayList<>();
        DimensionCalculator dimensionCalculator = new DimensionCalculatorImpl();

        for (MapElementConfiguration elementConfig : mapConfig.getMapElementConfigurations()) {
            String symbol = elementConfig.symbol();
            String name = elementConfig.name();
            int dimensionGrowth = elementConfig.dimensionGrowth();
            String preferredLocationSymbol = elementConfig.preferredLocationSymbol();

            int dimension = dimensionCalculator.calculateDimension(mapConfig.getMapSize(), dimensionGrowth);
            String[][] representation = createRepresentation(symbol, dimension);

            MapElement mapElement = new MapElement(representation, name, dimension, preferredLocationSymbol);
            generatedElements.add(mapElement);
        }

        return generatedElements;
    }

    private String[][] createRepresentation(String symbol, int dimension) {
        String[][] representation = new String[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                representation[i][j] = symbol;
            }
        }
        return representation;
    }
}
