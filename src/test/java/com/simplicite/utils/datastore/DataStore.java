package com.simplicite.utils.datastore;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Paths;

public class DataStore {

    public static DataContainer data = null;

    static  {
        try {
            ObjectMapper mapper = new ObjectMapper();
            data = mapper.readValue(Paths.get("src/test/resources/MetaData.json").toFile(), DataContainer.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
