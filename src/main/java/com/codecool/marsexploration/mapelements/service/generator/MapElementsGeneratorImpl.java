package com.codecool.marsexploration.mapelements.service.generator;

import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;
import com.codecool.marsexploration.mapelements.model.MapElement;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilder;

import java.util.ArrayList;
import java.util.List;

public class MapElementsGeneratorImpl implements MapElementsGenerator {

    private final MapElementBuilder builder;

    public MapElementsGeneratorImpl(MapElementBuilder builder) {
        this.builder = builder;
    }

    @Override
    public Iterable<MapElement> createAll(MapConfiguration mapConfig) {
        List<MapElement> elements = new ArrayList<>();
        for (MapElementConfiguration elementConfig : mapConfig.mapElementConfigurations()) {
            String symbol = elementConfig.symbol();
            String name = elementConfig.name();
            int dimensionGrowth = elementConfig.dimensionGrowth();
            String preferredLocationSymbol = elementConfig.preferredLocationSymbol();

            System.out.println("Element: " + name + " is unidimensional: " + isUnidimensional(elementConfig));
            for (ElementToSize elementToSize : elementConfig.elementToSizes()) {
                int size = elementToSize.size();
                int quantity = elementToSize.elementCount();

                for (int i = 0; i < quantity; i++) {
                    MapElement element = builder.build(size, symbol, name, dimensionGrowth, preferredLocationSymbol);
                    elements.add(element);
                }
            }
        }

        return elements;
    }

    private boolean isUnidimensional(MapElementConfiguration elementConfiguration) {
        return elementConfiguration.elementToSizes().stream()
                .map(elementToSize -> builder.build(
                        elementToSize.size(),
                        elementConfiguration.symbol(),
                        elementConfiguration.name(),
                        elementConfiguration.dimensionGrowth(),
                        elementConfiguration.preferredLocationSymbol()))
                .noneMatch(MapElement::isMultidimensional);
    }
}