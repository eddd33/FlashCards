package com.example.flashcards.controller;

import com.example.flashcards.command.ExitCommand;
import com.example.flashcards.command.ValidateInfoDeckModCommand;
import com.example.flashcards.models.Deck;
import com.example.flashcards.models.DeckContainer;
import com.example.flashcards.view.Observer;
import com.example.flashcards.view.ViewState;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DeckEditViewController implements Initializable, Observer {

    private DeckContainer container;
    private ViewState viewState;
    private ArrayList<String> tags;


    @FXML private TextField deckNameTextField;
    @FXML private TextField deckAuthorTextField;
    @FXML private TextArea deckDescTextArea;
    @FXML private TextField addTagTextField;
    @FXML private ListView<String> deckTagListView;



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
        this.container.addObserver(this);
        this.viewState.addObserver(this);
        tags = new ArrayList<>();
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

        deckTagListView.setOnKeyPressed(this::handle);

        deckTagListView.getItems().clear();
        deckTagListView.getItems().addAll(tags);
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
        tags = container.getActiveDeck().getTagList();

        deckTagListView.getItems().clear();
        deckTagListView.getItems().addAll(tags);
    }



    /*
     * Implementation of methods for the Command pattern.
     */

    public void ajouterTagCmd() {
        if (addTagTextField.getText().equals("")){
            System.out.println("Veuillez entrer un tag");
        }
        else {
            String tag = addTagTextField.getText();
            tags.add(tag);
            deckTagListView.getItems().add(tag);
            addTagTextField.clear();
        }
    }

    public void validateCmd() {
        String name = deckNameTextField.getText();
        String auth = deckAuthorTextField.getText();
        String desc = deckDescTextArea.getText();
        new ValidateInfoDeckModCommand(container,viewState,name,auth,desc,tags).execute();
    }

    public void exitApplicationCmd() {
        new ExitCommand().execute();
    }



    /**
     * Method used to delete a selected tag element when the BackSpace key is pressed
     *
     * @param event
     * Key Event, need to be of KeyCode : BACK_SPACE
     */
    public void handle(KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            if(event.getCode() == KeyCode.BACK_SPACE) {
                int selectedIndex = deckTagListView.getSelectionModel().getSelectedIndex();
                if (selectedIndex >= 0 && selectedIndex < deckTagListView.getItems().size()) {
                    String toRemove = deckTagListView.getSelectionModel().getSelectedItem();
                    tags.remove(toRemove);
                    update();
                }
            }
        }
    }
}
