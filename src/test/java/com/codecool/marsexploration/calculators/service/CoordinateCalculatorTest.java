package com.codecool.marsexploration.calculators.service;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.calculators.service.CoordinateCalculatorImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class CoordinateCalculatorTest {
    private final CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl();

    @Test
    public void testGetRandomCoordinateWithinDimension() {
        int dimension = 10;

        for (int i = 0; i < 100; i++) {
            Coordinate coordinate = coordinateCalculator.getRandomCoordinate(dimension);
            Assertions.assertTrue(coordinate.getX() >= 0 && coordinate.getX() < dimension);
            Assertions.assertTrue(coordinate.getY() >= 0 && coordinate.getY() < dimension);
        }
    }

    @Test
    public void testGetAdjacentCoordinates() {
        int dimension = 5;
        Coordinate coordinate = new Coordinate(2, 2);
        Set<Coordinate> expectedAdjacentCoordinates = Set.of(
                new Coordinate(1, 2),
                new Coordinate(3, 2),
                new Coordinate(2, 1),
                new Coordinate(2, 3)
        );

        Iterable<Coordinate> adjacentCoordinates = coordinateCalculator.getAdjacentCoordinates(coordinate, dimension);
        Set<Coordinate> actualAdjacentCoordinates = new HashSet<>();
        adjacentCoordinates.forEach(actualAdjacentCoordinates::add);

        Assertions.assertEquals(expectedAdjacentCoordinates, actualAdjacentCoordinates);
    }
}
