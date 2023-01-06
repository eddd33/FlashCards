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
import java.util.List;
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

    @Override
    public void update() {
        if (app.getActiveDeck().getCards().size()==1){
            newLine();
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
        Label labelBestScore = new Label("None");
        Label labelLastScore = new Label("None");
        Label labelLastTime = new Label("None");
        int nbDecks = app.getDecks().size();
        tableau.add(labelnom,0,nbDecks);
        tableau.add(labelBestScore,1,nbDecks);
        tableau.add(labelLastScore,2,nbDecks);
        tableau.add(labelLastTime,3,nbDecks);
    }

    public void actualize(){
        String name = app.getActiveDeck().getName();
        for (Node n : tableau.getChildren()) {

            if (n instanceof Label l) {
                //int rang = GridPane.getRowIndex(l);
                if (l.getText().equals(name)) {
                    System.out.println("deck déjà présent");

                    List<Node> children = tableau.getChildren();
                    int index = children.indexOf(l);
                    if (children.get(index + 1) instanceof Label) {
                        Label bestS = (Label) children.get(index + 1);
                        double d = app.getActiveDeck().getBestScore();
                        bestS.setText(Double.toString(d));
                    }
                    if (children.get(index + 2) instanceof Label) {
                        Label lastS = (Label) children.get(index + 2);
                        double d = app.getActiveDeck().getLastScore();
                        lastS.setText(Double.toString(d));
                    }
                    if (children.get(index + 3) instanceof Label) {
                        Label lastT = (Label) children.get(index + 3);
                        lastT.setText(app.getActiveDeck().getLast_try());
                    }

                    break;
                }
            }
        }
    }

    @FXML
    public void changeToSelecCmd() {
        new ChangeSceneCommand(viewState,0).execute();
    }

}
