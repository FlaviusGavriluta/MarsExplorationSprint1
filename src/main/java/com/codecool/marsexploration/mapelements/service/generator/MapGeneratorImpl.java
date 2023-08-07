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
    private CoordinateCalculator coordinateCalculator; // Serviciul pentru calcularea coordonatelor

    public MapGeneratorImpl(MapElementsGenerator elementsGenerator, MapElementPlacer elementPlacer, CoordinateCalculator coordinateCalculator) {
        this.elementsGenerator = elementsGenerator;
        this.elementPlacer = elementPlacer;
        this.coordinateCalculator = coordinateCalculator;
    }

    @Override
    public Map generate(MapConfiguration mapConfig) {
        int mapSize = mapConfig.mapSize(); // Presupunând că există o metodă getMapSize() în MapConfiguration

        // Crearea hărții goale cu dimensiunea specificată
        String[][] emptyMapRepresentation = new String[mapSize][mapSize];
        Map map = new Map(emptyMapRepresentation);

        // Generarea elementelor pe baza configurației
        Iterable<MapElement> elements = elementsGenerator.createAll(mapConfig);

        // Plasarea elementelor pe hartă
        for (MapElement element : elements) {
            Coordinate coordinate = coordinateCalculator.getRandomCoordinate(mapSize); // Generează coordonate aleatorii

            // Verifică dacă elementul poate fi plasat și încearcă alte coordonate dacă este necesar
            while (!elementPlacer.canPlaceElement(element, emptyMapRepresentation, coordinate)) {
                coordinate = coordinateCalculator.getRandomCoordinate(mapSize); // Generează noi coordonate
            }

            elementPlacer.placeElement(element, emptyMapRepresentation, coordinate);
        }

        return map;
    }
}
