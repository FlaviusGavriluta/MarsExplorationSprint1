package com.codecool.marsexploration.calculators.service;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.CoordinateCalculatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

        assertNotNull(randomCoordinate);
        assertTrue(randomCoordinate.x() >= 0 && randomCoordinate.x() < dimension);
        assertTrue(randomCoordinate.y() >= 0 && randomCoordinate.y() < dimension);
    }

    @Test
    void testGetAdjacentCoordinatesWithValidCoordinate() {
        int dimension = 10;
        Coordinate coordinate = new Coordinate(5, 5);
        Iterable<Coordinate> adjacentCoordinates = coordinateCalculator.getAdjacentCoordinates(coordinate, dimension);

        assertNotNull(adjacentCoordinates);

        for (Coordinate adjCoord : adjacentCoordinates) {
            assertTrue(adjCoord.x() >= 0 && adjCoord.x() < dimension);
            assertTrue(adjCoord.y() >= 0 && adjCoord.y() < dimension);
        }
    }

    @Test
    void testGetAdjacentCoordinatesWithInvalidCoordinate() {
        int dimension = 10;
        Coordinate coordinate = new Coordinate(-1, -1);
        Iterable<Coordinate> adjacentCoordinates = coordinateCalculator.getAdjacentCoordinates(coordinate, dimension);

        assertTrue(adjacentCoordinates.iterator().hasNext());
        for (Coordinate adjCoord : adjacentCoordinates) {
            assertTrue(adjCoord.x() >= 0 && adjCoord.x() < dimension);
            assertTrue(adjCoord.y() >= 0 && adjCoord.y() < dimension);
        }
    }

    @Test
    void testGetAdjacentCoordinatesWithEdgeCoordinate() {
        int dimension = 10;
        Coordinate coordinate = new Coordinate(dimension - 1, dimension - 1);
        Iterable<Coordinate> adjacentCoordinates = coordinateCalculator.getAdjacentCoordinates(coordinate, dimension);

        assertNotNull(adjacentCoordinates);

        for (Coordinate adjCoord : adjacentCoordinates) {
            assertTrue(adjCoord.x() >= 0 && adjCoord.x() < dimension);
            assertTrue(adjCoord.y() >= 0 && adjCoord.y() < dimension);
        }
    }

    @Test
    void testGetAllAdjacentCoordinates() {
        int dimension = 10;
        Coordinate coordinate1 = new Coordinate(5, 5);
        Coordinate coordinate2 = new Coordinate(7, 8);
        Iterable<Coordinate> coordinates = List.of(coordinate1, coordinate2);
        Iterable<Coordinate> allAdjacentCoordinates = coordinateCalculator.getAdjacentCoordinates(coordinates, dimension);

        assertNotNull(allAdjacentCoordinates);

        for (Coordinate adjCoord : allAdjacentCoordinates) {
            assertTrue(adjCoord.x() >= 0 && adjCoord.x() < dimension);
            assertTrue(adjCoord.y() >= 0 && adjCoord.y() < dimension);
        }
    }
}
