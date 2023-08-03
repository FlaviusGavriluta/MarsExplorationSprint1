package com.codecool.marsexploration.mapelements.model;

public class Map {
    private final String[][] representation;

    private boolean successfullyGenerated;

    public Map(String[][] representation) {
        this.representation = representation;
    }

    public boolean isSuccessfullyGenerated() {
        return successfullyGenerated;
    }

    public void setSuccessfullyGenerated(boolean successfullyGenerated) {
        this.successfullyGenerated = successfullyGenerated;
    }

    private static String createStringRepresentation(String[][] arr) {
        StringBuilder map = new StringBuilder();

        for (String[] row : arr) {
            for (String element : row) {
                map.append(element);
            }
            map.append("\n");
        }
        return map.toString();
    }

    @Override
    public String toString() {
        return createStringRepresentation(representation);
    }

    public String[][] getRepresentation() {
        return representation;
    }
}

