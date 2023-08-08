package com.codecool.marsexploration.mapelements.model;

public class Map {
    private String[][] representation;

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

    public String[][] getRepresentation() {
        return representation;
    }

    public static String createStringRepresentation(String[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                sb.append(arr[i][j]);
                if (j < arr[i].length - 1) sb.append(" "); // Delimitator
            }
            if (i < arr.length - 1) sb.append("\n"); // Linie nouă pentru fiecare rând, exceptând ultimul
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return createStringRepresentation(representation);
    }
}

