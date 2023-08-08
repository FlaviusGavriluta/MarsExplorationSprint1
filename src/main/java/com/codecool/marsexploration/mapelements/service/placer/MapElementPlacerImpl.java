package com.codecool.marsexploration.mapelements.service.placer;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.mapelements.model.MapElement;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MapElementPlacerImpl implements MapElementPlacer {
    CoordinateCalculator coordinateCalculator;

    public MapElementPlacerImpl(CoordinateCalculator coordinateCalculator) {
        this.coordinateCalculator = coordinateCalculator;
    }

    @Override
    public boolean canPlaceElement(MapElement element, String[][] map, Coordinate coordinate) {
        int elementDimension = element.getDimension();
        int mapDimension = map.length;
        String preferredLocation = element.getPreferredLocationSymbol();
        int x = coordinate.x();
        int y = coordinate.y();

        if (!preferredLocation.isEmpty()) {
            Coordinate preferredCoordinate = coordinateCalculator.getRandomCoordinate(mapDimension);
            while (!Objects.equals(map[preferredCoordinate.x()][preferredCoordinate.y()], preferredLocation)) {
                preferredCoordinate = coordinateCalculator.getRandomCoordinate(mapDimension);
            }
            Iterable<Coordinate> adjacentCoordinates = coordinateCalculator.getAdjacentCoordinates(preferredCoordinate, mapDimension);
            for (Coordinate adjacentCoordinate : adjacentCoordinates) {
                if (Objects.equals(map[adjacentCoordinate.getX()][adjacentCoordinate.getY()], " ")) {
                    map[adjacentCoordinate.getX()][adjacentCoordinate.getY()] = element.toString();
                }
            }
        }

        if (x + elementDimension > map.length || y + elementDimension > map[0].length) {
            return false;
        }

        if (elementDimension == 1) {
            return Objects.equals(map[x][y], " ");
        } else {
            for (int i = x; i < x + elementDimension; i++) {
                for (int j = y; j < y + elementDimension; j++) {
                    if (!Objects.equals(map[i][j], " ")) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    @Override
    public void placeElement(MapElement element, String[][] map, Coordinate coordinate) {
        int dimension = element.getDimension();
        int x = coordinate.x();
        int y = coordinate.y();
        String[][] representation = element.getRepresentation();

        if (dimension == 1) {
            map[x][y] = representation[0][0];
        } else {
            for (int i = 0; i < dimension; i++) {
                System.arraycopy(representation[i], 0, map[x + i], y , dimension);
            }
        }

    }
}