package com.example.flashcards.controller;

import com.example.flashcards.models.DeckContainer;
import com.example.flashcards.view.Observer;
import com.example.flashcards.view.ViewState;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class StatViewController implements Observer, Initializable {

    private DeckContainer app;
    private ViewState viewState;
    public StatViewController(DeckContainer app, ViewState viewState) {
        this.app = app;
        this.viewState = viewState;
        this.app.addObserver(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //récupérer les scores de chaque deck et les afficher dans l'ordre avec le nom du deck
    }

    @Override
    public void update() {
    }


}
