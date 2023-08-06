package com.codecool.marsexploration.calculators.service;

import com.codecool.marsexploration.calculators.model.Coordinate;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CoordinateCalculatorImplTest {
    private CoordinateCalculatorImpl calculator = new CoordinateCalculatorImpl();
    private int dimension = 5; // dimension of the grid

    @Test
    public void testGetRandomCoordinate() {
        Coordinate c = calculator.getRandomCoordinate(dimension);
        assertTrue(c.x() >= 0 && c.x() < dimension);
        assertTrue(c.y() >= 0 && c.y() < dimension);
    }

    @Test
    public void testGetAdjacentCoordinatesSingle() {
        Coordinate c = new Coordinate(2, 2);
        Iterable<Coordinate> adjacent = calculator.getAdjacentCoordinates(c, dimension);
        List<Coordinate> expected = Arrays.asList(
                new Coordinate(1, 1), new Coordinate(1, 2), new Coordinate(1, 3),
                new Coordinate(2, 1), /* omit (2,2) as it is the input coord */ new Coordinate(2, 3),
                new Coordinate(3, 1), new Coordinate(3, 2), new Coordinate(3, 3)
        );
        assertTrue(containsSameElements(expected, adjacent));
    }

    @Test
    public void testGetAdjacentCoordinatesMultiple() {
        List<Coordinate> coords = Arrays.asList(new Coordinate(2, 2), new Coordinate(0, 0));
        Iterable<Coordinate> adjacent = calculator.getAdjacentCoordinates(coords, dimension);
        List<Coordinate> expected = Arrays.asList(
                // For (2,2)
                new Coordinate(1, 1), new Coordinate(1, 2), new Coordinate(1, 3),
                new Coordinate(2, 1), /* omit (2,2) as it is the input coord */ new Coordinate(2, 3),
                new Coordinate(3, 1), new Coordinate(3, 2), new Coordinate(3, 3),
                // For (0,0)
                /* omit (0,0) as it is the input coord */ new Coordinate(0, 1),
                new Coordinate(1, 0), new Coordinate(1, 1)
        );
        assertTrue(containsSameElements(expected, adjacent));
    }

    private boolean containsSameElements(Collection<Coordinate> col1, Iterable<Coordinate> col2) {
        Set<Coordinate> set1 = new HashSet<>(col1);
        Set<Coordinate> set2 = new HashSet<>();
        for (Coordinate c : col2) {
            set2.add(c);
        }
        return set1.equals(set2);
    }
}