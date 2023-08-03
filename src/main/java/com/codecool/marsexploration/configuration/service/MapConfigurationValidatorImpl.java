package com.codecool.marsexploration.configuration.service;

import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;

import java.util.List;

public class MapConfigurationValidatorImpl implements MapConfigurationValidator {

    private static final String SYMBOL_MOUNTAIN = "#";
    private static final String SYMBOL_PIT = "&";
    private static final String SYMBOL_MINERAL = "%";
    private static final String SYMBOL_WATER = "*";
    private static final double ELEMENT_TO_SPACE_RATIO = 0.5;

    @Override
    public boolean validate(MapConfiguration mapConfig) {
        List<MapElementConfiguration> elements = mapConfig.getMapElementConfigurations();
        int size = mapConfig.getMapSize();
        for (MapElementConfiguration element : elements) {
            if (!validateElement(element)) {
                return false;
            }
        }
        return !(elements.size() > size * ELEMENT_TO_SPACE_RATIO);
    }

    private boolean validateElement(MapElementConfiguration element) {
        return switch (element.symbol()) {
            case SYMBOL_MOUNTAIN -> validateWallElement(element);
            case SYMBOL_PIT -> validateRockElement(element);
            case SYMBOL_MINERAL -> validateResourceElement(element);
            case SYMBOL_WATER -> validateMarkerElement(element);
            default -> false;
        };
    }

    private boolean validateWallElement(MapElementConfiguration element) {
        return element.dimensionGrowth() == 3
                && element.getElementToSizes().size() >= 2
                && element.preferredLocationSymbol().isEmpty();
    }

    private boolean validateRockElement(MapElementConfiguration element) {
        return element.dimensionGrowth() == 10
                && element.getElementToSizes().size() >= 2
                && element.preferredLocationSymbol().isEmpty();
    }

    private boolean validateResourceElement(MapElementConfiguration element) {
        return element.dimensionGrowth() == 0
                && element.getElementToSizes().size() <= 1
                && element.preferredLocationSymbol().equals(SYMBOL_MOUNTAIN);
    }

    private boolean validateMarkerElement(MapElementConfiguration element) {
        return element.dimensionGrowth() == 0
                && element.getElementToSizes().size() <= 1
                && element.preferredLocationSymbol().equals(SYMBOL_PIT);
    }
}
