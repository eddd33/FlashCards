package com.example.flashcards.controller;

import com.example.flashcards.command.ChangeSceneCommand;
import com.example.flashcards.models.DeckContainer;
import com.example.flashcards.view.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateViewController implements Observer, Initializable {
    private DeckContainer app;
    private ViewState viewState;

    public CreateViewController(DeckContainer app, ViewState ViewState){
        this.app=app;
        this.viewState=ViewState;
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
    public void changeToSelecCmd() {
        new ChangeSceneCommand(viewState,0).execute();
    }

    public void searchByTag(){

    }

}
