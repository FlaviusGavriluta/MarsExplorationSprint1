package com.codecool.marsexploration.calculators.service;

import com.codecool.marsexploration.calculators.model.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CoordinateCalculatorImpl implements CoordinateCalculator {
    private final Random random = new Random();

    @Override
    public Coordinate getRandomCoordinate(int dimension) {
        int x = random.nextInt(dimension);
        int y = random.nextInt(dimension);
        return new Coordinate(x, y);
    }

    @Override
    public Iterable<Coordinate> getAdjacentCoordinates(Coordinate coordinate, int dimension) {
        List<Coordinate> adjacentCoordinates = new ArrayList<>();

        int x = coordinate.x();
        int y = coordinate.y();

        // Top
        if (x - 1 >= 0) {
            adjacentCoordinates.add(new Coordinate(x - 1, y));
        }
        // Bottom
        if (x + 1 < dimension) {
            adjacentCoordinates.add(new Coordinate(x + 1, y));
        }
        // Left
        if (y - 1 >= 0) {
            adjacentCoordinates.add(new Coordinate(x, y - 1));
        }
        // Right
        if (y + 1 < dimension) {
            adjacentCoordinates.add(new Coordinate(x, y + 1));
        }

        return adjacentCoordinates;
    }

    @Override
    public Iterable<Coordinate> getAdjacentCoordinates(Iterable<Coordinate> coordinates, int dimension) {
        List<Coordinate> allAdjacentCoordinates = new ArrayList<>();
        for (Coordinate coordinate : coordinates) {
            Iterable<Coordinate> adjacentCoordinates = getAdjacentCoordinates(coordinate, dimension);
            allAdjacentCoordinates.addAll((List<Coordinate>) adjacentCoordinates);
        }
        return allAdjacentCoordinates;
    }
}