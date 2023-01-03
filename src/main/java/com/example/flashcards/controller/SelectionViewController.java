package com.example.flashcards.controller;

import com.example.flashcards.models.DeckContainer;
import com.example.flashcards.view.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
public class SelectionViewController implements Observer{
    private DeckContainer app;
    private ViewState viewState;

    public SelectionViewController(DeckContainer app,ViewState ViewState){
        this.app=app;
        this.viewState=ViewState;
        app.addObserver(this);
        viewState.addObserver(this);
    }
    public void update(){

    }
    @FXML
    public void Leave(){
        Platform.exit();
    }

    @FXML
    public void changeScene(int etat){
        viewState.changeScene(etat);
    }

}
