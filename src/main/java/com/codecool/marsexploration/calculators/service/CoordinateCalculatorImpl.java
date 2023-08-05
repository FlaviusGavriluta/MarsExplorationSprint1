package com.codecool.marsexploration.calculators.service;

import com.codecool.marsexploration.calculators.model.Coordinate;

import java.util.*;

public class CoordinateCalculatorImpl implements CoordinateCalculator {
    private Random random = new Random();

    @Override
    public Coordinate getRandomCoordinate(int dimension) {
        int x = random.nextInt(dimension);
        int y = random.nextInt(dimension);
        return new Coordinate(x, y);
    }

    @Override
    public Iterable<Coordinate> getAdjacentCoordinates(Coordinate coordinate, int dimension) {
        List<Coordinate> adjacentCoordinates = new ArrayList<>();

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0) continue;
                int newX = coordinate.x() + x;
                int newY = coordinate.y() + y;
                if (newX >= 0 && newX < dimension && newY >= 0 && newY < dimension) {
                    adjacentCoordinates.add(new Coordinate(newX, newY));
                }
            }
        }
        return adjacentCoordinates;
    }

    @Override
    public Iterable<Coordinate> getAdjacentCoordinates(Iterable<Coordinate> coordinates, int dimension) {
        Set<Coordinate> adjacentCoordinates = new HashSet<>();
        for (Coordinate coordinate : coordinates) {
            for (Coordinate adjacentCoordinate : getAdjacentCoordinates(coordinate, dimension)) {
                adjacentCoordinates.add(adjacentCoordinate);
            }
        }
        return adjacentCoordinates;
    }
}