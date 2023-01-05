package com.example.flashcards.controller;

import com.example.flashcards.command.*;
import com.example.flashcards.models.Deck;
import com.example.flashcards.models.DeckContainer;
import com.example.flashcards.view.*;
import com.example.flashcards.saveDeckContainerProcedure;
import com.example.flashcards.loadDeckContainerProcedure;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SelectionViewController implements Observer, Initializable {

    private DeckContainer app;
    private ViewState viewState;

    @FXML private ArrayList<Deck> deckListTag; //liste des tags
    @FXML private VBox deckList;
    @FXML private HBox buttonContainer;


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

        // creation du bouton pour effectuer la demande de recherche.
        Button byTagButton = new Button();
        byTagButton.setText("Recherche");
        // creation d'un TextField permettant d'entrer la demande de la recherche.
        TextField tag = new TextField();
        // Un event du bouton entraînera l'appel de la méthode searchByTag sur le texte du TexteField.
        byTagButton.setOnAction(event -> searchByTag(tag.getText()));
        // ajout des éléments dans la HBox, défini dans le .fxml
        buttonContainer.getChildren().clear();
        buttonContainer.getChildren().addAll(byTagButton,tag);

        // on récupère la liste des decks à afficher, par défaut, il s'agit de tous.
        deckListTag = app.getDecks();
        for (Deck d : deckListTag) {
            HBox newBox = new HBox();
            Button title = new Button();
            Button modify = new Button();
            Button bikini = new Button();
            Button delete = new Button();
            Label description = new Label();

            // settings du titre.
            title.setPrefSize(150.0,100.0);
            title.setStyle("-fx-background-color: grey;");
            title.setText(d.getName());
            title.setTextAlignment(TextAlignment.CENTER);
            title.setTextFill(Color.WHITE);
            title.setWrapText(true);
            title.setOnAction(event -> changeToLearnCmd(d));

            // settings de la description.
            description.setPrefSize(480.0,100.0);
            description.setWrapText(true);
            description.setText(d.getDescription());

            // settings du bouton Modifier qui envoie sur la page de gestion du deck.
            modify.setText("Modifier");
            modify.setOnAction(event -> changeToCreateCmd(d));

            // settings du bouton Dupliquer qui duplique le deck et envoie sur la page de gestion de la copie.
            bikini.setText("Dupliquer");
            bikini.setOnAction(event -> duplicateDeckCmd(d));

            // settings du bouton Supprimer qui supprime le deck.
            delete.setText("Supprimer");
            delete.setOnAction(event -> deleteDeckCmd(d));

            // Ajout de tous les éléments à la HBox, puis à la VBox global.
            newBox.getChildren().addAll(title,description,modify,bikini,delete);
            deckList.getChildren().add(newBox);
        }
    }

    @FXML
    public void saveProcedure() {
        try {
            new saveDeckContainerProcedure(app).save();
        }
        catch (IOException Exception) {
            throw new RuntimeException(Exception);
        }
    }
    @FXML
    public void loadProcedure() {
        try {
            new loadDeckContainerProcedure(app).load();
            deckListTag = app.getDecks();
            update();
        }
        catch (IOException Exception) {
            throw new RuntimeException(Exception);
        }
    }



    /**
     * Implementation of update() method for Observer interface
     */
    public void update() {

        // on vide la VBox qui contient l'affichage des decks
        deckList.getChildren().clear();
        for (Deck d : deckListTag) {
            HBox newBox = new HBox();
            Button title = new Button();
            Button modify = new Button();
            Button bikini = new Button();
            Button delete = new Button();
            Label description = new Label();

            // settings du titre.
            title.setPrefSize(150.0, 100.0);
            title.setStyle("-fx-background-color: grey;");
            title.setText(d.getName());
            title.setTextAlignment(TextAlignment.CENTER);
            title.setTextFill(Color.WHITE);
            title.setWrapText(true);
            title.setOnAction(event -> changeToLearnCmd(d));

            // settings de la description.
            description.setPrefSize(480.0, 100.0);
            description.setWrapText(true);
            description.setText(d.getDescription());

            // settings du bouton Modifier qui envoie sur la page de gestion du deck.
            modify.setText("Modifier");
            modify.setOnAction(event -> changeToCreateCmd(d));

            // settings du bouton Dupliquer qui duplique le deck et envoie sur la page de gestion de la copie.
            bikini.setText("Dupliquer");
            bikini.setOnAction(event -> duplicateDeckCmd(d));

            // settings du bouton Supprimer qui supprime le deck.
            delete.setText("Supprimer");
            delete.setOnAction(event -> deleteDeckCmd(d));

            // Ajout de tous les éléments à la HBox, puis à la VBox global.
            newBox.getChildren().addAll(title, description, modify, bikini, delete);
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
        app.setActiveCard(deck.getCards().get(0));
        new ChangeSceneCommand(viewState,2).execute();
    }

    @FXML
    public void changeToStatCmd() {
        new ChangeSceneCommand(viewState,4).execute();
    }

    @FXML
    public void duplicateDeckCmd(Deck deck) {
        new DuplicateDeckCommand(app,viewState,deck).execute();
    }

    @FXML
    public void deleteDeckCmd(Deck deck) {
        new DeleteDeckCommand(app,deck).execute();
    }



    /*
     * Implementation of methods for the search by tag
     */

    /**
     * Method used when the search button is pressed.
     * Create an empty list of deck that will be filled only with deck with corresponding tag.
     *
     * @param tag
     * The tag that is research.
     * WARNING : No spelling error are accepted.
     */
    public void searchByTag(String tag) {
        deckListTag = new ArrayList<>();

        // creating a new button that will replace the research button and will reset the search when pressed.
        Button withoutTagButton = new Button();
        withoutTagButton.setText("Voir tout");
        withoutTagButton.setOnAction(event -> withoutTag());
        buttonContainer.getChildren().clear();
        buttonContainer.getChildren().add(withoutTagButton);

        // loop to check if each deck as the research tag.
        for (Deck deck : app.getDecks()) {
            for (String tagFromList : deck.getTagList()) {
                if (tagFromList.equals(tag)) {
                    deckListTag.add(deck);
                }
                break;
            }
        }
        update();
    }



    /**
     * Method used when the reset search button is pressed.
     * Puts back the complete list of deck to be display.
     */
    public void withoutTag() {
        deckListTag = app.getDecks();

        // creation of the research Button and TextField;
        Button byTagButton = new Button();
        TextField tag = new TextField();
        byTagButton.setText("Recherche");
        byTagButton.setOnAction(event -> searchByTag(tag.getText()));
        buttonContainer.getChildren().clear();
        buttonContainer.getChildren().addAll(byTagButton,tag);

        update();
    }
}
