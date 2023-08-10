package com.codecool.marsexploration.mapelements.service.builder;

import com.codecool.marsexploration.calculators.service.DimensionCalculator;
import com.codecool.marsexploration.calculators.service.DimensionCalculatorImpl;
import com.codecool.marsexploration.mapelements.model.MapElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapElementBuilderImplTest {
    private MapElementBuilder mapElementBuilder;

    @BeforeEach
    void setUp() {
        DimensionCalculator dimensionCalculator = new DimensionCalculatorImpl();
        mapElementBuilder = new MapElementBuilderImpl(dimensionCalculator);
    }

    @Test
    void testBuildElementWithCorrectDimension() {
        MapElement element = mapElementBuilder.build(16, "*", "random", 3, "");
        assertEquals(7, element.getDimension());
    }

    @Test
    void testBuildSingleDimensionElement() {
        MapElement element = mapElementBuilder.build(1, "%", "minerals", 0, "#");
        assertFalse(element.isMultidimensional());
    }

    @Test
    void testBuildMultiDimensionElement() {
        MapElement element = mapElementBuilder.build(20, "&", "pit", 10, "");
        assertTrue(element.isMultidimensional());
    }

    @Test
    void testBuildElementWithPreferredLocation() {
        MapElement element = mapElementBuilder.build(5, "*", "water", 0, "&");
        assertEquals("&", element.getPreferredLocationSymbol());
    }
}
