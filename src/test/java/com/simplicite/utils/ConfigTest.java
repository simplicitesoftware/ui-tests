package com.simplicite.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ConfigTest {

    private final String path;
    public String url;
    public String port;
    public boolean headless;
    public String browser;
    public String browsersize;

    public ConfigTest(){
        path = "src/test/resources/Configuration.txt";
        readFile();
    }

    public void readFile(){
        StringBuilder linebuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                updateConfig(line);
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void updateConfig(String line) {
        if (line.contains("url="))
            url = line.substring(4);
        else if (line.contains("port="))
            port = line.substring(5);
        else if (line.contains("headless="))
            headless = line.substring(10).equals("true");
        else if (line.contains("browser="))
            browser=line.substring(8);
        else if (line.contains("browsersize="))
            browsersize=line.substring(12);
    }
}
