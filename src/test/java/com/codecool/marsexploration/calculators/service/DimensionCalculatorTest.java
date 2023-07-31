package com.codecool.marsexploration.calculators.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DimensionCalculatorTest {
    private final DimensionCalculator dimensionCalculator = new DimensionCalculatorImpl();

    @Test
    public void testCalculateDimensionWithSize20AndDimensionGrowth3() {
        int size = 20;
        int dimensionGrowth = 3;
        int expectedDimension = 7;

        int result = dimensionCalculator.calculateDimension(size, dimensionGrowth);
        Assertions.assertEquals(expectedDimension, result);
    }

    @Test
    public void testCalculateDimensionWithSize1AndDimensionGrowth1() {
        int size = 1;
        int dimensionGrowth = 1;
        int expectedDimension = 1;

        int result = dimensionCalculator.calculateDimension(size, dimensionGrowth);
        Assertions.assertEquals(expectedDimension, result);
    }

    @Test
    public void testCalculateDimensionWithSize10AndDimensionGrowth5() {
        int size = 10;
        int dimensionGrowth = 5;
        int expectedDimension = 6;

        int result = dimensionCalculator.calculateDimension(size, dimensionGrowth);
        Assertions.assertEquals(expectedDimension, result);
    }
}
