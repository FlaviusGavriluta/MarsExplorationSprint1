package com.codecool.marsexploration.output.service;

import com.codecool.marsexploration.mapelements.model.Map;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MapFileWriterImpl implements MapFileWriter {

    @Override
    public void writeMapFile(Map map, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            String[][] representation = map.getRepresentation();
            for (String[] row : representation) {
                for (String cell : row) {
                    writer.write(cell);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing the map file: " + e.getMessage());
        }
    }
}