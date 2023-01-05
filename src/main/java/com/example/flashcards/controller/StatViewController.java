package com.example.flashcards.controller;

import com.example.flashcards.Main;
import com.example.flashcards.command.ChangeSceneCommand;
import com.example.flashcards.models.Deck;
import com.example.flashcards.models.DeckContainer;
import com.example.flashcards.view.Observer;
import com.example.flashcards.view.ViewState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

public class StatViewController implements Observer, Initializable {

    private DeckContainer app;
    private ViewState viewState;

    @FXML private GridPane tableau;

    public StatViewController(DeckContainer app, ViewState viewState) {
        this.app = app;
        this.viewState = viewState;
        app.addObserver(this);
        viewState.addObserver(this);
    }

    //public StatViewController(){}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //récupérer les scores de chaque deck et les afficher dans l'ordre avec le nom du deck
    }


    private boolean test = false;
    @Override
    public void update() {
        if (app.getActiveDeck().getCards().size()==1){
            newLine();
        }
        System.out.println(app.getActiveDeck().getName());
        if (app.getActiveDeck().getCards().size()==3) {
            System.out.println("siiiiiiiiiiii");
            Deck deck = app.getActiveDeck();
            String name = deck.getName();
            Label labelnom = new Label(name);
            double Bscore = deck.getBestScore();
            Label labelBestScore = new Label(toString().valueOf(Bscore));
            double Lscore = deck.getLastScore();
            Label labelLastScore = new Label(toString().valueOf(Lscore));
            System.out.println(" Lscore : " + Lscore);
            Calendar lastTry = deck.getLast_try();
            Label labelLastTime = new Label(toString().valueOf(lastTry));
            int nbDecks = app.getDecks().size();
            tableau.add(labelnom,0,nbDecks);
            tableau.add(labelBestScore,1,nbDecks);
            tableau.add(labelLastScore,2,nbDecks);
            tableau.add(labelLastTime,3,nbDecks);
        }
        System.out.println("update");
        /*System.out.println("StatView update");
        Deck deck = app.getActiveDeck();
        String name = deck.getName();
        System.out.println("Deck name : " + name);
        //Node node = root.lookup("#nomDeck");
        if (deck != null){
            System.out.println("Deck non null");
        }
        if (deck != null) {
            System.out.println("StatView update : deck != null && node != null");
            //int rang = tableau.getRowIndex(node);
            //tableau.getChildren().get(rang).get(0).setText(name);
            for (Node n : tableau.getChildren()) {
                System.out.println(n);
                if (tableau.getColumnIndex(n) == 0) {
                    System.out.println("StatView update : GridPane.getColumnIndex(n) == 0");
                    //((Label) n).setText(name);
                    Label label = (Label) n;
                    label.setText(name);
                }
                if (GridPane.getColumnIndex(n) == 1) {
                    Label label = (Label) n;
                    label.setText(name);
                }
                if (tableau.getColumnIndex(n) == 2) {
                    Label label = (Label) n;
                    label.setText(name);
                }
                if (tableau.getColumnIndex(n) == 3) {
                    Label label = (Label) n;
                    label.setText(name);
                }
            }
        }*/
        //
    }

    public void newLine(){
        Deck deck = app.getActiveDeck();
        String name = deck.getName();
        Label labelnom = new Label(name);
        Label labelBestScore = new Label("0");
        Label labelLastScore = new Label("0");
        Label labelLastTime = new Label("0");
        int nbDecks = app.getDecks().size();
        tableau.add(labelnom,0,nbDecks);
        tableau.add(labelBestScore,1,nbDecks);
        tableau.add(labelLastScore,2,nbDecks);
        tableau.add(labelLastTime,3,nbDecks);
    }

    @FXML
    public void changeToSelecCmd() {
        new ChangeSceneCommand(viewState,0).execute();
    }

}
