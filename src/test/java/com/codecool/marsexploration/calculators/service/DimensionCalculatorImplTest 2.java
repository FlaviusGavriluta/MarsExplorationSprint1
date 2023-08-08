package com.codecool.marsexploration.calculators.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DimensionCalculatorImplTest {

    private DimensionCalculatorImpl dimensionCalculator;

    @BeforeEach
    void setUp() {
        dimensionCalculator = new DimensionCalculatorImpl();
    }

    @Test
    void testCalculateDimensionWithSize20AndDimensionGrowth3() {
        int size = 20;
        int dimensionGrowth = 3;
        int expectedDimension = 8;
        int actualDimension = dimensionCalculator.calculateDimension(size, dimensionGrowth);
        assertEquals(expectedDimension, actualDimension);
    }

    @Test
    void testCalculateDimensionWithSize1AndDimensionGrowth2() {
        int size = 1;
        int dimensionGrowth = 2;
        int expectedDimension = 3;
        int actualDimension = dimensionCalculator.calculateDimension(size, dimensionGrowth);
        assertEquals(expectedDimension, actualDimension);
    }

    @Test
    void testCalculateDimensionWithSize100AndDimensionGrowth5() {
        int size = 100;
        int dimensionGrowth = 5;
        int expectedDimension = 15;
        int actualDimension = dimensionCalculator.calculateDimension(size, dimensionGrowth);
        assertEquals(expectedDimension, actualDimension);
    }

    @Test
    void testCalculateDimensionWithSize10AndDimensionGrowth1() {
        int size = 10;
        int dimensionGrowth = 1;
        int expectedDimension = 5;
        int actualDimension = dimensionCalculator.calculateDimension(size, dimensionGrowth);
        assertEquals(expectedDimension, actualDimension);
    }
}
