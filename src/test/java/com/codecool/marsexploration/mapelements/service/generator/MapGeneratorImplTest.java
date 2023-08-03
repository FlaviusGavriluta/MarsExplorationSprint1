package com.codecool.marsexploration.mapelements.service.generator;

import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;
import com.codecool.marsexploration.mapelements.model.Map;
import com.codecool.marsexploration.mapelements.service.placer.MapElementPlacerImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class MapGeneratorImplTest {

    @Test
    void testGenerate() {
        MapElementPlacerImpl mapElementPlacer = new MapElementPlacerImpl();
        MapGeneratorImpl mapGenerator = new MapGeneratorImpl(mapElementPlacer);

        MapConfiguration mapConfig = createSampleMapConfiguration();
        Map map = mapGenerator.generate(mapConfig);

        assertNotNull(map);
    }

    private MapConfiguration createSampleMapConfiguration() {
        // Create a sample MapConfiguration for testing
        // Customize this configuration as needed for testing
        int mapSize = 5;
        double elementToSpaceRatio = 0.2;

        MapElementConfiguration elementConfig = new MapElementConfiguration(
                "#", "SampleElement", List.of(new ElementToSize(1, 2)), 1, " "
        );
        List<MapElementConfiguration> elementConfigs = List.of(elementConfig);

        return new MapConfiguration(mapSize, elementToSpaceRatio, elementConfigs);
    }
}
