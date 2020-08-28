package com.startIndustries.client;

import com.google.gson.JsonObject;
import com.starkIndustries.game.StoryTeller;

import java.io.File;

public class PlayerClient {
    public static void main(String[] args) {
        File path = new File("resources/story.json");

        StoryTeller storyTeller = new StoryTeller();

        JsonObject newStory = storyTeller.getStory(path);


        System.out.println("You wakes up suddenly with a tube up your nose,laying on a cot in a cave. you rip out " +
                "the" +
                " tube and tries to pick up a cup on the table beside you, but fail. you notice a man shaving and " +
                "tries to reach further but is stopped by wires.\n");

        System.out.println(newStory.get("Yinsen: I wouldn't do that if I were you").getAsJsonObject().get("description"));


    }
}
