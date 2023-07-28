package com.codecool.marsexploration.calculators.service;

import com.codecool.marsexploration.calculators.model.Coordinate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class CoordinateCalculatorImpl implements CoordinateCalculator {
    @Override
    public Coordinate getRandomCoordinate(int dimension) {
        Random random = new Random();
        int x = random.nextInt(dimension);
        int y = random.nextInt(dimension);
        return new Coordinate(x, y);
    }

    @Override
    public Iterable<Coordinate> getAdjacentCoordinates(Coordinate coordinate, int dimension) {
        List<Coordinate> adjacentCoordinates = new ArrayList<>();

        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            for (int yOffset = -1; yOffset <= 1; yOffset++) {
                int adjX = coordinate.x() + xOffset;
                int adjY = coordinate.y() + yOffset;

                if (adjX >= 0 && adjX < dimension && adjY >= 0 && adjY < dimension) {
                    adjacentCoordinates.add(new Coordinate(adjX, adjY));
                }
            }
        }

        return adjacentCoordinates;
    }

    @Override
    public Iterable<Coordinate> getAdjacentCoordinates(Iterable<Coordinate> coordinates, int dimension) {
        List<Coordinate> adjacentCoordinates = new ArrayList<>();

        for (Coordinate coordinate : coordinates) {
            adjacentCoordinates.addAll((Collection<? extends Coordinate>) getAdjacentCoordinates(coordinate, dimension));
        }

        return adjacentCoordinates;
    }
}
