package com.codecool.marsexploration;

import com.codecool.marsexploration.calculators.service.*;
import com.codecool.marsexploration.configuration.model.*;
import com.codecool.marsexploration.configuration.service.*;
import com.codecool.marsexploration.mapelements.model.Map;
import com.codecool.marsexploration.mapelements.service.generator.*;
import com.codecool.marsexploration.output.service.MapFileWriter;
import com.codecool.marsexploration.output.service.MapFileWriterImpl;

import java.io.File;
import java.util.List;

public class Application {
    private static final String WORK_DIR = "src/main";

    public static void main(String[] args) {
        System.out.println("Mars Exploration Sprint 1");
        MapConfiguration mapConfig = getConfiguration();

        CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl();

        MapConfigurationValidator mapConfigValidator = new MapConfigurationValidatorImpl();
        System.out.println("Map is valid: " + mapConfigValidator.validate(mapConfig));

        MapGenerator mapGenerator = new MapGeneratorImpl(coordinateCalculator);

        createAndWriteMaps(3, mapGenerator, mapConfig);

        System.out.println("Mars maps successfully generated.");
    }

    private static void createAndWriteMaps(int count, MapGenerator mapGenerator, MapConfiguration mapConfig) {
        File mapDir = new File(WORK_DIR + "/resources/maps");
        if (!mapDir.exists()) {
            mapDir.mkdir();
        }

        for (int i = 1; i <= count; i++) {
            Map map = mapGenerator.generate(mapConfig);
            String filename = "map_" + i + ".map";
            writeMapToFile(mapDir.getAbsolutePath() + "/" + filename, map);
        }
    }

    private static void writeMapToFile(String filename, Map map) {
        MapFileWriter mapFileWriter = new MapFileWriterImpl();
        mapFileWriter.writeMapFile(map, filename);
    }

    private static MapConfiguration getConfiguration() {
        final String mountainSymbol = "#";
        final String pitSymbol = "&";
        final String mineralSymbol = "%";
        final String waterSymbol = "*";

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

        MapElementConfiguration mineralsCfg = new MapElementConfiguration(
                mineralSymbol,
                "mineral",
                List.of(
                        new ElementToSize(10, 1)
                ),
                0,
                mountainSymbol
        );

        MapElementConfiguration watersCfg = new MapElementConfiguration(
                waterSymbol,
                "water",
                List.of(
                        new ElementToSize(10, 1)
                ),
                0,
                pitSymbol
        );

        List<MapElementConfiguration> elementsCfg = List.of(mountainsCfg, pitsCfg, mineralsCfg, watersCfg);
        return new MapConfiguration(50, 0.5, elementsCfg);
    }
}