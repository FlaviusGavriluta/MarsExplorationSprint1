package com.codecool.marsexploration.mapelements.service.generator;

import com.codecool.marsexploration.calculators.service.DimensionCalculator;
import com.codecool.marsexploration.calculators.service.DimensionCalculatorImpl;
import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;
import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.mapelements.model.Map;
import com.codecool.marsexploration.mapelements.model.MapElement;
import com.codecool.marsexploration.mapelements.service.placer.MapElementPlacerImpl;

import java.util.List;

public class MapGeneratorImpl implements MapGenerator {
    private final DimensionCalculator dimensionCalculator;

    public MapGeneratorImpl() {
        this.dimensionCalculator = new DimensionCalculatorImpl();
    }

    @Override
    public Map generate(MapConfiguration mapConfig) {
        int mapSize = mapConfig.getMapSize();
        double elementToSpaceRatio = mapConfig.getElementToSpaceRatio();
        List<MapElementConfiguration> mapElementConfigurations = mapConfig.getMapElementConfigurations();

        String[][] mapRepresentation = new String[mapSize][mapSize];

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                mapRepresentation[i][j] = " ";
            }
        }

        for (MapElementConfiguration elementConfig : mapElementConfigurations) {
            String symbol = (String) elementConfig.getSymbol();
            String preferredLocationSymbol = elementConfig.preferredLocationSymbol();
            List<ElementToSize> elementToSizes = elementConfig.getElementToSizes();

            int elementSize = calculateElementSize(elementToSizes, elementToSpaceRatio, mapSize);
            MapElement element = createMapElement(symbol, elementSize);
            placeElementOnMap(mapRepresentation, element, preferredLocationSymbol);
        }

        Map map = new Map(mapRepresentation);
        map.setSuccessfullyGenerated(true);
        return map;
    }

    private int calculateElementSize(List<ElementToSize> elementToSizes, double elementToSpaceRatio, int mapSize) {
        int elementSize = 0;

        for (ElementToSize elementToSize : elementToSizes) {
            if (elementToSize.getElementCount() <= mapSize * mapSize * elementToSpaceRatio) {
                elementSize = elementToSize.size();
                break;
            }
        }

        return elementSize;
    }

    private MapElement createMapElement(String symbol, int dimension) {
        String[][] representation = new String[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                representation[i][j] = symbol;
            }
        }
        return new MapElement(representation, symbol, dimension);
    }

    private void placeElementOnMap(String[][] map, MapElement element, String preferredLocationSymbol) {
        Coordinate coordinate = findCoordinateForElement(map, preferredLocationSymbol);
        if (coordinate != null) {
            MapElementPlacerImpl elementPlacer = new MapElementPlacerImpl();
            elementPlacer.placeElement(element, map, coordinate);
        }
    }

    private Coordinate findCoordinateForElement(String[][] map, String preferredLocationSymbol) {
        if (preferredLocationSymbol != null) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (preferredLocationSymbol.equals(map[i][j])) {
                        return new Coordinate(i, j);
                    }
                }
            }
        }

        int center = map.length / 2;
        if (map[center][center].isEmpty()) {
            return new Coordinate(center, center);
        }

        return null;
    }
}
