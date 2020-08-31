package com.starkIndustries.game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class StartGame {
    private StoryTeller storyTeller = new StoryTeller();
    private File path = new File("resources/story.json");
    private JsonObject newStory = storyTeller.getStory(path);
    public Scanner scanner = new Scanner(System.in);

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

    //TODO: write a method to choose to start new game or load game
    public void initGame(){
        String userInput = scanner.nextLine();
        if (Integer.parseInt(userInput) == 1) {
            storyTeller.runScript(newStory,"0");
        }else if (Integer.parseInt(userInput) == 2){
            readGameFile();
        }else if (userInput.toUpperCase().equals("EXIT")){
            exitGame();
        }else {
            System.out.println("Please enter a valid input");
            initGame();
        }
    }


    //TODO: write a method to load game to play
    public void readGameFile(){
        //TODO: method to read saved Json

    }

    //TODO: write a method to exit game
    public void exitGame(){
        System.out.println("do you want to save your game before exit? Y/N?");
        String userInput = scanner.nextLine();
        if (userInput.toUpperCase().equals("Y")){
            saveGame("0");
            System.out.println("thanks for playing!");
        } else if (userInput.toUpperCase().equals("N")){
            System.out.println("thanks for playing!");
        } else {
            System.out.println("Please enter a valid input");
            exitGame();
        }
    }

    //TODO: write a method to save game
    public void saveGame(String sceneNum){

        String fileToSave = Prompter.ask("please enter a name for your game file");
        GameFile gameFile = new GameFile(fileToSave, sceneNum);
        try {
            Gson gson = new Gson();
            Writer writer = Files.newBufferedWriter(Paths.get("resources/gameFile.json"));
            gson.toJson(gameFile, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(gameFile);




    }


    public static void main(String[] args) {
        StartGame startGame = new StartGame();
        startGame.printBanner();
        startGame.initGame();
        //startGame.saveGame("0");
    }
}
