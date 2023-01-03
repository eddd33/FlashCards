package com.example.flashcards.controller;

import com.example.flashcards.models.DeckContainer;
import com.example.flashcards.view.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.util.ArrayList;

public class SceneController{
    private Stage stage;
    private ArrayList<Scene> Scenes;

    public SceneController(Stage stage){
        this.stage=stage;
    }
    public void addScene(Scene scene){
        this.Scenes.add(scene);
    }
    @FXML
    public void Leave(){
        Platform.exit();
    }
    @FXML
    public void changeScene(int etat){
        ViewState.changeScene(etat);
    }
}
