package com.starkIndustries.client;

import com.google.gson.JsonObject;
import com.starkIndustries.game.StartGame;
import com.starkIndustries.game.StoryTeller;

import java.io.File;

public class PlayerClient {
    public static void main(String[] args) {
        StartGame startGame = new StartGame();
        startGame.initGame();
    }
}

