package com.codecool.marsexploration.mapelements.service.generator;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;
import com.codecool.marsexploration.mapelements.model.Map;
import com.codecool.marsexploration.mapelements.model.MapElement;
import com.codecool.marsexploration.mapelements.service.placer.MapElementPlacer;
import com.codecool.marsexploration.mapelements.service.placer.MapElementPlacerImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapGeneratorImpl implements MapGenerator {

    private final MapElementPlacer mapElementPlacer;

    public MapGeneratorImpl(MapElementPlacerImpl mapElementPlacer) {
        this.mapElementPlacer = new MapElementPlacerImpl();
    }

    @Override
    public Map generate(MapConfiguration mapConfig) {
        int mapSize = mapConfig.getMapSize();
        String[][] mapRepresentation = new String[mapSize][mapSize];
        List<Coordinate> freeCoordinates = new ArrayList<>();

        // Initialize the map with empty spaces and populate the freeCoordinates list
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                mapRepresentation[i][j] = " ";
                freeCoordinates.add(new Coordinate(i, j));
            }
        }

        // Generate and place all map elements
        List<MapElementConfiguration> elementConfigs = mapConfig.getMapElementConfigurations();
        for (MapElementConfiguration elementConfig : elementConfigs) {
            int elementCount = elementConfig.getElementToSizes().get(0).getElementCount();
            int elementSize = elementConfig.getElementToSizes().get(0).size();
            String symbol = (String) elementConfig.getSymbol();

            for (int i = 0; i < elementCount; i++) {
                boolean placed = false;
                while (!placed && !freeCoordinates.isEmpty()) {
                    Random random = new Random();
                    int randomIndex = random.nextInt(freeCoordinates.size());
                    Coordinate coordinate = freeCoordinates.get(randomIndex);
                    MapElement element = new MapElement(generateElementRepresentation(symbol, elementSize), elementConfig.name(), elementSize);
                    if (mapElementPlacer.canPlaceElement(element, mapRepresentation, coordinate)) {
                        mapElementPlacer.placeElement(element, mapRepresentation, coordinate);
                        updateFreeCoordinates(freeCoordinates, coordinate, elementSize);
                        placed = true;
                    }
                }
            }
        }

        return new Map(mapRepresentation);
    }

    private void updateFreeCoordinates(List<Coordinate> freeCoordinates, Coordinate placedCoordinate, int elementSize) {
        List<Coordinate> coordinatesToRemove = new ArrayList<>();
        for (Coordinate coordinate : freeCoordinates) {
            int distanceX = Math.abs(coordinate.getX() - placedCoordinate.getX());
            int distanceY = Math.abs(coordinate.getY() - placedCoordinate.getY());
            if (distanceX <= elementSize / 2 && distanceY <= elementSize / 2) {
                coordinatesToRemove.add(coordinate);
            }
        }
        freeCoordinates.removeAll(coordinatesToRemove);
    }

    private String[][] generateElementRepresentation(String symbol, int dimension) {
        String[][] representation = new String[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                representation[i][j] = symbol;
            }
        }
        return representation;
    }
}
