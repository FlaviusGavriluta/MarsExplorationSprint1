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
            if (isUnidimensional(elementConfig)) {
                elements.addAll(generateUnidimensionalElements(elementConfig));
            } else {
                elements.addAll(generateMultidimensionalElements(elementConfig));
            }
        }
        return elements;
    }

    private List<MapElement> generateUnidimensionalElements(MapElementConfiguration mapElementConfiguration) {
        List<MapElement> elements = new ArrayList<>();
        for (ElementToSize elementToSize : mapElementConfiguration.elementToSizes()) {
            if (elementToSize.size() == 1) {
                int quantity = elementToSize.elementCount();
                for (int i = 0; i < quantity; i++) {
                    MapElement element = builder.build(
                            1,
                            mapElementConfiguration.symbol(),
                            mapElementConfiguration.name(),
                            mapElementConfiguration.dimensionGrowth(),
                            mapElementConfiguration.preferredLocationSymbol());
                    elements.add(element);
                }
            }
        }
        return elements;
    }

    private List<MapElement> generateMultidimensionalElements(MapElementConfiguration mapElementConfiguration) {
        List<MapElement> elements = new ArrayList<>();
        for (ElementToSize elementToSize : mapElementConfiguration.elementToSizes()) {
            if (elementToSize.size() > 1) {
                int quantity = elementToSize.elementCount();
                for (int i = 0; i < quantity; i++) {
                    MapElement element = builder.build(
                            elementToSize.size(),
                            mapElementConfiguration.symbol(),
                            mapElementConfiguration.name(),
                            mapElementConfiguration.dimensionGrowth(),
                            mapElementConfiguration.preferredLocationSymbol());
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