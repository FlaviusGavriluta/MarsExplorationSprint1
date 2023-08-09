package com.codecool.marsexploration.configuration.service;

import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;

public class MapConfigurationValidatorImpl implements MapConfigurationValidator {
    @Override
    public boolean validate(MapConfiguration mapConfig) {
        // 1. Calculate the total number of elements that need to be generated.
        // This includes taking into account the sizes of the elements and multiplying them by their count.
        int totalSizeElements = mapConfig.mapElementConfigurations().stream()
                .mapToInt(config -> config.elementToSizes().stream()
                        .mapToInt(element -> element.elementCount() * element.size())
                        .sum()).sum();

        // 2. Check if the total number of elements does not exceed the limit imposed by ElementToSpaceRatio.
        // This ensures that the elements do not take up too much space on the map.
        if (totalSizeElements > mapConfig.mapSize() * mapConfig.elementToSpaceRatio()) {
            System.out.println("Total size of elements exceeds the threshold!");
            return false;
        }

        // 3. Check if the rules related to the dimensionality of the elements are respected.
        // In this case, "mineral" and "water" must be one-dimensional, and their dimensionality growth must be 0.
        for (MapElementConfiguration elementConfig : mapConfig.mapElementConfigurations()) {
            if ((elementConfig.name().equals("mineral") || elementConfig.name().equals("water")) && (!elementConfig.elementToSizes().stream()
                    .allMatch(size -> size.size() == 1) || elementConfig.dimensionGrowth() != 0)) {
                return false;
            }
        }

        return true;
    }
}