package com.codecool.marsexploration.configuration.service;

import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;

public class MapConfigurationValidatorImpl implements MapConfigurationValidator {
    @Override
    public boolean validate(MapConfiguration mapConfig) {
        // Check if the total number of elements does not exceed the ElementToSpaceRatio
        int totalElements = getTotalElements(mapConfig);
        double elementToSpaceRatio = mapConfig.elementToSpaceRatio();
        int mapSize = mapConfig.mapSize();
        int maxElements = (int) (mapSize * mapSize * elementToSpaceRatio);
        if (totalElements > maxElements) {
            return false;
        }

        // Check if all element configurations are valid
        for (MapElementConfiguration elementConfig : mapConfig.mapElementConfigurations()) {
            if (!isValidElementConfiguration(elementConfig)) {
                return false;
            }
        }
        return true;
    }

    private int getTotalElements(MapConfiguration mapConfig) {
        int totalElements = 0;
        for (MapElementConfiguration elementConfig : mapConfig.mapElementConfigurations()) {
            for (ElementToSize elementToSize : elementConfig.elementToSizes()) {
                totalElements += elementToSize.elementCount() * elementToSize.size();
            }
        }
        return totalElements;
    }

    private boolean isValidElementConfiguration(MapElementConfiguration elementConfig) {
        // Check if minerals and water are single-dimensional elements
        if ((elementConfig.symbol().equals("%") || elementConfig.symbol().equals("*"))
                && elementConfig.dimensionGrowth() != 0) {
            return false;
        }

        // Check if mountains are multi-dimensional elements
        if (elementConfig.symbol().equals("#") && elementConfig.dimensionGrowth() != 3) {
            return false;
        }

        // Check if pits are multi-dimensional elements
        if (elementConfig.symbol().equals("&") && elementConfig.dimensionGrowth() != 10) {
            return false;
        }

        return true;
    }
}
