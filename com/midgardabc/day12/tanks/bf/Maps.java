package com.midgardabc.day12.tanks.bf;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by user on 06.12.2015.
 */
public class Maps {

    private Map<String, String[][]> maps;
    private Map<String, String> locations;
    private String[] objectsSymbols = {" ", "B", "R", "W"};

    private final String newMap = "New Map";
    private final String randomMap = "Random Map";

    private String[][] standart = {
            {"B", "B", "B", "B", "B", "B", "B", "B", "B"},
            {" ", "B", " ", "B", " ", "B", "B", "B", " "},
            {" ", "B", "B", " ", "B", "B", "B", "B", " "},
            {" ", "B", " ", "R", "R", " ", "R", "R", "R"},
            {"W", "W", "W", " ", "B", "W", "W", "W", "W"},
            {"W", "W", " ", "B", " ", "R", " ", "W", "W"},
            {"W", "R", " ", " ", " ", " ", " ", "B", "B"},
            {"R", "R", " ", "B", "B", "B", " ", " ", "B"},
            {"R", " ", " ", "R", "E", "B", " ", " ", "B"}
    };

    private String[][] tanks2D = {
            {" ", " ", "R", " ", "W", "R", "R", " ", " "},
            {" ", "R", " ", "R", "W", "R", " ", "R", " "},
            {" ", " ", " ", "R", "W", "R", " ", "R", " "},
            {" ", " ", "R", " ", "W", "R", " ", "R", " "},
            {" ", "R", " ", " ", "W", "R", " ", "R", " "},
            {" ", "R", "R", "R", "W", "R", "R", " ", " "},
            {"W", "W", "W", "W", "W", "W", "W", "W", "W"},
            {"B", "B", " ", "B", "B", "B", " ", "B", "B"},
            {"B", "B", " ", "B", "E", "B", " ", "B", "B"}
    };

    private String[][] whoStronger = {
            {" ", " ", " ", " ", " ", " ", " ", " ", " "},
            {" ", " ", "B", "B", "B", "B", "B", " ", " "},
            {"B", "B", "B", "W", "W", "W", "B", "B", "B"},
            {"W", "W", "W", "W", "W", "W", "W", "W", "W"},
            {"W", "W", "W", "W", "W", "W", "W", "W", "W"},
            {"B", "B", "B", "W", "W", "W", "R", "B", "R"},
            {" ", " ", "B", "B", "B", "B", "B", "B", "B"},
            {" ", " ", " ", " ", " ", " ", "R", "B", "B"},
            {" ", " ", " ", " ", " ", " ", "R", "B", "E"}
    };

    public Maps() {
        maps = new HashMap<>();
        maps.put("Standart", standart);
        maps.put("Tanks 2D", tanks2D);
        maps.put("Who Stronger", whoStronger);
        locations = new HashMap<>();
        locations.put("Standart", "512_64:128_512:64_0");
        locations.put("Tanks 2D", "512_128:128_512:0_448");
        locations.put("Who Stronger", "512_128:64_512:64_0");
    }

    public Map<String, String> getLocations() {
        return locations;
    }

    public Map<String, String[][]> getMaps() {
        return maps;
    }

    public String getNewMap() {
        return newMap;
    }

    public String getRandomMap() {
        return randomMap;
    }

    public int chackCreatedMap(JTextField[][] textFields) {
        int counter = 0;
        for (JTextField[] textFieldsArray : textFields) {
            for (JTextField textField : textFieldsArray) {
                if (textField.getText().equals("E")) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public void createNewMap(JTextField[][] textFields) {
        String[][] createdMap = new String[9][9];
        for (int i = 0; i < createdMap.length; i++) {
            for (int j = 0; j < createdMap[i].length; j++) {
                createdMap[i][j] = textFields[i][j].getText();
                if (createdMap[i][j].equals("E")) {
                    createLocations(i+"_"+j, newMap);
                }
            }
        }
        maps.put(newMap, createdMap);
    }

    public void randomMap() {
        String[][] createdMap = new String[9][9];
        createdMap[8][4] = "E";
        createLocations(8+"_"+4, randomMap);
        Random random = new Random();
        for (int i = 0; i < createdMap.length; i++) {
            for (int j = 0; j < createdMap[i].length; j++) {
                if (i == 8 && j == 4) {
                    continue;
                }
                createdMap[i][j] = objectsSymbols[random.nextInt(4)];
            }
        }
        maps.put(randomMap, createdMap);
    }

    private void createLocations(String eagleCordinates, String key) {
        int y = Integer.parseInt(eagleCordinates.split("_")[0]);
        int x = Integer.parseInt(eagleCordinates.split("_")[1]);

        if (x >= 0 && x < 7) {
            x += 2;
        } else {
            x -= 2;
        }

        int defX = x*64;
        int defY = y*64;
        String locations = defY + "_" + defX + ":0_512:0_0";
        this.locations.put(key, locations);
    }

}
