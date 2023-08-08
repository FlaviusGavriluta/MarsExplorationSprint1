package com.codecool.marsexploration.configuration.service;

import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapConfigurationValidatorImplTest {

    private MapConfigurationValidator validator;

    @BeforeEach
    void setUp() {
        validator = new MapConfigurationValidatorImpl();
    }

    @Test
    void testValidate_ValidConfiguration() {
        MapConfigurationValidator validator = new MapConfigurationValidatorImpl();
        MapConfiguration mapConfig = createValidConfiguration();
        assertTrue(validator.validate(mapConfig));
    }

//    @Test
//    void testValidate_InvalidDimensions() {
//        MapConfigurationValidator validator = new MapConfigurationValidatorImpl();
//        MapConfiguration mapConfig = createInvalidDimensionsConfiguration();
//        assertFalse(validator.validate(mapConfig));
//    }
//
//    @Test
//    void testValidate_ExceedsElementToSpaceRatio() {
//        MapConfigurationValidator validator = new MapConfigurationValidatorImpl();
//        MapConfiguration mapConfig = createExceedingElementToSpaceRatioConfiguration();
//        assertFalse(validator.validate(mapConfig));
//    }

    private MapConfiguration createValidConfiguration() {
        List<ElementToSize> mineralToSizes = List.of(new ElementToSize(5, 1));
        MapElementConfiguration mineral = new MapElementConfiguration("%", "mineral", mineralToSizes, 0, "#");
        List<MapElementConfiguration> mapElementConfigurations = List.of(mineral);
        return new MapConfiguration(10, 0.1, mapElementConfigurations);
    }

//    private MapConfiguration createInvalidDimensionsConfiguration() {
//        // Aici puteți crea o configurație cu dimensiuni nevalide
//        // ...
//        return new MapConfiguration(/*...*/);
//    }
//
//    private MapConfiguration createExceedingElementToSpaceRatioConfiguration() {
//        // Aici puteți crea o configurație care depășește ElementToSpaceRatio
//        // ...
//        return new MapConfiguration(/*...*/);
//    }

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
        MapConfiguration validMapConfig = new MapConfiguration(12, 0.5, elementsCfg);

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
        MapConfiguration invalidMapConfig = new MapConfiguration(15, 0.3, elementsCfg);

        assertFalse(validator.validate(invalidMapConfig));
    }

    @Test
    void testInvalidMapConfigurationMultiDimensionalMinerals() {
        // Create an invalid map configuration with multi-dimensional minerals
        MapElementConfiguration mineralsCfg = new MapElementConfiguration(
                "%",
                "mineral",
                List.of(
                        new ElementToSize(1, 2) // Invalid dimension size for mineral
                ),
                0,
                "#"
        );

        List<MapElementConfiguration> elementsCfg = List.of(mineralsCfg);
        MapConfiguration invalidMapConfig = new MapConfiguration(10, 0.5, elementsCfg);

        assertFalse(validator.validate(invalidMapConfig));
    }

    // add more tests here
    @Test
    void testInvalidMapConfigurationMultiDimensionalWater() {
        // Create an invalid map configuration with multi-dimensional water
        MapElementConfiguration mineralsCfg = new MapElementConfiguration(
                "*",
                "water",
                List.of(
                        new ElementToSize(1, 1)
                ),
                3, // Invalid dimension growth for water
                ""
        );

        List<MapElementConfiguration> elementsCfg = List.of(mineralsCfg);
        MapConfiguration invalidMapConfig = new MapConfiguration(10, 0.5, elementsCfg);

        assertFalse(validator.validate(invalidMapConfig));
    }

}
