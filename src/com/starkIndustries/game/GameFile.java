package com.starkIndustries.game;

public class GameFile {
  private String gameFile;
  private String scene;

    //Ctor
    public GameFile(String gameFile, String scene) {
        this.gameFile = gameFile;
        this.scene = scene;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    //getter and setter
    public String getGameFile() {
        return gameFile;
    }

    public void setGameFile(String gameFile) {
        this.gameFile = gameFile;
    }
}
