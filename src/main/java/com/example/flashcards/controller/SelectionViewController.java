package com.example.flashcards.controller;

import com.example.flashcards.command.ChangeSceneCommand;
import com.example.flashcards.models.DeckContainer;
import com.example.flashcards.view.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class SelectionViewController implements Observer, Initializable {
    private DeckContainer app;
    private ViewState viewState;

    public SelectionViewController(DeckContainer app,ViewState ViewState){
        this.app=app;
        this.viewState=ViewState;
        app.addObserver(this);
        viewState.addObserver(this);
    }

    public void initialize(URL location, ResourceBundle resources) {

    }
    public void update(){

    }
    @FXML
    public void Leave(){
        Platform.exit();
    }


    @FXML
    public void changeToLearnCmd() {
        new ChangeSceneCommand(viewState,1).execute();
    }

    @FXML
    public void changeToCreateCmd() {
        new ChangeSceneCommand(viewState,2).execute();
    }

}
