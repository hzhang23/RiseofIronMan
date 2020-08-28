package com.starkIndustries.game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class StartGame {

    //DONE: print IronMan ASCII
    public void printBanner(){
        Path bannerPath = Paths.get("resources/banner.txt");
        String banner = "THE RISE OF IRON MAN";
        try {
            banner = Files.readString(bannerPath, StandardCharsets.US_ASCII);
            System.out.println(banner);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO: write a method to create UserName
    public void createUserName(){
        //take user input and create a json object
        Gson gson = new GsonBuilder().create();
        String startGame = "Please creat your user ID";
        String userID = Prompter.ask(startGame);
        User user = new User(userID);
        String userJson = gson.toJson(user, User.class);
        System.out.println(userJson);

        //write json object into user.json
        try {
            gson.toJson(user, new FileWriter("resources/user.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //TODO: write a method to load game to play

    //TODO: write a method to exit game

    //TODO: write a method to save game



    public static void main(String[] args) {
        StartGame starGame = new StartGame();
        starGame.printBanner();
        //starGame.createUserName();
    }
}
