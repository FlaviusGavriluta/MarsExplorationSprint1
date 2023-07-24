package com.codecool.marsexploration.configuration.model;

public record ElementToSize(int elementCount, int size) {
    public int getElementCount() {
        return elementCount;
    }
}
