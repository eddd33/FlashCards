package com.example.flashcards.controller;

import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.ArrayList;

public class SceneController{
    private Stage stage;
    private ArrayList<Scene> scenes;

    /**
     * @param stage
     * The main Stage for the application.
     */
    public SceneController(Stage stage){
        this.stage = stage;
        scenes = new ArrayList<>();
    }



    /**
     * @param scene
     * New Scene to add to the ArrayList scenes.
     */
    public void addScene(Scene scene){
        this.scenes.add(scene);
    }



    /**
     * @param i
     * The index of the scene we want to switch to.
     * The following index are for :
     * 0 -> SelectionView
     * 1 -> LearningView
     * 2 -> CreateView
     * 3 -> DeckEditView
     * 4 -> StatView
     */
    public void changeScene(int i) {
        stage.setScene(scenes.get(i));
    }
}
