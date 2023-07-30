package com.codecool.marsexploration.mapelements.service.placer;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.mapelements.model.MapElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MapElementPlacerImplTest {

    private MapElementPlacerImpl mapElementPlacer;

    @BeforeEach
    void setUp() {
        mapElementPlacer = new MapElementPlacerImpl();
    }

    @Test
    void testCanPlaceElement() {
        // Initialize the map array with non-null values
        String[][] map = new String[5][5];
        for (String[] strings : map) {
            Arrays.fill(strings, "");
        }

        // Create a sample MapElement and Coordinate for testing
        String[][] elementRepresentation = new String[][]{
                {"#", "#"},
                {"#", "#"}
        };
        MapElement element = new MapElement(elementRepresentation, "SampleElement", 2);
        Coordinate coordinate = new Coordinate(2, 2);

        // Test if the element can be placed on the map
        assertTrue(mapElementPlacer.canPlaceElement(element, map, coordinate));
    }

    @Test
    void testPlaceElement() {
        // Initialize the map array with non-null values
        String[][] map = new String[5][5];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = "";
            }
        }

        // Create a sample MapElement and Coordinate for testing
        String[][] elementRepresentation = new String[][]{
                {"#", "#"},
                {"#", "#"}
        };
        MapElement element = new MapElement(elementRepresentation, "SampleElement", 2);
        Coordinate coordinate = new Coordinate(2, 2);

        // Place the element on the map
        mapElementPlacer.placeElement(element, map, coordinate);

        // Test if the element was placed correctly on the map
        assertEquals("#", map[1][1]);
        assertEquals("#", map[1][2]);
        assertEquals("#", map[2][1]);
        assertEquals("#", map[2][2]);
    }
}
