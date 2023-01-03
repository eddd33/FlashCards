package com.example.flashcards.controller;

import com.example.flashcards.models.DeckContainer;
import com.example.flashcards.view.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class LearningViewController implements Observer, Initializable {
    private DeckContainer app;
    private ViewState ViewState;

    @FXML
    private Button affiche_reponse;



    public LearningViewController(DeckContainer app, ViewState ViewState){
        this.app=app;
        this.ViewState=ViewState;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void update(){

    }
    @FXML
    public void Leave(){
        Platform.exit();
    }
    @FXML
    public void changeScene(int etat){
        ViewState.changeScene(etat);
    }

    public void reveal(){

    }


}
