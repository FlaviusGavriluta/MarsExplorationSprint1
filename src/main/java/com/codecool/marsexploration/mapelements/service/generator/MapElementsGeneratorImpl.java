package com.codecool.marsexploration.mapelements.service.generator;

import com.codecool.marsexploration.calculators.service.DimensionCalculator;
import com.codecool.marsexploration.calculators.service.DimensionCalculatorImpl;
import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;
import com.codecool.marsexploration.mapelements.model.MapElement;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilder;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilderImpl;

import java.util.ArrayList;
import java.util.List;

public class MapElementsGeneratorImpl implements MapElementsGenerator{
    private final MapElementBuilder mapElementBuilder = new MapElementBuilderImpl();
    @Override
    public Iterable<MapElement> createAll(MapConfiguration mapConfig) {
        List<MapElement> mapElements = new ArrayList<>();
        List<MapElementConfiguration> mapElementConfigurations = mapConfig.getMapElementConfigurations();

        for (MapElementConfiguration mapElementConfiguration : mapElementConfigurations) {
            for (ElementToSize elementToSize : mapElementConfiguration.getElementToSizes()) {
                int elementCount = elementToSize.elementCount();
                while (elementCount > 0) {
                    mapElements.add(mapElementBuilder.build(elementToSize.size(), mapElementConfiguration.symbol(), mapElementConfiguration.name(), mapElementConfiguration.dimensionGrowth(), mapElementConfiguration.preferredLocationSymbol()));
                    elementCount--;
                }
            }
        }
        return mapElements;
    }
}
