package com.example.flashcards.controller;

import com.example.flashcards.command.ExitCommand;
import com.example.flashcards.command.ValidateInfoDeckModCommand;
import com.example.flashcards.models.Deck;
import com.example.flashcards.models.DeckContainer;
import com.example.flashcards.view.Observer;
import com.example.flashcards.view.ViewState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DeckEditViewController implements Initializable, Observer {

    private DeckContainer container;
    private ViewState viewState;


    @FXML private TextField deckNameTextField;
    @FXML private TextField deckAuthorTextField;
    @FXML private TextArea deckDescTextArea;



    /**
     * @param container
     * The DeckContainer that is used to control decks and cards.
     *
     * @param viewState
     * The ViewState used to know at any time in which Scene we are.
     */
    public DeckEditViewController(DeckContainer container, ViewState viewState){
        this.container = container;
        this.viewState = viewState;
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
        Deck deck = container.getActiveDeck();
        deckNameTextField.setText(deck.getName());
        deckAuthorTextField.setText(deck.getAuthor());
        deckDescTextArea.setText( deck.getDescription());
    }



    /**
     * Implementation of update() method for Observer interface
     */
    @Override
    public void update() {
        Deck deck = container.getActiveDeck();
        deckNameTextField.setText(deck.getName());
        deckAuthorTextField.setText(deck.getAuthor());
        deckDescTextArea.setText( deck.getDescription());
    }



    /*
     * Implementation of methods for the Command pattern.
     */

    public void validateCmd() {
        String name = deckNameTextField.getText();
        String auth = deckAuthorTextField.getText();
        String desc = deckDescTextArea.getText();
        new ValidateInfoDeckModCommand(container,viewState,name,auth,desc).execute();
    }

    public void exitApplicationCmd() {
        new ExitCommand().execute();
    }
}
