package com.codecool.marsexploration.mapelements.service.placer;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.mapelements.model.MapElement;

import java.util.List;
import java.util.Objects;


public class MapElementPlacerImpl implements MapElementPlacer {
    private final CoordinateCalculator coordinateCalculator;

    public MapElementPlacerImpl(CoordinateCalculator coordinateCalculator) {
        this.coordinateCalculator = coordinateCalculator;
    }
    @Override
    public boolean canPlaceElement(MapElement element, String[][] map, Coordinate coordinate) {
        int mapDimension = map.length * map[0].length;
        int elementWidth = element.getRepresentation().length;
        int elementHeight = element.getRepresentation()[0].length;
        int x = coordinate.x();
        int y = coordinate.y();
        int startX = x - (elementWidth / 2);
        int startY = y - (elementHeight / 2);

        if (startX < 0 || startY < 0 || startX + elementWidth > map.length || startY + elementHeight > map[0].length) {
            return false;
        }

        Iterable<Coordinate> adjacentCoordinates = coordinateCalculator.getAdjacentCoordinates(coordinate, mapDimension);
        Iterable<Coordinate> allAdjacentCoordinates = coordinateCalculator.getAdjacentCoordinates(adjacentCoordinates, mapDimension);

        



        return true;
    }

    @Override
    public void placeElement(MapElement element, String[][] map, Coordinate coordinate) {

    }

}
