package com.simplicite.utils.datastore;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;

@JsonSerialize
public record DataContainer(ArrayList<Data> objects) {

    public Data getObjectByName(String name) {
        int index = 0;
        int length = objects.size();
        while (index < length && !objects.get(index).name().equalsIgnoreCase(name))
            index++;
        return objects.get(index);
    }
}
