package com.example.flashcards.controller;

import com.example.flashcards.command.ChangeSceneCommand;
import com.example.flashcards.models.DeckContainer;
import com.example.flashcards.view.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class LearningViewController implements Observer, Initializable {
    private DeckContainer app;
    private ViewState viewState;

    @FXML
    private Button affiche_reponse;



    /**
     * @param app
     * The DeckContainer that is used to control decks and cards.
     *
     * @param viewState
     * The ViewState used to know at any time in which Scene we are.
     */
    public LearningViewController(DeckContainer app, ViewState viewState){
        this.app=app;
        this.viewState=viewState;
    }



    /**
     * Implementation of initialize method for Initializable interface :
     *
     * @param location
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param resources
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {}



    /**
     * Implementation of update() method for Observer interface
     */
    public void update(){}



    /*
     * Implementation of different methods for the pattern Command
     */

    @FXML
    public void changeToSelecCmd() {
        new ChangeSceneCommand(viewState,0).execute();
    }


    public void reveal() {}


}
