package com.simplicite.utils.datastore;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStore {

    ArrayList<Map<String, Object>> data = new ArrayList<>();

    JsonArray ar = new JsonArray();
    @Test
    public void test() {
        var data = new DataContainer(new ArrayList<>());
        try {
            // create Gson instance
            ObjectMapper mapper = new ObjectMapper();
            var des = mapper.readValue(Paths.get("src/test/resources/MetaData.json").toFile(), DataContainer.class);
            System.out.println(des);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void test2() throws IOException {
        var data = new DataContainer(new ArrayList<>());
        String content = Files.readString(Paths.get("src/test/resources/MetaData.json"));
        JSONObject a = new JSONObject(content);
        JSONArray array = a.getJSONArray("objects");
    }
}
