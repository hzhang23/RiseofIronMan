package com.starkIndustries.game;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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

    //DONE: write a method to choose to start new game or load game
    public void initGame(){
        //printBanner();
        String userInput = scanner.nextLine();
        if (userInput.equals("1")) {
            storyTeller.runScript(newStory,"0");
        }else if (userInput.equals("2")){
            readGameFile();
        }else if (userInput.toUpperCase().equals("EXIT")){
            System.out.println("thanks for playing!");
        }else {
            System.out.println("Please enter a valid input");
            initGame();
        }
    }


    //DONE: write a method to load game to play
    public void readGameFile(){
        try {
            JsonElement gameFile = JsonParser.parseReader(new FileReader("resources/gameFile.json"));
            JsonObject gameFileObj = gameFile.getAsJsonObject();
            Set<String> keySet = gameFileObj.keySet();
            List<String> keyList = new ArrayList<>(keySet);
            System.out.println("loading game file:");
            for (int i = 0 ; i < keyList.size(); i++){
                System.out.println("No."+ (i+1) + " game file is: " + keyList.get(i));
            }
           String userInput =  Prompter.ask("Please enter the number for which game file you would like to continue " +
                   "to play " + "with:");
            //TODO: write a if statement to validate keyList has the the input
            try{
                Integer chosenIdx = Integer.parseInt(userInput) - 1;
                String scene = gameFileObj.get(keyList.get(chosenIdx)).getAsString();
                storyTeller.runScript(newStory, scene);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Please enter a valid input");
                readGameFile();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //DONE: method to save game
    public void saveGame(String sceneNum){
        String fileToSave = Prompter.ask("please enter a name for your game file");
        try {
            Gson gson = new Gson();
            JsonElement gameFile = JsonParser.parseReader(new FileReader("resources/gameFile.json"));
            Type fileMap = new TypeToken<Map<String, String>>(){}.getType();
            Map<String,String> newMap = gson.fromJson(gameFile, fileMap);
            System.out.println(newMap);
            newMap.put(fileToSave,sceneNum);
            Writer writer = new FileWriter("resources/gameFile.json");
            gson.toJson(newMap, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //DONE: method exit game
    public void exitGame(String scene){
        System.out.println("do you want to save your game before exit? Y/N?");
        String userInput = scanner.nextLine();
        if (userInput.toUpperCase().equals("Y")){
            saveGame(scene);
            System.out.println("thanks for playing!");
        } else if (userInput.toUpperCase().equals("N")){
            System.out.println("thanks for playing!");
        } else {
            System.out.println("Please enter a valid input");
            exitGame(scene);
        }
    }
}
