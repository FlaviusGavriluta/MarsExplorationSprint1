package com.codecool.marsexploration.calculators.service;

public class DimensionCalculatorImpl implements DimensionCalculator {
    @Override
    public int calculateDimension(int size, int dimensionGrowth) {
        // Check if the size is zero or negative or the growth is negative
        if (size <= 0 || dimensionGrowth < 0) {
            return -1;
        }
        // Calculate the minimum dimension that can contain the given size
        int minimumDimension = (int) Math.ceil(Math.sqrt(size));
        // Add the dimension growth to the minimum dimension
        return minimumDimension + dimensionGrowth;
    }
}