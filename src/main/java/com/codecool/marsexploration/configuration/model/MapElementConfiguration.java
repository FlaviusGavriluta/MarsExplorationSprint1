package com.codecool.marsexploration.configuration.model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public record MapElementConfiguration(
        String symbol,
        String name,
        List<ElementToSize> elementToSizes,
        int dimensionGrowth,
        String preferredLocationSymbol) {
    public Object getSymbol() {
        return symbol;
    }

    public List<ElementToSize> getElementToSizes() {
        return elementToSizes;
    }
}
