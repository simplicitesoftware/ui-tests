package com.simplicite.utils.datastore;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;

@JsonSerialize
public record DataContainer(ArrayList<Object> objects){}
