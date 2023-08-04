package com.codecool.marsexploration.configuration.service;

import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;

public class MapConfigurationValidatorImpl implements MapConfigurationValidator {
    private static final double ELEMENT_TO_SPACE_RATIO_THRESHOLD = 0.5;

    @Override
    public boolean validate(MapConfiguration mapConfig) {
        // Check if minerals are defined as multi-dimensional
        for (MapElementConfiguration elementConfig : mapConfig.getMapElementConfigurations()) {
            if (elementConfig.getSymbol().equals("%") && elementConfig.getElementToSizes().size() > 1) {
                System.out.println("ERROR: Minerals cannot be multi-dimensional.");
                return false;
            }
        }

        // Check if the total number of elements exceeds the ElementToSpaceRatio
        int totalElements = getTotalElements(mapConfig);
        int maxAllowedElements = (int) (mapConfig.getMapSize() * mapConfig.getElementToSpaceRatio());

        if (totalElements > maxAllowedElements) {
            System.out.println("ERROR: The total number of elements exceeds the ElementToSpaceRatio.");
            return false;
        }

        // All validations passed, the MapConfiguration is valid
        return true;
    }

    private int getTotalElements(MapConfiguration mapConfig) {
        int totalElements = 0;

        for (MapElementConfiguration elementConfig : mapConfig.getMapElementConfigurations()) {
            for (ElementToSize elementToSize : elementConfig.getElementToSizes()) {
                totalElements += elementToSize.elementCount();
            }
        }

        return totalElements;
    }
}
