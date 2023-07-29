package com.codecool.marsexploration.configuration.service;

import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;

import java.util.List;

public class MapConfigurationValidatorImpl implements MapConfigurationValidator {

    @Override
    public boolean validate(MapConfiguration mapConfig) {
        List<MapElementConfiguration> elements = mapConfig.getMapElementConfigurations();
        for (MapElementConfiguration element : elements) {
            System.out.println(element.symbol());
            System.out.println(element.name() + " is valid: " + validateElement(element));
        }
        return false;
    }

    private boolean validateElement(MapElementConfiguration element) {
        if (element.symbol().equals("#")) {
            if (element.dimensionGrowth() != 3) {
                return false;
            }
            if (element.getElementToSizes().size() < 2) {
                return false;
            }
            return element.preferredLocationSymbol().equals("");
        } else if (element.symbol().equals("&")) {
            if (element.dimensionGrowth() != 10) {
                return false;
            }
            if (element.getElementToSizes().size() < 2) {
                return false;
            }
            return element.preferredLocationSymbol().equals("");
        } else if (element.symbol().equals("%")) {
            if (element.dimensionGrowth() != 0) {
                return false;
            }
            if (element.getElementToSizes().size() > 1) {
                return false;
            }
            return element.preferredLocationSymbol().equals("#");
        } else if (element.symbol().equals("*")) {
            if (element.dimensionGrowth() != 0) {
                return false;
            }
            if (element.getElementToSizes().size() > 1) {
                return false;
            }
            return element.preferredLocationSymbol().equals("&");
        }
        return false;
    }
}
