package com.codecool.marsexploration.mapelements.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapTest {
    @Test
    void testCreateStringRepresentation() {
        String[][] representation = {
                {".", "#", ".", "#", "."},
                {"%", "*", "%", "*", "%"},
                {"&", "#", ".", "#", "&"},
                {".", "*", "*", "*", "."},
                {"%", "#", "%", "#", "%"}
        };

        Map map = new Map(representation);

        String expectedString = ".#.#.\n%*%*%\n&#.#&\n.***.\n%#%#%\n";
        String actualString = map.toString();

        assertEquals(expectedString, actualString);
    }
}
