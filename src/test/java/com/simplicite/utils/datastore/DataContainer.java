package com.simplicite.utils.datastore;

import java.util.ArrayList;
import java.util.Map;

public record DataContainer(String datatype, ArrayList<Map<String, Object>> data){
}
