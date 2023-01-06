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

    @FXML private static GridPane tableau;

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

    @Override
    public void update() {
        if (app.getActiveDeck().getCards().size()==1){
            newLine();
        }
        System.out.println(app.getActiveDeck().getName());
        System.out.println(app.getActiveDeck().getLast_try());
        //la condition ci-dessous est fausse
        //la condition doit etre si lastTry != null
        //ensuite le pb est que update n'est pas éxécuté quand on achève une révision et donc lorsque le score est calculé

        if (app.getActiveDeck().getLast_try()!="Never") {

            Deck deck = app.getActiveDeck();
            String name = deck.getName();
            Label labelnom = new Label(name);
            double Bscore = deck.getBestScore();
            Label labelBestScore = new Label(String.valueOf(Bscore));
            double Lscore = deck.getLastScore();
            Label labelLastScore = new Label(String.valueOf(Lscore));
            System.out.println(" Lscore : " + Lscore);
            String lastTry = deck.getLast_try();
            Label labelLastTime = new Label(lastTry);
            int nbDecks = app.getDecks().size();
            //ensuite ici les labels ne doivent pas être ajoutés
            //on doit simplement modifier les labels existants sur la bonne ligne
            tableau.add(labelnom,0,nbDecks);
            tableau.add(labelBestScore,1,nbDecks);
            tableau.add(labelLastScore,2,nbDecks);
            tableau.add(labelLastTime,3,nbDecks);
        }
        System.out.println("update");
    }

    public void newLine(){
        Deck deck = app.getActiveDeck();
        String name = deck.getName();
        Label labelnom = new Label(name);
        for (Node n : tableau.getChildren()) {
            if (n instanceof Label l) {
                if (l.getText().equals(name)) {
                    System.out.println("deck déjà présent");
                    return;
                }
            }
        }
        Label labelBestScore = new Label("0");
        Label labelLastScore = new Label("0");
        Label labelLastTime = new Label("Never");
        int nbDecks = app.getDecks().size();
        tableau.add(labelnom,0,nbDecks);
        tableau.add(labelBestScore,1,nbDecks);
        tableau.add(labelLastScore,2,nbDecks);
        tableau.add(labelLastTime,3,nbDecks);
    }

    public static void actualize(String bestS, String lastS, String lastT, int nbDecks, String name){
        for (Node n : tableau.getChildren()) {
            if (n instanceof Label l) {
                if (l.getText().equals(name)) {
                    System.out.println("deck déjà présent");
                    int rang = tableau.getRowIndex(l);
                    Label nextLabel1 = (Label) tableau.getChildren().get(rang + 1);
                    Label nextLabel2 = (Label) tableau.getChildren().get(rang + 2);
                    Label nextLabel3 = (Label) tableau.getChildren().get(rang + 3);

                    return;
                }
            }
        }
    }

    @FXML
    public void changeToSelecCmd() {
        new ChangeSceneCommand(viewState,0).execute();
    }

}