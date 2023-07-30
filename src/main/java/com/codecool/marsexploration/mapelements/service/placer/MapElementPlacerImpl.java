package com.codecool.marsexploration.mapelements.service.placer;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.mapelements.model.MapElement;

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

        for (int i = 0; i < elementWidth; i++) {
            for (int j = 0; j < elementHeight; j++) {
                int mapX = startX + i;
                int mapY = startY + j;

                if (mapX < 0 || mapX >= map.length || mapY < 0 || mapY >= map[0].length) {
                    return false;
                }

                if (!map[mapX][mapY].isEmpty() && !element.getRepresentation()[i][j].isEmpty()) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public void placeElement(MapElement element, String[][] map, Coordinate coordinate) {
        int elementWidth = element.getRepresentation().length;
        int elementHeight = element.getRepresentation()[0].length;
        int x = coordinate.x();
        int y = coordinate.y();
        int startX = x - (elementWidth / 2);
        int startY = y - (elementHeight / 2);

        for (int i = 0; i < elementWidth; i++) {
            for (int j = 0; j < elementHeight; j++) {
                map[startX + i][startY + j] = element.getRepresentation()[i][j];
            }
        }
    }
}
