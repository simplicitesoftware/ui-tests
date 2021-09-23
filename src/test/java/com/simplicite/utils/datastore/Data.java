package com.simplicite.utils.datastore;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.Map;

@JsonSerialize
public record Data(String name, ArrayList<Map<String, Object>> data) {
}
