package com.codecool.marsexploration;

import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.calculators.service.CoordinateCalculatorImpl;
import com.codecool.marsexploration.calculators.service.DimensionCalculator;
import com.codecool.marsexploration.calculators.service.DimensionCalculatorImpl;
import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;
import com.codecool.marsexploration.configuration.service.MapConfigurationValidator;
import com.codecool.marsexploration.configuration.service.MapConfigurationValidatorImpl;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilder;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilderImpl;
import com.codecool.marsexploration.mapelements.service.generator.MapElementsGenerator;
import com.codecool.marsexploration.mapelements.service.generator.MapElementsGeneratorImpl;
import com.codecool.marsexploration.mapelements.service.generator.MapGenerator;
import com.codecool.marsexploration.mapelements.service.generator.MapGeneratorImpl;
import com.codecool.marsexploration.mapelements.service.placer.MapElementPlacer;
import com.codecool.marsexploration.mapelements.service.placer.MapElementPlacerImpl;

import java.util.List;

public class Application {
    // You can change this to any directory you like
    private static final String WorkDir = "src/main";

    public static void main(String[] args) {
        System.out.println("Mars Exploration Sprint 1");
        MapConfiguration mapConfig = getConfiguration();

        DimensionCalculator dimensionCalculator = new DimensionCalculatorImpl();
        CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl();

        MapElementBuilder mapElementBuilder = new MapElementBuilderImpl(dimensionCalculator);
        MapElementsGenerator mapElementsGenerator = new MapElementsGeneratorImpl(mapElementBuilder);

        MapConfigurationValidator mapConfigValidator = new MapConfigurationValidatorImpl();
        MapElementPlacer mapElementPlacer = new MapElementPlacerImpl();

        if (mapConfigValidator.validate(mapConfig)) {
            MapGenerator mapGenerator = new MapGeneratorImpl(mapElementsGenerator, mapElementPlacer, coordinateCalculator);
            System.out.println(mapGenerator.generate(mapConfig).toString());

            createAndWriteMaps(3, mapGenerator, mapConfig);

            System.out.println("Mars maps successfully generated.");
        }
    }

    private static void createAndWriteMaps(int count, MapGenerator mapGenerator, MapConfiguration mapConfig) {
    }

    private static MapConfiguration getConfiguration() {
        final String mountainSymbol = "#";
        final String pitSymbol = "&";
        final String mineralSymbol = "%";
        final String waterSymbol = "*";

        // Mountain configuration
        MapElementConfiguration mountainsCfg = new MapElementConfiguration(
                mountainSymbol,
                "mountain",
                List.of(
                        new ElementToSize(2, 20),
                        new ElementToSize(1, 30)
                ),
                3,
                ""
        );

        // Pit configuration
        MapElementConfiguration pitsCfg = new MapElementConfiguration(
                pitSymbol,
                "pit",
                List.of(
                        new ElementToSize(2, 10),
                        new ElementToSize(1, 20)
                ),
                10,
                ""
        );

        // Mineral configuration
        MapElementConfiguration mineralsCfg = new MapElementConfiguration(
                mineralSymbol,
                "mineral",
                List.of(
                        new ElementToSize(10, 1)
                ),
                0,
                mountainSymbol
        );

        // Water configuration
        MapElementConfiguration waterCfg = new MapElementConfiguration(
                waterSymbol,
                "water",
                List.of(
                        new ElementToSize(10, 1)
                ),
                0,
                pitSymbol
        );


        List<MapElementConfiguration> elementsCfg = List.of(mountainsCfg, pitsCfg, mineralsCfg, waterCfg);
        System.out.printf("Map configuration: %s\n", elementsCfg);
        MapConfiguration mapConfig = new MapConfiguration(17, 0.5, elementsCfg);
        MapConfigurationValidatorImpl validator = new MapConfigurationValidatorImpl();
        System.out.println("Validating map configuration..." + validator.validate(mapConfig));
        return mapConfig;
    }
}