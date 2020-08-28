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

    public void runScript (JsonObject script){
        /*
        1. print voice over
        2. print NPC
        3. print player. player voice
        4. take user prompt  if prompter.scanner == "A" : => "1" / if prompter.scanner == "B": => "2"
        5. go to next scene choosed by user
         */
        JsonObject scene = script.get("0").getAsJsonObject();
        String voiceOver = script.get("0").getAsJsonObject().get("voice over").getAsString();
        String nPC = script.get("").getsome




    }

    //TODO: write a method to tell Story
}
