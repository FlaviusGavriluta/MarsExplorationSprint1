package com.codecool.marsexploration.mapelements.service.placer;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.mapelements.model.MapElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapElementPlacerImplTest {

    private MapElementPlacer mapElementPlacer;
    private String[][] map;

    @BeforeEach
    void setUp() {
        mapElementPlacer = new MapElementPlacerImpl();
        map = new String[][] {
                {" ", " ", " "},
                {" ", " ", " "},
                {" ", " ", " "}
        };
    }

    @Test
    void canPlaceElement_True() {
        MapElement element = new MapElement(new String[][] {{"#"}}, "test", 1);
        Coordinate coordinate = new Coordinate(1, 1);
        assertTrue(mapElementPlacer.canPlaceElement(element, map, coordinate));
    }

    @Test
    void canPlaceElement_False_OutOfBounds() {
        MapElement element = new MapElement(new String[][] {{"#"}, {"#"}}, "test", 2);
        Coordinate coordinate = new Coordinate(2, 2);
        assertFalse(mapElementPlacer.canPlaceElement(element, map, coordinate));
    }

    @Test
    void canPlaceElement_False_OccupiedSpace() {
        map[1][1] = "*";
        MapElement element = new MapElement(new String[][] {{"#"}}, "test", 1);
        Coordinate coordinate = new Coordinate(1, 1);
        assertFalse(mapElementPlacer.canPlaceElement(element, map, coordinate));
    }

    @Test
    void placeElement() {
        MapElement element = new MapElement(new String[][] {{"#"}}, "test", 1);
        Coordinate coordinate = new Coordinate(1, 1);
        mapElementPlacer.placeElement(element, map, coordinate);
        assertEquals("#", map[1][1]);
    }
}



//package com.codecool.marsexploration.mapelements.service.placer;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import com.codecool.marsexploration.calculators.model.Coordinate;
//import com.codecool.marsexploration.mapelements.model.MapElement;
//import org.junit.jupiter.api.Test;
//
//public class MapElementPlacerImplTest {
//
//    @Test
//    public void testCanPlaceElement() {
//        String[][] map = new String[5][5];
//        MapElement element = new MapElement(new String[][]{{"X"}}, "Test", 1);
//        Coordinate coordinate = new Coordinate(2, 2);
//
//        MapElementPlacerImpl placer = new MapElementPlacerImpl();
//        assertTrue(placer.canPlaceElement(element, map, coordinate));
//
//        map[2][2] = "Y";
//        assertFalse(placer.canPlaceElement(element, map, coordinate));
//    }
//
//    @Test
//    public void testPlaceElement() {
//        String[][] map = new String[5][5];
//        MapElement element = new MapElement(new String[][]{{"X"}}, "Test", 1);
//        Coordinate coordinate = new Coordinate(2, 2);
//
//        MapElementPlacerImpl placer = new MapElementPlacerImpl();
//        placer.placeElement(element, map, coordinate);
//
//        assertEquals("X", map[2][2]);
//        // ... additional assertions for multi-dimensional elements
//    }
//}
