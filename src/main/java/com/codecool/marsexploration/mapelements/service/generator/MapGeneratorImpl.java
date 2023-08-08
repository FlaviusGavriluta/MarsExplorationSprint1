package com.codecool.marsexploration.mapelements.service.generator;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.mapelements.model.Map;
import com.codecool.marsexploration.mapelements.model.MapElement;
import com.codecool.marsexploration.mapelements.service.placer.MapElementPlacer;

public class MapGeneratorImpl implements MapGenerator {

    private MapElementsGenerator elementsGenerator;
    private MapElementPlacer elementPlacer;
    private CoordinateCalculator coordinateCalculator;

    public MapGeneratorImpl(MapElementsGenerator elementsGenerator, MapElementPlacer elementPlacer, CoordinateCalculator coordinateCalculator) {
        this.elementsGenerator = elementsGenerator;
        this.elementPlacer = elementPlacer;
        this.coordinateCalculator = coordinateCalculator;
    }

    @Override
    public Map generate(MapConfiguration mapConfig) {
        int mapSize = mapConfig.mapSize();

        String[][] emptyMapRepresentation = new String[mapSize][mapSize];

        for (int i = 0; i < emptyMapRepresentation.length; i++) {
            for (int j = 0; j < emptyMapRepresentation[0].length; j++) {
                emptyMapRepresentation[i][j] = " ";
            }
        }

        Map map = new Map(emptyMapRepresentation);
        Iterable<MapElement> elements = elementsGenerator.createAll(mapConfig);

        for (MapElement element : elements) {
            Coordinate coordinate = coordinateCalculator.getRandomCoordinate(mapSize);
            while (!elementPlacer.canPlaceElement(element, emptyMapRepresentation, coordinate)) {
<<<<<<< HEAD
                coordinate = coordinateCalculator.getRandomCoordinate(mapSize);
=======
                coordinate = coordinateCalculator.getRandomCoordinate(mapSize); // Generează noi coordonate
                System.out.println("Intra in while");
                System.out.println("Element " + element + " can't be placed at " + coordinate.toString());
>>>>>>> f88caac1d11b8c09276f88700286f64928d1d48c
            }

            elementPlacer.placeElement(element, emptyMapRepresentation, coordinate);
        }

        return map;
    }
}
