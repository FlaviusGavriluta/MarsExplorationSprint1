package com.codecool.marsexploration.mapelements.service.placer;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.mapelements.model.MapElement;

import java.util.ArrayList;
import java.util.List;

public class MapElementPlacerImpl implements MapElementPlacer {
    @Override
    public boolean canPlaceElement(MapElement element, String[][] map, Coordinate coordinate) {
        int elementWidth = element.getRepresentation().length;
        int elementHeight = element.getRepresentation()[0].length;
        int x = coordinate.x();
        int y = coordinate.y();
        int startX = x - (elementWidth / 2);
        int startY = y - (elementHeight / 2);

        if (startX < 0 || startY < 0 || startX + elementWidth > map.length || startY + elementHeight > map[0].length) {
            return false;
        }

        List<Coordinate> occupiedCoordinates = getOccupiedCoordinates(element, coordinate);
        for (Coordinate coord : occupiedCoordinates) {
            int coordX = coord.x();
            int coordY = coord.y();
            if (!map[coordX][coordY].isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void placeElement(MapElement element, String[][] map, Coordinate coordinate) {
        List<Coordinate> occupiedCoordinates = getOccupiedCoordinates(element, coordinate);
        for (Coordinate coord : occupiedCoordinates) {
            int coordX = coord.x();
            int coordY = coord.y();
            map[coordX][coordY] = element.getRepresentation()[coordX - coordinate.x() + element.getRepresentation().length / 2][coordY - coordinate.y() + element.getRepresentation()[0].length / 2];
        }
    }

    private List<Coordinate> getOccupiedCoordinates(MapElement element, Coordinate coordinate) {
        List<Coordinate> occupiedCoordinates = new ArrayList<>();
        int elementWidth = element.getRepresentation().length;
        int elementHeight = element.getRepresentation()[0].length;
        int x = coordinate.x();
        int y = coordinate.y();
        int startX = x - (elementWidth / 2);
        int startY = y - (elementHeight / 2);

        for (int i = 0; i < elementWidth; i++) {
            for (int j = 0; j < elementHeight; j++) {
                occupiedCoordinates.add(new Coordinate(startX + i, startY + j));
            }
        }

        return occupiedCoordinates;
    }
}
