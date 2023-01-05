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
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LearningViewController implements Observer, Initializable {

    private DeckContainer app;
    private ViewState viewState;
    private Deck deck;
    private ArrayList<Card> studyList;

    private double current_score;

    @FXML private Button affiche_reponse;
    @FXML private Label answerLabel;
    @FXML private Label questionLabel;
    @FXML private AnchorPane buttonContainer;

    @FXML private Text goodCards;
    @FXML private Text mehCards;
    @FXML private Text badCards;


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
        this.app.addObserver(this);
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
        System.out.print("entrée init");
        if (deck != null && deck.getCards().size() != 0) {
            deck = app.getActiveDeck();
            studyList = deck.getCards();
        }
        else {
            studyList = new ArrayList<Card>();
            Card C1 = new Card();
            C1.setQuestion("Question 1");
            C1.setAnswer("Réponse 1");
            Card C2 = new Card();
            C2.setQuestion("Question 2");
            C2.setAnswer("Réponse 2");
            Card C3 = new Card();
            C3.setQuestion("Question 3");
            C3.setAnswer("Réponse 3");
            deck = new Deck("Paquet test");
            studyList.add(C1);
            studyList.add(C2);
            studyList.add(C3);
            deck.getCards().addAll(studyList);
        }
        sortByDiff(studyList);
        int goodNum= 0;
        int midNum = 0;
        int hardNum =0;
        for (Card card :
                studyList) {
            if (card.getDifficulty()<1) {
                goodNum++;
            } else if (card.getDifficulty()<1.7) {
                midNum++;
            }
            else {
                hardNum++;
            }
        }
        goodCards.setText(Integer.toString(goodNum));
        mehCards.setText(Integer.toString(midNum));
        badCards.setText(Integer.toString(hardNum));
        answerLabel.setText(studyList.get(0).getAnswer());
        questionLabel.setText(studyList.get(0).getQuestion());
        answerLabel.setOpacity(0);
    }



    /**
     * Implementation of update() method for Observer interface
     */
    public void update(){
        System.out.println("entrée update");
        buttonContainer.getChildren().clear();
        Button answerBut = new Button();
        if (studyList.size() != 0) {
            questionLabel.setText(studyList.get(0).getQuestion());
            answerLabel.setText(studyList.get(0).getAnswer());
            answerLabel.setOpacity(0);
            answerBut.setOnAction(event -> reveal(event));
            answerBut.setText("Afficher la réponse");
        }
        else {
            double score = calcScore();
            if (app.getActiveDeck().getBestScore() > score) {
                app.getActiveDeck().setBestScore(score);
            }
            app.getActiveDeck().setLastScore(score);
            questionLabel.setText("Révision terminée");
            answerLabel.setText("Félicitation Shinji! :clap:");
            answerBut.setOnAction(event -> changeToSelecCmd());
            answerBut.setText("Retourner à la selection des paquets");
        }
        buttonContainer.getChildren().add(answerBut);
        int goodNum= 0;
        int midNum = 0;
        int hardNum =0;
        for (Card card :
                studyList) {
            if (card.getDifficulty()<1) {
                goodNum++;
            } else if (card.getDifficulty()<1.7) {
                midNum++;
            }
            else {
                hardNum++;
            }
        }
        goodCards.setText(Integer.toString(goodNum));
        mehCards.setText(Integer.toString(midNum));
        badCards.setText(Integer.toString(hardNum));
    }



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
            easyButton.setOnAction(event -> {
                nextCard(0.9);
                current_score = current_score +1;
            });
            Button midButton = new Button();
            midButton.setText("Moyen");
            midButton.setOnAction(event -> {
                    nextCard(1);
                    current_score = current_score + 2;
            });
            Button hardButton = new Button();
            hardButton.setText("Difficile");
            hardButton.setOnAction(event -> {
                nextCard(1.2);
                current_score = current_score + 3;
            });
            buttonBox.getChildren().addAll(easyButton,midButton,hardButton);
            buttonContainer.getChildren().add(buttonBox);
    }

    public void nextCard(double coef) {
        if (studyList.size() > 0) {
            Card firstCard = studyList.get(0);
            firstCard.setDifficulty(firstCard.getDifficulty() * coef);
            app.getCard(firstCard.getQuestion()).setDifficulty(firstCard.getDifficulty());
            studyList.remove(0);
            if (firstCard.getDifficulty() >= 1) {
                insertByDiff(studyList, firstCard);
            }
        }
        System.out.println("Avant Notif");
        app.notifyObserver();
    }

    private void sortByDiff(ArrayList<Card> List) {
        ArrayList<Card> bufferList = new ArrayList<Card>();
        for (Card card : List) {
            if (bufferList.size() == 0){
                bufferList.add(card);
            }
            else {
                int i =0;
                while (i<bufferList.size() && card.getDifficulty() <= bufferList.get(i).getDifficulty()){
                    i++;
                }
                if (i == bufferList.size()) {
                    bufferList.add(card);
                }
                else {
                    bufferList.add(i,card);
                }
            }
        }
        studyList = bufferList;
    }

    private void insertByDiff(ArrayList<Card> List, Card element) {
        int i = 0;
        while (i < List.size() && element.getDifficulty() < List.get(i).getDifficulty()) {
            i ++;
        }
        if (i+1 >= List.size()) {
            List.add(element);
        }
        else {
            List.add(i+1,element);
        }
    }

    private double calcScore() {
        int cardNum = app.getActiveDeck().getCards().size();
        return current_score/cardNum;
    }


}
