package com.codecool.marsexploration.configuration.service;

import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapConfigurationValidatorImplTest {

    private MapConfigurationValidatorImpl validator;

    @BeforeEach
    void setUp() {
        validator = new MapConfigurationValidatorImpl();
    }

    @Test
    void testValidMapConfiguration() {
        final String mountainSymbol = "#";
        final String pitSymbol = "&";
        final String mineralSymbol = "%";
        final String waterSymbol = "*";

        MapElementConfiguration mountainsCfg = createMapElementConfiguration(mountainSymbol,
                "mountain",
                List.of(new ElementToSize(2, 20), new ElementToSize(1, 30)),
                3,
                "");

        MapElementConfiguration pitsCfg = createMapElementConfiguration(pitSymbol,
                "pit",
                List.of(new ElementToSize(2, 10), new ElementToSize(1, 20)),
                10,
                "");

        MapElementConfiguration mineralsCfg = createMapElementConfiguration(mineralSymbol,
                "mineral",
                List.of(new ElementToSize(10, 1)),
                0,
                mountainSymbol);

        MapElementConfiguration watersCfg = createMapElementConfiguration(waterSymbol,
                "water",
                List.of(new ElementToSize(10, 1)),
                0,
                pitSymbol);

        List<MapElementConfiguration> elementsCfg = List.of(mountainsCfg, pitsCfg, mineralsCfg, watersCfg);

        MapConfiguration mapConfig = new MapConfiguration(1000, 0.5, elementsCfg);
        assertTrue(validator.validate(mapConfig));
    }

    @Test
    void testInvalidMapConfiguration() {
        final String mountainSymbol = "#";
        final String pitSymbol = "&";
        final String mineralSymbol = "%";
        final String waterSymbol = "*";

        MapElementConfiguration mountainsCfg = createMapElementConfiguration(mountainSymbol,
                "mountain",
                List.of(new ElementToSize(2, 20), new ElementToSize(1, 30)),
                5,
                "");

        MapElementConfiguration pitsCfg = createMapElementConfiguration(pitSymbol,
                "pit",
                List.of(new ElementToSize(2, 10), new ElementToSize(1, 20)),
                10,
                "");

        MapElementConfiguration mineralsCfg = createMapElementConfiguration(mineralSymbol,
                "mineral",
                List.of(new ElementToSize(10, 1)),
                0,
                mountainSymbol);

        MapElementConfiguration watersCfg = createMapElementConfiguration(waterSymbol,
                "water",
                List.of(new ElementToSize(10, 1)),
                0,
                pitSymbol);

        List<MapElementConfiguration> elementsCfg = List.of(mountainsCfg, pitsCfg, mineralsCfg, watersCfg);

        MapConfiguration mapConfig = new MapConfiguration(1000, 0.5, elementsCfg);
        assertFalse(validator.validate(mapConfig));
    }

    @Test
    void testValidElementToSpaceRatio() {
        final String mountainSymbol = "#";
        final String pitSymbol = "&";
        final String mineralSymbol = "%";
        final String waterSymbol = "*";

        MapElementConfiguration mountainsCfg = createMapElementConfiguration(mountainSymbol,
                "mountain",
                List.of(new ElementToSize(2, 20), new ElementToSize(1, 30)),
                3,
                "");

        MapElementConfiguration pitsCfg = createMapElementConfiguration(pitSymbol,
                "pit",
                List.of(new ElementToSize(2, 10), new ElementToSize(1, 20)),
                10,
                "");

        MapElementConfiguration mineralsCfg = createMapElementConfiguration(mineralSymbol,
                "mineral",
                List.of(new ElementToSize(10, 1)),
                0,
                mountainSymbol);

        MapElementConfiguration watersCfg = createMapElementConfiguration(waterSymbol,
                "water",
                List.of(new ElementToSize(10, 1)),
                0,
                pitSymbol);

        List<MapElementConfiguration> elementsCfg = List.of(mountainsCfg, pitsCfg, mineralsCfg, watersCfg);

        MapConfiguration mapConfig = new MapConfiguration(1000, 0.5, elementsCfg);
        assertTrue(validator.validate(mapConfig));
    }

    @Test
    void testInvalidElementToSpaceRatio() {
        final String mountainSymbol = "#";
        final String pitSymbol = "&";
        final String mineralSymbol = "%";
        final String waterSymbol = "*";

        MapElementConfiguration mountainsCfg = createMapElementConfiguration(mountainSymbol,
                "mountain",
                List.of(new ElementToSize(2, 20), new ElementToSize(1, 30)),
                3,
                "");

        MapElementConfiguration pitsCfg = createMapElementConfiguration(pitSymbol,
                "pit",
                List.of(new ElementToSize(2, 10), new ElementToSize(1, 20)),
                10,
                "");

        MapElementConfiguration mineralsCfg = createMapElementConfiguration(mineralSymbol,
                "mineral",
                List.of(new ElementToSize(10, 1)),
                0,
                mountainSymbol);

        MapElementConfiguration watersCfg = createMapElementConfiguration(waterSymbol,
                "water",
                List.of(new ElementToSize(10, 1)),
                0,
                pitSymbol);

        List<MapElementConfiguration> elementsCfg = List.of(mountainsCfg, pitsCfg, mineralsCfg, watersCfg);

        MapConfiguration mapConfig = new MapConfiguration(3, 0.5, elementsCfg);
        assertFalse(validator.validate(mapConfig));
    }

    private static MapElementConfiguration createMapElementConfiguration(String symbol,
                                                                         String name,
                                                                         List<ElementToSize> elementToSizes,
                                                                         int dimensionGrowth,
                                                                         String preferredLocationSymbol) {
        return new MapElementConfiguration(symbol, name, elementToSizes, dimensionGrowth, preferredLocationSymbol);
    }
}
