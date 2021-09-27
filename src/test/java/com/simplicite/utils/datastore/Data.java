package com.simplicite.utils.datastore;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.Map;

@JsonSerialize
public record Data(String name, String action,ArrayList<Map<String, Object>> data) {
    public Map<String, Object> getDataByKey(String key, String name) {
        int index = 0;
        int length = data.size();
        while (index < length && !data.get(index).get(key).equals(name))
            index++;
        return data.get(index);
    }
}
