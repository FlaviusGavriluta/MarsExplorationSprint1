package com.codecool.marsexploration.mapelements.service.generator;

import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;
import com.codecool.marsexploration.mapelements.model.MapElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapElementsGeneratorImplTest {

    private MapElementsGeneratorImpl mapElementsGenerator;

    @BeforeEach
    void setUp() {
        mapElementsGenerator = new MapElementsGeneratorImpl();
    }

    @Test
    void testCreateAll() {
        int mapSize = 1000;
        double elementToSpaceRatio = 0.5;

        List<MapElementConfiguration> elementConfigurations = new ArrayList<>();
        elementConfigurations.add(new MapElementConfiguration(
                "#",
                "mountain",
                List.of(
                        new ElementToSize(2, 20),
                        new ElementToSize(1, 30)
                ),
                3,
                ""
        ));
        elementConfigurations.add(new MapElementConfiguration(
                "&",
                "pit",
                List.of(
                        new ElementToSize(2, 10),
                        new ElementToSize(1, 20)
                ),
                10,
                ""
        ));
        elementConfigurations.add(new MapElementConfiguration(
                "%",
                "mineral",
                List.of(
                        new ElementToSize(10, 1)
                ),
                0,
                "#"
        ));
        elementConfigurations.add(new MapElementConfiguration(
                "*",
                "water",
                List.of(
                        new ElementToSize(10, 1)
                ),
                0,
                "&"
        ));

        MapConfiguration mapConfig = new MapConfiguration(mapSize, elementToSpaceRatio, elementConfigurations);

        Iterable<MapElement> mapElements = mapElementsGenerator.createAll(mapConfig);

        assertNotNull(mapElements);

        int count = 0;
        for (MapElement element : mapElements) {
            assertNotNull(element);
            int dimension = element.getDimension();
            assertTrue(dimension >= 1 && dimension <= mapSize);
            count++;
        }

        assertEquals(elementConfigurations.size(), count);
    }
}
