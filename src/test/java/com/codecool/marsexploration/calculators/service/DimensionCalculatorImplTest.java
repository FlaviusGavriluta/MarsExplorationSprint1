package com.codecool.marsexploration.calculators.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DimensionCalculatorImplTest {

    private final DimensionCalculator calculator = new DimensionCalculatorImpl();

    @Test
    public void testCalculateDimensionWithPositiveValues() {
        assertEquals(8, calculator.calculateDimension(20, 3));
        assertEquals(15, calculator.calculateDimension(20, 10));
        assertEquals(35, calculator.calculateDimension(1000, 3));
        assertEquals(453, calculator.calculateDimension(200000, 5));
    }

    @Test
    public void testCalculateDimensionWithZeroSizeAndGrowth() {
        assertEquals(-1, calculator.calculateDimension(0, 0));
    }

    @Test
    public void testCalculateDimensionWithZeroSizeAndPositiveGrowth() {
        assertEquals(-1, calculator.calculateDimension(0, 1));
        assertEquals(-1, calculator.calculateDimension(0, 10));
    }

    @Test
    public void testCalculateDimensionWithPositiveSizeAndZeroGrowth() {
        assertEquals(5, calculator.calculateDimension(20, 0));
        assertEquals(32, calculator.calculateDimension(1000, 0));
    }

    @Test
    public void testCalculateDimensionWithNegativeValues() {
        assertEquals(-1, calculator.calculateDimension(-5, 3));
        assertEquals(-1, calculator.calculateDimension(10, -2));
    }

    @Test
    public void testCalculateDimensionWithEqualSizeAndGrowth() {
        assertEquals(25, calculator.calculateDimension(20, 20));
        assertEquals(1032, calculator.calculateDimension(1000, 1000));
    }
}
