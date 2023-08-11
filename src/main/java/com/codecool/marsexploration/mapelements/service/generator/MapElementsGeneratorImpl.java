package com.codecool.marsexploration.mapelements.service.generator;

import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;
import com.codecool.marsexploration.mapelements.model.MapElement;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MapElementsGeneratorImpl implements MapElementsGenerator {
    private final MapElementBuilder builder;

    public MapElementsGeneratorImpl(MapElementBuilder builder) {
        this.builder = builder;
    }

    @Override
    public Iterable<MapElement> createAll(MapConfiguration mapConfig) {
        return mapConfig.mapElementConfigurations().stream()
                .flatMap(elementConfig -> isUnidimensional(elementConfig)
                        ? generateUnidimensionalElements(elementConfig).stream()
                        : generateMultidimensionalElements(elementConfig).stream())
                .collect(Collectors.toList());
    }

    private List<MapElement> generateUnidimensionalElements(MapElementConfiguration mapElementConfiguration) {
        return mapElementConfiguration.elementToSizes().stream()
                .filter(elementToSize -> elementToSize.size() == 1)
                .flatMap(elementToSize -> IntStream.range(0, elementToSize.elementCount())
                        .mapToObj(i -> builder.build(
                                1,
                                mapElementConfiguration.symbol(),
                                mapElementConfiguration.name(),
                                mapElementConfiguration.dimensionGrowth(),
                                mapElementConfiguration.preferredLocationSymbol())))
                .collect(Collectors.toList());
    }

    private List<MapElement> generateMultidimensionalElements(MapElementConfiguration mapElementConfiguration) {
        return mapElementConfiguration.elementToSizes().stream()
                .filter(elementToSize -> elementToSize.size() > 1)
                .flatMap(elementToSize -> IntStream.range(0, elementToSize.elementCount())
                        .mapToObj(i -> builder.build(
                                elementToSize.size(),
                                mapElementConfiguration.symbol(),
                                mapElementConfiguration.name(),
                                mapElementConfiguration.dimensionGrowth(),
                                mapElementConfiguration.preferredLocationSymbol())))
                .collect(Collectors.toList());
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