package com.codecool.marsexploration.mapelements.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MapTest {

    @Test
    public void testCreateStringRepresentation() {
        String[][] input = {
                {"#", "#", "#"},
                {"*", "*", "*"},
                {"&", "&", "&"}
        };
        String expected = "# # #\n* * *\n& & &";

        Map map = new Map(input);
        String actual = map.toString();

        assertEquals(expected, actual, "The string representation should match the expected format.");
    }

    // Putem adăuga și alte teste pentru cazuri de margine sau variații specifice
}
