package com.starkIndustries.game;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLOutput;
import java.util.Set;

public class StoryTeller {

    //load JSON objects
    public JsonObject getStory(File path)  {
        JsonElement jsonFile = null;
        try {
            jsonFile = JsonParser.parseReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return jsonFile.getAsJsonObject();
    }

    //run the script and return userInput to change scene
    public static void runScript (JsonObject script, String scene){
        /*
        1. print voice over
        2. print NPC
        3. print player. player voice
        4. take user prompt  if prompter.scanner == "A" : => "1" / if prompter.scanner == "B": => "2"
        5. go to next scene choosed by user
         */
        JsonObject thisScene = script.get(scene).getAsJsonObject();
        JsonObject player = thisScene.get("Player").getAsJsonObject();
        Set<String> sceneKey = thisScene.keySet();
        Set<String> playerKey = player.get("reply").getAsJsonObject().keySet();
        for (String key : sceneKey){
            if (!key.equals("Player")){
                System.out.println(thisScene.get(key).getAsString());
                Prompter.promptEnterKey();
            }
        }
        String answer = Prompter.handleResponse(player.get("voice").getAsString());
        if (answer.toUpperCase().equals("EXIT")){
            StartGame newGame = new StartGame();
            newGame.exitGame(scene);
        }else if (playerKey.contains(answer.toUpperCase())) {
            String nextScene = player.get("reply").getAsJsonObject().get(answer).getAsString();
            runScript(script,nextScene);
        }else {
            System.out.println("Please enter a valid input");
        }
    }

}
