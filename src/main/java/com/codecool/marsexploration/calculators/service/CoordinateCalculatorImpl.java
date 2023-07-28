package com.codecool.marsexploration.calculators.service;

import com.codecool.marsexploration.calculators.model.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CoordinateCalculatorImpl implements CoordinateCalculator {
    private Random random;

    public CoordinateCalculatorImpl() {
        random = new Random();
    }

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

        if (x > 0) {
            adjacentCoordinates.add(new Coordinate(x - 1, y));
        }

        if (x < dimension - 1) {
            adjacentCoordinates.add(new Coordinate(x + 1, y));
        }

        if (y > 0) {
            adjacentCoordinates.add(new Coordinate(x, y - 1));
        }

        if (y < dimension - 1) {
            adjacentCoordinates.add(new Coordinate(x, y + 1));
        }

        return adjacentCoordinates;
    }

    @Override
    public Iterable<Coordinate> getAdjacentCoordinates(Iterable<Coordinate> coordinates, int dimension) {
        List<Coordinate> allAdjacentCoordinates = new ArrayList<>();

        for (Coordinate coordinate : coordinates) {
            allAdjacentCoordinates.addAll((List<Coordinate>) getAdjacentCoordinates(coordinate, dimension));
        }

        return allAdjacentCoordinates;
    }
}
