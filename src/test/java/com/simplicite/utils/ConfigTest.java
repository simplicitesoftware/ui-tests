package com.simplicite.utils;

import com.simplicite.account.Authentication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Deprecated
public class ConfigTest {

    private final String path;
    public String url;
    public String port;
    public boolean headless;
    public String browser;
    public String browsersize;

    public Authentication auth;
    public ConfigTest(){
        path = "src/test/resources/Configuration.txt";
        auth = new Authentication();
        readFile();
    }

    public ConfigTest(String path){
        this.path = path;
        auth = new Authentication();
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
            headless = line.substring(9).equals("true");
        else if (line.contains("browser="))
            browser=line.substring(8);
        else if (line.contains("browsersize="))
            browsersize=line.substring(12);
        else if (line.contains("name="))
            auth.setUsername(line.substring(5));
        else if (line.contains("password="))
            auth.setPassword(line.substring(9));
    }
}
