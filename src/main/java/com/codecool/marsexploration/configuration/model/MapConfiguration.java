package com.codecool.marsexploration.configuration.model;

import java.util.List;

public record MapConfiguration(
        int mapSize,
        double elementToSpaceRatio,
        List<MapElementConfiguration> mapElementConfigurations) {

    public int getMapSize() {
        return mapSize;
    }

    public List<MapElementConfiguration> getMapElementConfigurations() {
        return mapElementConfigurations;
    }
}
