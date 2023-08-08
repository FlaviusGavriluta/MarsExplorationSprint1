package com.codecool.marsexploration.mapelements.service.generator;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.calculators.service.CoordinateCalculatorImpl;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;
import com.codecool.marsexploration.mapelements.model.Map;
import com.codecool.marsexploration.mapelements.model.MapElement;
import com.codecool.marsexploration.mapelements.service.placer.MapElementPlacer;
import com.codecool.marsexploration.mapelements.service.placer.MapElementPlacerImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MapGeneratorImpl implements MapGenerator {

    private final MapElementPlacer mapElementPlacer;
    private final MapElementsGenerator mapElementsGenerator;
    private final CoordinateCalculator coordinateCalculator;

    public MapGeneratorImpl(MapElementPlacer mapElementPlacer, MapElementsGenerator mapElementsGenerator, CoordinateCalculator coordinateCalculator) {
        this.mapElementPlacer = new MapElementPlacerImpl(coordinateCalculator);
        this.mapElementsGenerator = new MapElementsGeneratorImpl();
        this.coordinateCalculator = new CoordinateCalculatorImpl();
    }

    @Override
    public Map generate(MapConfiguration mapConfig) {
        int mapSize = mapConfig.getMapSize();
        Iterable<MapElement> mapElements = mapElementsGenerator.createAll(mapConfig);
        String[][] map = new String[mapSize][mapSize];

        for (String[] strings : map) {
            Arrays.fill(strings, " ");
        }

        for (MapElement mapElement : mapElements) {
            Coordinate randomCoordinate = coordinateCalculator.getRandomCoordinate(mapSize);
            while (!mapElementPlacer.canPlaceElement(mapElement, map, randomCoordinate)) {
                randomCoordinate = coordinateCalculator.getRandomCoordinate(mapSize);
            }
            mapElementPlacer.placeElement(mapElement, map, randomCoordinate);
        }

        return new Map(map);
    }
}
