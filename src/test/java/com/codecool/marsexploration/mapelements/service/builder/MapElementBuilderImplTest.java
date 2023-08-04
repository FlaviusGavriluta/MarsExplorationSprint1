package com.codecool.marsexploration.mapelements.service.builder;

import com.codecool.marsexploration.calculators.service.DimensionCalculator;
import com.codecool.marsexploration.calculators.service.DimensionCalculatorImpl;
import com.codecool.marsexploration.mapelements.model.MapElement;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilder;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapElementBuilderImplTest {
    private MapElementBuilder mapElementBuilder;
    private DimensionCalculator dimensionCalculator;

    @BeforeEach
    void setUp() {
        dimensionCalculator = new DimensionCalculatorImpl();
        mapElementBuilder = new MapElementBuilderImpl(dimensionCalculator);
    }

    @Test
    void testBuildOneDimensionalElement() {
        int size = 1;
        String symbol = "%";
        String name = "minerals";
        int dimensionGrowth = 0;
        String preferredLocationSymbol = "mountains";

        MapElement element = mapElementBuilder.build(size, symbol, name, dimensionGrowth, preferredLocationSymbol);

        assertNotNull(element);
        assertEquals(dimensionCalculator.calculateDimension(1,0), element.getDimension());
        assertEquals(symbol, element.getRepresentation()[0][0]);
    }

    @Test
    void testBuildMultiDimensionalElement() {
        int size = 20;
        String symbol = "&";
        String name = "pit";
        int dimensionGrowth = 10;
        String preferredLocationSymbol = "";

        MapElement element = mapElementBuilder.build(size, symbol, name, dimensionGrowth, preferredLocationSymbol);

        assertNotNull(element);
        assertEquals(dimensionCalculator.calculateDimension(size, dimensionGrowth), element.getDimension());
        assertEquals(symbol, element.getRepresentation()[0][0]);
    }

    @Test
    void testBuildElementWithPreferredLocation() {
        int size = 5;
        String symbol = "@";
        String name = "water";
        int dimensionGrowth = 2;
        String preferredLocationSymbol = "#";

        MapElement element = mapElementBuilder.build(size, symbol, name, dimensionGrowth, preferredLocationSymbol);

        assertNotNull(element);
        assertEquals(dimensionCalculator.calculateDimension(size, dimensionGrowth), element.getDimension());
    }

    @Test
    void testBuildElementWithZeroSize() {
        int size = 0;
        String symbol = "X";
        String name = "test";
        int dimensionGrowth = 1;
        String preferredLocationSymbol = "";

        MapElement element = mapElementBuilder.build(size, symbol, name, dimensionGrowth, preferredLocationSymbol);

        assertNotNull(element);
        assertEquals(1, element.getDimension());
        assertEquals(symbol, element.getRepresentation()[0][0]);
    }

    @Test
    void testBuildElementWithNegativeDimensionGrowth() {
        int size = 5;
        String symbol = "*";
        String name = "test";
        int dimensionGrowth = -2;
        String preferredLocationSymbol = "";

        MapElement element = mapElementBuilder.build(size, symbol, name, dimensionGrowth, preferredLocationSymbol);

        assertNotNull(element);
        assertEquals(5, element.getDimension());
        assertEquals(symbol, element.getRepresentation()[0][0]);
    }
    // Add more tests to cover edge cases and other scenarios...
}
