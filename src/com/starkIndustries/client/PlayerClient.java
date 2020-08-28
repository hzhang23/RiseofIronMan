package com.starkIndustries.client;

import com.google.gson.JsonObject;
import com.starkIndustries.game.StoryTeller;

import java.io.File;

public class PlayerClient {
    public static void main(String[] args) {
        File path = new File("resources/story.json");

        StoryTeller storyTeller = new StoryTeller();

        JsonObject newStory = storyTeller.getStory(path);

        storyTeller.runScript(newStory, "0");
    }
}
