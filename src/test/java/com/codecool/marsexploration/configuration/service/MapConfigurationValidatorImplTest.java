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
    void shouldValidateValidConfiguration() {
        MapConfiguration mapConfig = createValidConfiguration();
        assertTrue(validator.validate(mapConfig));
    }

    @Test
    void shouldInvalidateWhenExceedingElementToSpaceRatio() {
        MapConfiguration mapConfig = createExceedingElementToSpaceRatioConfiguration();
        assertFalse(validator.validate(mapConfig));
    }

    @Test
    void shouldValidateConfigurationWithFiftyPercentElementToSpaceRatio() {
        MapElementConfiguration mountainsCfg = new MapElementConfiguration("#", "mountain", List.of(new ElementToSize(2, 20), new ElementToSize(1, 30)), 3, "");
        List<MapElementConfiguration> elementsCfg = List.of(mountainsCfg);
        MapConfiguration validMapConfig = new MapConfiguration(242, 0.5, elementsCfg);
        assertTrue(validator.validate(validMapConfig));
    }

    @Test
    void shouldInvalidateWhenExceedingElementToSpaceRatioInMountains() {
        MapElementConfiguration mountainsCfg = new MapElementConfiguration("#", "mountain", List.of(new ElementToSize(2, 20), new ElementToSize(1, 30)), 3, "");
        List<MapElementConfiguration> elementsCfg = List.of(mountainsCfg);
        MapConfiguration invalidMapConfig = new MapConfiguration(15, 0.3, elementsCfg);
        assertFalse(validator.validate(invalidMapConfig));
    }

    @Test
    void shouldInvalidateMultiDimensionalMinerals() {
        MapElementConfiguration mineralsCfg = new MapElementConfiguration("%", "mineral", List.of(new ElementToSize(1, 2) // Invalid dimension size for mineral
        ), 0, "#");
        List<MapElementConfiguration> elementsCfg = List.of(mineralsCfg);
        MapConfiguration invalidMapConfig = new MapConfiguration(10, 0.5, elementsCfg);
        assertFalse(validator.validate(invalidMapConfig));
    }

    @Test
    void shouldInvalidateMultiDimensionalWaterWithGrowth() {
        MapElementConfiguration mineralsCfg = new MapElementConfiguration("*", "water", List.of(new ElementToSize(1, 1)), 3, "");
        List<MapElementConfiguration> elementsCfg = List.of(mineralsCfg);
        MapConfiguration invalidMapConfig = new MapConfiguration(10, 0.5, elementsCfg);
        assertFalse(validator.validate(invalidMapConfig));
    }

    private MapConfiguration createValidConfiguration() {
        List<ElementToSize> mineralToSizes = List.of(new ElementToSize(5, 1));
        MapElementConfiguration mineral = new MapElementConfiguration("%", "mineral", mineralToSizes, 0, "#");
        List<MapElementConfiguration> mapElementConfigurations = List.of(mineral);
        return new MapConfiguration(50, 0.1, mapElementConfigurations);
    }

    private MapConfiguration createExceedingElementToSpaceRatioConfiguration() {
        // Aici se presupune că un raport de 0.75 depășește limita permisă
        List<ElementToSize> elementToSizes = List.of(new ElementToSize(15, 1));
        MapElementConfiguration mineral = new MapElementConfiguration("%", "mineral", elementToSizes, 0, "#");
        List<MapElementConfiguration> mapElementConfigurations = List.of(mineral);
        return new MapConfiguration(10, 0.1, mapElementConfigurations);
    }
}
