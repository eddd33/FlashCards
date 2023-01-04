package com.example.flashcards.controller;

import com.example.flashcards.command.ChangeSceneCommand;
import com.example.flashcards.models.Card;
import com.example.flashcards.models.Deck;
import com.example.flashcards.models.DeckContainer;
import com.example.flashcards.view.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LearningViewController implements Observer, Initializable {
    private DeckContainer app;
    private ViewState viewState;
    private Deck deck;
    private ArrayList<Card> studyList;

    @FXML
    private Button affiche_reponse;
    @FXML
    private Label answerLabel;
    @FXML
    private Label questionLabel;

    @FXML
    private AnchorPane buttonContainer;



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
    public void initialize(URL location, ResourceBundle resources) {
        deck = new Deck("Le deck de test");
        Card C1 = new Card();
        C1.setQuestion("C'est la question 1");
        C1.setAnswer("C'est la réponse 1");
        Card C2 = new Card();
        C2.setQuestion("C'est la question 2");
        C2.setAnswer("C'est la réponse 2");
        Card C3 = new Card();
        C3.setQuestion("C'est la question 3");
        C3.setAnswer("C'est la réponse 3");
        deck.addCard(C1);
        deck.addCard(C2);
        deck.addCard(C3);
        studyList = deck.getCards();
        answerLabel.setOpacity(0);
    }



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

    @FXML
    public void reveal(ActionEvent e) {
        answerLabel.setOpacity(1);
        buttonContainer.getChildren().clear();
        HBox buttonBox = new HBox();
        Button easyButton = new Button();
        easyButton.setText("Facile");
        easyButton.setOnAction(event -> nextCard());
        Button midButton = new Button();
        midButton.setText("Moyen");
        Button hardButton = new Button();
        hardButton.setText("Difficile");
        Button lostButton = new Button();
        lostButton.setText("Impossible");
        buttonBox.getChildren().addAll(easyButton,midButton,hardButton,lostButton);
        buttonContainer.getChildren().add(buttonBox);
    }

    public void nextCard() {
        questionLabel.setText(studyList.get(0).getQuestion());
        answerLabel.setText(studyList.get(0).getAnswer());
        studyList.remove(0);
        answerLabel.setOpacity(0);
        buttonContainer.getChildren().clear();
        Button answerBut = new Button();
        answerBut.setText("Afficher la réponse");
        answerBut.setOnAction(event -> reveal(event));
        buttonContainer.getChildren().add(answerBut);
    }


}
