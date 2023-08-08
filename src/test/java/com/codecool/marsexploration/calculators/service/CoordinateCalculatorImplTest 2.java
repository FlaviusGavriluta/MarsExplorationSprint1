package com.codecool.marsexploration.calculators.service;

import com.codecool.marsexploration.calculators.model.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CoordinateCalculatorImplTest {

    private CoordinateCalculatorImpl coordinateCalculator;

    @BeforeEach
    void setUp() {
        coordinateCalculator = new CoordinateCalculatorImpl();
    }

    @Test
    void testGetRandomCoordinate() {
        int dimension = 10;
        Coordinate randomCoordinate = coordinateCalculator.getRandomCoordinate(dimension);
        assertTrue(randomCoordinate.getX() >= 0 && randomCoordinate.getX() < dimension);
        assertTrue(randomCoordinate.getY() >= 0 && randomCoordinate.getY() < dimension);
    }

    @Test
    void testGetAdjacentCoordinates() {
        int dimension = 5;
        Coordinate centerCoordinate = new Coordinate(2, 2);
        Iterable<Coordinate> adjacentCoordinates = coordinateCalculator.getAdjacentCoordinates(centerCoordinate, dimension);

        Set<Coordinate> expectedAdjacent = new HashSet<>();
        expectedAdjacent.add(new Coordinate(1, 2));
        expectedAdjacent.add(new Coordinate(3, 2));
        expectedAdjacent.add(new Coordinate(2, 1));
        expectedAdjacent.add(new Coordinate(2, 3));

        Set<Coordinate> actualAdjacent = new HashSet<>();
        adjacentCoordinates.forEach(actualAdjacent::add);

        assertEquals(expectedAdjacent, actualAdjacent);
    }

    @Test
    void testGetAdjacentCoordinatesWithMultipleCoordinates() {
        int dimension = 5;
        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(0, 1));
        coordinates.add(new Coordinate(3, 4));

        Iterable<Coordinate> adjacentCoordinates = coordinateCalculator.getAdjacentCoordinates(coordinates, dimension);

        Set<Coordinate> expectedAdjacent = new HashSet<>();
        expectedAdjacent.add(new Coordinate(0, 0));
        expectedAdjacent.add(new Coordinate(0, 2));
        expectedAdjacent.add(new Coordinate(1, 1));
        expectedAdjacent.add(new Coordinate(2, 4));
        expectedAdjacent.add(new Coordinate(3, 3));
        expectedAdjacent.add(new Coordinate(4, 4));

        List<Coordinate> expectedList = new ArrayList<>(expectedAdjacent);
        List<Coordinate> actualList = new ArrayList<>();
        adjacentCoordinates.forEach(actualList::add);

        // Sort the lists before comparison
        expectedList.sort(Comparator.comparing(Coordinate::getX).thenComparing(Coordinate::getY));
        actualList.sort(Comparator.comparing(Coordinate::getX).thenComparing(Coordinate::getY));

        assertEquals(expectedList, actualList);
    }

}
