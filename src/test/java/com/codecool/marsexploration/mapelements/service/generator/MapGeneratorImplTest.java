package com.codecool.marsexploration.mapelements.service.generator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;
import com.codecool.marsexploration.mapelements.model.Map;
import com.codecool.marsexploration.mapelements.model.MapElement;
import com.codecool.marsexploration.mapelements.service.placer.MapElementPlacer;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

class MapGeneratorImplTest {

    @Mock
    private MapElementsGenerator elementsGenerator;

    @Mock
    private MapElementPlacer elementPlacer;

    @Mock
    private CoordinateCalculator coordinateCalculator;

    List<MapElementConfiguration> elementConfigs = List.of(
            new MapElementConfiguration("symbol", "name", List.of(new ElementToSize(2, 20)), 3, "")
    );

    @Test
    void testGenerateEmptyMap() {
        MapConfiguration mapConfig = new MapConfiguration(5, 0.5, elementConfigs); // Presupunând că există un constructor cu dimensiunea hărții

        when(coordinateCalculator.getRandomCoordinate(anyInt())).thenReturn(new Coordinate(0, 0));
        when(elementsGenerator.createAll(any())).thenReturn(Collections.emptyList());

        MapGeneratorImpl mapGenerator = new MapGeneratorImpl(elementsGenerator, elementPlacer, coordinateCalculator);
        Map map = mapGenerator.generate(mapConfig);

        String[][] expectedRepresentation = new String[5][5];
        assertArrayEquals(expectedRepresentation, map.getRepresentation());
    }

    @Test
    void testPlaceElementOnMap() {
        MapConfiguration mapConfig = new MapConfiguration(5, 0.5, elementConfigs);
        MapElement element = mock(MapElement.class);
        when(element.getDimension()).thenReturn(1);
        when(element.getRepresentation()).thenReturn(new String[][]{{"E"}});

        when(coordinateCalculator.getRandomCoordinate(anyInt())).thenReturn(new Coordinate(2, 2));
        when(elementsGenerator.createAll(any())).thenReturn(Collections.singletonList(element));
        when(elementPlacer.canPlaceElement(any(), any(), any())).thenReturn(true);

        MapGeneratorImpl mapGenerator = new MapGeneratorImpl(elementsGenerator, elementPlacer, coordinateCalculator);
        Map map = mapGenerator.generate(mapConfig);

        String[][] expectedRepresentation = new String[5][5];
        expectedRepresentation[2][2] = "E";
        assertArrayEquals(expectedRepresentation, map.getRepresentation());
    }
}
