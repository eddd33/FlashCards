package com.example.flashcards.controller;

import com.example.flashcards.command.ChangeSceneCommand;
import com.example.flashcards.command.DuplicateDeckCommand;
import com.example.flashcards.command.ExitCommand;
import com.example.flashcards.command.NewDeckCommand;
import com.example.flashcards.models.Card;
import com.example.flashcards.models.Deck;
import com.example.flashcards.models.DeckContainer;
import com.example.flashcards.view.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SelectionViewController implements Observer, Initializable {
    private DeckContainer app;
    private ViewState viewState;

    @FXML
    private ArrayList<Deck> deckListTag; //liste des tags
    @FXML private VBox deckList;


    @FXML
    private HBox buttonContainer;
    /**
     * @param app
     * The DeckContainer that is used to control decks and cards.
     *
     * @param viewState
     * The ViewState used to know at any time in which Scene we are.
     */
    public SelectionViewController(DeckContainer app,ViewState viewState){
        this.app=app;
        this.viewState=viewState;
        this.deckListTag=new ArrayList<>();
        app.addObserver(this);
        viewState.addObserver(this);
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
    public void initialize(URL location, ResourceBundle resources) {
        deckListTag=app.getDecks();
        buttonContainer.getChildren().clear();
        HBox buttonBox = new HBox();
        Button byTagButton = new Button();
        byTagButton.setText("Recherche");
        TextField tag = new TextField();
        byTagButton.setOnAction(event -> searchByTag(tag.getText()));
        buttonBox.getChildren().addAll(byTagButton,tag);
        buttonContainer.getChildren().add(buttonBox);
        for (Deck d : deckListTag) {
            HBox newBox = new HBox();
            Button title = new Button();
            Button modify = new Button();
            Button bikini = new Button();
            Label description = new Label();

            title.setPrefSize(150.0,100.0);
            title.setStyle("-fx-background-color: grey;");
            title.setText(d.getName());
            title.setTextAlignment(TextAlignment.CENTER);
            title.setTextFill(Color.WHITE);
            title.setWrapText(true);
            title.setOnAction(event -> changeToLearnCmd(d));

            description.setPrefSize(480.0,100.0);
            description.setWrapText(true);
            description.setText(d.getDescription());

            modify.setText("Modifier");
            modify.setOnAction(event -> changeToLearnCmd(d));

            bikini.setText("Dupliquer");
            bikini.setOnAction(event -> duplicateDeckCmd(d));

            newBox.getChildren().addAll(title,description,modify,bikini);


            deckList.getChildren().add(newBox);
        }
    }



    /**
     * Implementation of update() method for Observer interface
     */
    public void update(){
        deckList.getChildren().clear();
        for (Deck d : deckListTag) {
            HBox newBox = new HBox();
            Button title = new Button();
            Button modify = new Button();
            Button bikini = new Button();
            Label description = new Label();

            title.setPrefSize(150.0, 100.0);
            title.setStyle("-fx-background-color: grey;");
            title.setText(d.getName());
            title.setTextAlignment(TextAlignment.CENTER);
            title.setTextFill(Color.WHITE);
            title.setWrapText(true);
            title.setOnAction(event -> changeToLearnCmd(d));

            description.setPrefSize(480.0, 100.0);
            description.setWrapText(true);
            description.setText(d.getDescription());

            modify.setText("Modifier");
            modify.setOnAction(event -> changeToCreateCmd(d));

            bikini.setText("Dupliquer");
            bikini.setOnAction(event -> duplicateDeckCmd(d));

            newBox.getChildren().addAll(title, description, modify, bikini);
            deckList.getChildren().add(newBox);
        }
    }



    /*
     * Implementation of different methods for the pattern Command
     */

    @FXML
    public void exitCmd() {
        new ExitCommand().execute();
    }

    @FXML
    public void newDeckCmd() {
        new NewDeckCommand(app, viewState).execute();
    }

    @FXML
    public void changeToLearnCmd(Deck deck) {
        app.setActiveDeck(deck);
        new ChangeSceneCommand(viewState,1).execute();
    }

    @FXML
    public void changeToCreateCmd(Deck deck) {
        app.setActiveDeck(deck);
        new ChangeSceneCommand(viewState,3).execute();
    }

    @FXML
    public void duplicateDeckCmd(Deck deck) {
        new DuplicateDeckCommand(app,viewState,deck).execute();
    }
    public void searchByTag(String tag) {
        deckListTag =new ArrayList<Deck>();

        buttonContainer.getChildren().clear();
        Button withoutTagButton = new Button();
        withoutTagButton.setText("Recherche");
        withoutTagButton.setOnAction(event -> withoutTag());
        buttonContainer.getChildren().add(withoutTagButton);
        for (Deck deck : app.getDecks()) {
            for (String tagFromList : deck.getTagList()) {
                if (tagFromList.equals(tag)) {
                    deckListTag.add(deck);
                }
            }
        }
        update();
    }
    public void withoutTag() {
        buttonContainer.getChildren().clear();
        HBox buttonBox = new HBox();
        Button byTagButton = new Button();
        byTagButton.setText("Recherche");
        TextField tag = new TextField();
        byTagButton.setOnAction(event -> searchByTag(tag.getText()));
        buttonBox.getChildren().addAll(byTagButton,tag);
        buttonContainer.getChildren().add(buttonBox);
        deckListTag = app.getDecks();
        update();
    }

}
