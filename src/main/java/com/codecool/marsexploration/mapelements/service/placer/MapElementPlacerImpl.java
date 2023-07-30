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
        
        return false;
    }

    @Override
    public void placeElement(MapElement element, String[][] map, Coordinate coordinate) {

    }
}
