package com.codecool.marsexploration.configuration.service;

import com.codecool.marsexploration.configuration.model.*;

public class MapConfigurationValidatorImpl implements MapConfigurationValidator {
    @Override
    public boolean validate(MapConfiguration mapConfig) {
        // Calculează numărul total de elemente care trebuie să fie generate
        int totalSizeElements = mapConfig.mapElementConfigurations().stream()
                .mapToInt(config -> config.elementToSizes().stream()
                        .mapToInt(element -> element.elementCount() * element.size())
                        .sum()).sum();

        System.out.printf("Total size of elements: %d\n", totalSizeElements);

        // Verifică dacă numărul total de elemente nu depășește limita impusă de ElementToSpaceRatio
        if (totalSizeElements > mapConfig.mapSize() * mapConfig.mapSize() * mapConfig.elementToSpaceRatio()) {
            System.out.println("Total size of elements exceeds the threshold!");
            return false;
        }

        // Verifică dacă sunt respectate regulile legate de dimensionalitatea elementelor
        for (MapElementConfiguration elementConfig : mapConfig.mapElementConfigurations()) {
            if ((elementConfig.name().equals("mineral") || elementConfig.name().equals("water")) && !elementConfig.elementToSizes().stream()
                    .allMatch(size -> size.size() == 1)) {
                return false;
            }
        }

        return true;
    }
}
