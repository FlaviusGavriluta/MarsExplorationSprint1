package com.codecool.marsexploration.mapelements.service.placer;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.mapelements.model.MapElement;

import java.util.Arrays;
import java.util.Objects;

public class MapElementPlacerImpl implements MapElementPlacer {
    @Override
    public boolean canPlaceElement(MapElement element, String[][] map, Coordinate coordinate) {
        int dimension = element.getDimension();
        int x = coordinate.x();
        int y = coordinate.y();

        if (x + dimension > map.length || y + dimension > map[0].length) {
            System.out.println("First if");
            return false;
        }

        if (dimension == 1) {
            System.out.println("Second if");
            return Objects.equals(map[x][y], " ");
        } else {
            for (int i = x; i < x + dimension; i++) {
                for (int j = y; j < y + dimension; j++) {
                    if (!Objects.equals(map[i][j], " ")) {
                        System.out.println("Third if");
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
        System.out.println(element);
        String[][] representation = element.getRepresentation();
        System.out.println(Arrays.deepToString(representation));

        if (dimension == 1) {
            map[x][y] = representation[0][0];
        } else {
            for (int i = 0; i < dimension; i++) {
                System.arraycopy(representation[i], 0, map[x + i], y , dimension);
            }
        }

    }
}