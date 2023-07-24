package com.codecool.marsexploration.calculators.service;

public class DimensionCalculatorImpl implements DimensionCalculator {

    @Override
    public int calculateDimension(int size, int dimensionGrowth) {
        int dimension = 1;
        int totalElements = dimension;

        while (totalElements < size) {
            dimension += dimensionGrowth;
            totalElements = dimension * dimension;
        }

        return dimension;
    }
}