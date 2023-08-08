package com.codecool.marsexploration.mapelements.service.placer;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.calculators.service.CoordinateCalculatorImpl;
import com.codecool.marsexploration.mapelements.model.MapElement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapElementPlacerImplTest {

    @Test
    void testCanPlaceElement() {
        CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl();
        // Initialize the map array with non-null values
        String[][] map = new String[5][5];
        for (String[] strings : map) {
            for (int j = 0; j < map[0].length; j++) {
                strings[j] = "";
            }
        }

        // Create a sample MapElement and Coordinate for testing
        String[][] elementRepresentation = new String[][]{
                {"#", "#"},
                {"#", "#"}
        };
        MapElement element = new MapElement(elementRepresentation, "SampleElement", 2);
        Coordinate coordinate = new Coordinate(2, 2);

        // Test if the element can be placed on the map
        MapElementPlacer mapElementPlacer = new MapElementPlacerImpl(coordinateCalculator);
        assertTrue(mapElementPlacer.canPlaceElement(element, map, coordinate));
    }

    @Test
    void testCannotPlaceElement() {
        CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl();
        // Initialize the map array with non-null values and place an element
        String[][] map = new String[5][5];
        for (String[] strings : map) {
            for (int j = 0; j < map[0].length; j++) {
                strings[j] = "";
            }
        }
        map[2][2] = "#";
        map[2][3] = "#";
        map[3][2] = "#";
        map[3][3] = "#";

        // Create a sample MapElement and Coordinate for testing
        String[][] elementRepresentation = new String[][]{
                {"#", "#"},
                {"#", "#"}
        };
        MapElement element = new MapElement(elementRepresentation, "SampleElement", 2);
        Coordinate coordinate = new Coordinate(2, 2);

        // Test if the element cannot be placed on the map due to overlapping with an existing element
        MapElementPlacer mapElementPlacer = new MapElementPlacerImpl(coordinateCalculator);
        assertFalse(mapElementPlacer.canPlaceElement(element, map, coordinate));
    }
}
