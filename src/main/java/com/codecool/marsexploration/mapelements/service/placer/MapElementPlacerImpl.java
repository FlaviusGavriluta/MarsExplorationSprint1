package com.codecool.marsexploration.mapelements.service.placer;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.mapelements.model.MapElement;

import java.util.Objects;

public class MapElementPlacerImpl implements MapElementPlacer {
    CoordinateCalculator coordinateCalculator;

    public MapElementPlacerImpl(CoordinateCalculator coordinateCalculator) {
        this.coordinateCalculator = coordinateCalculator;
    }

    @Override
    public boolean canPlaceElement(MapElement element, String[][] map, Coordinate coordinate) {
        int elementDimension = element.getDimension();
        String preferredLocation = element.getPreferredLocationSymbol();
        int x = coordinate.x();
        int y = coordinate.y();
        if (!preferredLocation.isEmpty()) {
            Iterable<Coordinate> adjacentCoordinates = coordinateCalculator.getAdjacentCoordinates(coordinate, 1);
            boolean valid = false;
            for (Coordinate adjacentCoordinate : adjacentCoordinates) {
                if (map[adjacentCoordinate.x()][adjacentCoordinate.y()].equals(element.getPreferredLocationSymbol())) {
                    valid = true;
                }
            }
            if (!valid) {
                return false;
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
                System.arraycopy(representation[i], 0, map[x + i], y, dimension);
            }
        }
    }

}