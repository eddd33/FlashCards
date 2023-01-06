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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

public class StatViewController implements Observer, Initializable {

    private DeckContainer app;
    private ViewState viewState;

    @FXML private GridPane tableau;

    @FXML private Label numberOfDecks;
    @FXML private Label best;
    @FXML private Label worst;


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
        numberOfDecks.setText("Nombre de decks : "+app.getDecks().size());
        best.setText("Deck le mieux réussi : "+cherche_best());
        worst.setText("Deck le moins réussi : "+cherche_worst());
    }

    public String cherche_best(){
        List<Deck> decks = app.getDecks();
        double best = 99999;
        String best_deck = "";
        for (Deck deck : decks){
            if (deck.getBestScore()<best){
                best = deck.getBestScore();
                best_deck = deck.getName();
            }
        }
        return best_deck;
    }

    public String cherche_worst(){
        List<Deck> decks = app.getDecks();
        double worst = 0;
        String worst_deck = "";
        for (Deck deck : decks){
            if (deck.getBestScore()>worst){
                worst = deck.getBestScore();
                worst_deck = deck.getName();
            }
        }
        return worst_deck;
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
        Label tailleDeck = new Label(String.valueOf(deck.getCards().size()));
        int nbDecks = app.getDecks().size();
        for (Node node : tableau.getChildren()) {
            if (node instanceof Label) {
                // Récupération des coordonnées du Label
                int rowIndex = GridPane.getRowIndex(node);
                int columnIndex = GridPane.getColumnIndex(node);
                if (rowIndex == nbDecks && columnIndex == 0) {
                    if (node instanceof Label l) {
                        l.setText("");
                    }
                }
                if (rowIndex == nbDecks && columnIndex == 1) {
                    if (node instanceof Label l && l.getText().equals("None")) {
                        l.setText("");
                    }
                }
                if (rowIndex == nbDecks && columnIndex == 2) {
                    if (node instanceof Label l && l.getText().equals("None")) {
                        l.setText("");
                    }
                }
                if (rowIndex == nbDecks && columnIndex == 3) {
                    if (node instanceof Label l && l.getText().equals("None")) {
                        l.setText("");
                    }
                }
                if (rowIndex == nbDecks && columnIndex == 4) {
                    if (node instanceof Label l) {
                        l.setText("");
                    }
                }
            }
        }

        tableau.add(labelnom,0,nbDecks);
        tableau.add(labelBestScore,1,nbDecks);
        tableau.add(labelLastScore,2,nbDecks);
        tableau.add(labelLastTime,3,nbDecks);
        tableau.add(tailleDeck,4,nbDecks);
    }

    public void actualize(){
        //String name = app.getActiveDeck().getName();
        for(Deck deck : app.getDecks()) {
            String name = deck.getName();

            for (Node n : tableau.getChildren()) {

                if (n instanceof Label l) {
                    //int rang = GridPane.getRowIndex(l);
                    if (l.getText().equals(name)) {
                        System.out.println("deck déjà présent");

                        List<Node> children = tableau.getChildren();
                        int index = children.indexOf(l);
                        if (children.get(index + 1) instanceof Label) {
                            Label bestS = (Label) children.get(index + 1);
                            double d = deck.getBestScore();
                            bestS.setText(Double.toString(d));
                        }
                        if (children.get(index + 2) instanceof Label) {
                            Label lastS = (Label) children.get(index + 2);
                            double d = deck.getLastScore();
                            lastS.setText(Double.toString(d));
                        }
                        if (children.get(index + 3) instanceof Label) {
                            Label lastT = (Label) children.get(index + 3);
                            lastT.setText(deck.getLast_try());
                        }

                        break;
                    }
                }
            }
        }
    }

    public void sortByName(){
        //trier les decks par ordre alphabétique
        ArrayList<Deck> decks = app.getDecks();
        ArrayList<Deck> sorted = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        for (Deck deck : decks){
            if (sorted.size()==0){
                sorted.add(deck);
                names.add(deck.getName());
            }
            else{
                for (int i=0; i<sorted.size(); i++){
                    if (deck.getName().compareTo(sorted.get(i).getName())<0){
                        sorted.add(i,deck);
                        names.add(i,deck.getName());
                        break;
                    }
                    else if (i==sorted.size()-1){
                        sorted.add(deck);
                        names.add(deck.getName());
                        break;
                    }
                }
            }
        }
        System.out.println(names);
    }

    @FXML
    public void changeToSelecCmd() {
        new ChangeSceneCommand(viewState,0).execute();
    }

}
