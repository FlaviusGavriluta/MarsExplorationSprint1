package com.codecool.test.configuration.service;

import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;
import com.codecool.marsexploration.configuration.service.MapConfigurationValidator;
import com.codecool.marsexploration.configuration.service.MapConfigurationValidatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapConfigurationValidatorImplTest {

    private MapConfigurationValidator validator;

    @BeforeEach
    void setUp() {
        validator = new MapConfigurationValidatorImpl();
    }

    @Test
    void testValidMapConfiguration() {
        // Create a valid map configuration with 50% ElementToSpaceRatio
        MapElementConfiguration mountainsCfg = new MapElementConfiguration(
                "#",
                "mountain",
                List.of(
                        new ElementToSize(2, 20),
                        new ElementToSize(1, 30)
                ),
                3,
                ""
        );

        List<MapElementConfiguration> elementsCfg = List.of(mountainsCfg);
        MapConfiguration validMapConfig = new MapConfiguration(100, 0.5, elementsCfg);

        assertTrue(validator.validate(validMapConfig));
    }

    @Test
    void testInvalidMapConfigurationExceedingElementToSpaceRatio() {
        // Create an invalid map configuration with more elements than allowed by ElementToSpaceRatio
        MapElementConfiguration mountainsCfg = new MapElementConfiguration(
                "#",
                "mountain",
                List.of(
                        new ElementToSize(2, 20),
                        new ElementToSize(1, 30)
                ),
                3,
                ""
        );

        List<MapElementConfiguration> elementsCfg = List.of(mountainsCfg);
        MapConfiguration invalidMapConfig = new MapConfiguration(5, 0.3, elementsCfg);

        assertFalse(validator.validate(invalidMapConfig));
    }

    @Test
    void testInvalidMapConfigurationMultiDimensionalMinerals() {
        // Create an invalid map configuration with multi-dimensional minerals
        MapElementConfiguration mineralsCfg = new MapElementConfiguration(
                "%",
                "mineral",
                List.of(
                        new ElementToSize(1, 10)
                ),
                3, // Invalid dimension growth for minerals
                ""
        );

        List<MapElementConfiguration> elementsCfg = List.of(mineralsCfg);
        MapConfiguration invalidMapConfig = new MapConfiguration(10, 0.5, elementsCfg);

        assertFalse(validator.validate(invalidMapConfig));
    }

    // Add more test cases for other potential invalid configurations here if needed
}
