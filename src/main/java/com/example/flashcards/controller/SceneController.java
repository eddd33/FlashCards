package com.example.flashcards.controller;

import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.ArrayList;

public class SceneController{
    private Stage stage;
    private ArrayList<Scene> scenes;

    public SceneController(Stage stage){
        this.stage=stage;
        scenes = new ArrayList<>();
    }

    public void addScene(Scene scene){
        this.scenes.add(scene);
    }

    public void changeScene(int i) {
        stage.setScene(scenes.get(i));
    }
}
