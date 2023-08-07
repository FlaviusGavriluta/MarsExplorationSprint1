package com.codecool.marsexploration.mapelements.service.placer;

import static org.junit.jupiter.api.Assertions.*;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.mapelements.model.MapElement;
import org.junit.jupiter.api.Test;

public class MapElementPlacerImplTest {

    @Test
    public void testCanPlaceElement() {
        String[][] map = new String[5][5];
        MapElement element = new MapElement(new String[][]{{"X"}}, "Test", 1);
        Coordinate coordinate = new Coordinate(2, 2);

        MapElementPlacerImpl placer = new MapElementPlacerImpl();
        assertTrue(placer.canPlaceElement(element, map, coordinate));

        map[2][2] = "Y";
        assertFalse(placer.canPlaceElement(element, map, coordinate));
    }

    @Test
    public void testPlaceElement() {
        String[][] map = new String[5][5];
        MapElement element = new MapElement(new String[][]{{"X"}}, "Test", 1);
        Coordinate coordinate = new Coordinate(2, 2);

        MapElementPlacerImpl placer = new MapElementPlacerImpl();
        placer.placeElement(element, map, coordinate);

        assertEquals("X", map[2][2]);
        // ... additional assertions for multi-dimensional elements
    }
}
