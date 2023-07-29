package com.codecool.marsexploration.mapelements.service.builder;

import com.codecool.marsexploration.mapelements.model.MapElement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapElementBuilderImplTest {
    @Test
    void testBuildMountain() {
        MapElementBuilderImpl mapElementBuilder = new MapElementBuilderImpl();

        int size = 20;
        String symbol = "#";
        String name = "Mountain";
        int dimensionGrowth = 3;
        String preferredLocationSymbol = "";

        MapElement mapElement = mapElementBuilder.build(size, symbol, name, dimensionGrowth, preferredLocationSymbol);

        int expectedDimension = 8; // dimension = ceil(sqrt(size)) + dimensionGrowth = ceil(sqrt(20)) + 3 = 8

        assertTrue(mapElement.toString().contains(symbol));
        assertEquals(name, mapElement.getName());
        assertEquals(expectedDimension, mapElement.getDimension());
        assertEquals(preferredLocationSymbol, mapElement.getPreferredLocationSymbol());
    }

    @Test
    void testBuildPit() {
        MapElementBuilderImpl mapElementBuilder = new MapElementBuilderImpl();

        int size = 10;
        String symbol = "&";
        String name = "Pit";
        int dimensionGrowth = 10;
        String preferredLocationSymbol = "";

        MapElement mapElement = mapElementBuilder.build(size, symbol, name, dimensionGrowth, preferredLocationSymbol);

        int expectedDimension = 14; // dimension = ceil(sqrt(size)) + dimensionGrowth = ceil(sqrt(20)) + 3 = 8

        assertTrue(mapElement.toString().contains(symbol));
        assertEquals(name, mapElement.getName());
        assertEquals(expectedDimension, mapElement.getDimension());
        assertEquals(preferredLocationSymbol, mapElement.getPreferredLocationSymbol());
    }
}
