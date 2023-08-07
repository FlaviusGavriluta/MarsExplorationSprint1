package com.codecool.marsexploration.mapelements.service.placer;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.mapelements.model.MapElement;

public class MapElementPlacerImpl implements MapElementPlacer {

    @Override
    public boolean canPlaceElement(MapElement element, String[][] map, Coordinate coordinate) {
        int dimension = element.getDimension();
        int x = coordinate.x();
        int y = coordinate.y();

        if (dimension == 1) {
            return map[x][y] == null; // sau cum vrei să reprezinți un spațiu liber
        } else {
            for (int i = x; i < x + dimension; i++) {
                for (int j = y; j < y + dimension; j++) {
                    if (map[i][j] != null) {
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
                for (int j = 0; j < dimension; j++) {
                    map[x + i][y + j] = representation[i][j];
                }
            }
        }
    }
}

