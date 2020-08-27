package com.starkIndustries.game;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class StoryTeller {
    //TODO：write a method to load JSON objects'
    public JsonObject getStory(File path)  {
        JsonElement jsonFile = null;
        try {
            jsonFile = JsonParser.parseReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return jsonFile.getAsJsonObject();

    }

    //TODO: write a method to tell Story
}
