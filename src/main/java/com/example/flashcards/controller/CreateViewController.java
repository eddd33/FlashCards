package com.example.flashcards.controller;

import com.example.flashcards.models.DeckContainer;
import com.example.flashcards.view.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
public class CreateViewController implements Observer{
    private DeckContainer app;
    private ViewState ViewState;

    public CreateViewController(DeckContainer app, ViewState ViewState){
        this.app=app;
        this.ViewState=ViewState;
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

}
