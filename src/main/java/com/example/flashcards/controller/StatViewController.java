package com.example.flashcards.controller;

import com.example.flashcards.view.Observer;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class StatViewController implements Observer, Initializable {
    public StatViewController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //récupérer les scores de chaque deck et les afficher dans l'ordre avec le nom du deck
    }

    @Override
    public void update() {
    }


}
