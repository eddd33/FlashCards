package com.example.flashcards.controller;

import com.example.flashcards.command.*;
import com.example.flashcards.models.Card;
import com.example.flashcards.models.DeckContainer;
import com.example.flashcards.view.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class CreateViewController implements Observer, Initializable {

    private DeckContainer app;
    private ViewState viewState;
    private ArrayList<String> tags;

    @FXML private TextField S_tag;
    @FXML private TextField tagAddTextField;
    @FXML private TextArea questionTextArea;
    @FXML private TextArea answerTextArea;
    @FXML private CheckBox twoSidedCheckBox;
    @FXML private ListView<String> selectedCardListView; // liste des decks
    @FXML private ListView<String> newCardListView; // cartes créées, mais pas dans un deck
    @FXML private ListView<String> addTagListView; // liste des tags


    /**
     * @param app
     * The DeckContainer that is used to control decks and cards.
     *
     * @param viewState
     * The ViewState used to know at any time in which Scene we are.
     */
    public CreateViewController(DeckContainer app, ViewState viewState){
        this.app=app;
        this.viewState=viewState;
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

        // newCardListView is the ListView of all the cards available
        newCardListView.setOnDragDetected(this::starDnd);

        // selectedCardListView is the ListView of all the cards in the deck
        selectedCardListView.setOnMouseClicked(this::clickHandle);
        selectedCardListView.setOnDragEntered(this::endDnD);

        //
        addTagListView.setOnKeyPressed(this::supprHandle);
    }



    /**
     * Implementation of update() method for Observer interface
     */
    public void update() {

        selectedCardListView.getItems().clear();
        newCardListView.getItems().clear();
        // We put cards in newCardListView except those already in the deck which go in selectedCardListView
        for (Card card : app.getCards()) {
            String name = card.getQuestion();
            System.out.println(app.getActiveDeck().isInDeck(card));
            if (app.getActiveDeck().isInDeck(card)) {
                // if there is no name : put a placeholder.
                selectedCardListView.getItems().add(Objects.requireNonNullElse(name, "<Question>"));
            } else {
                // if there is no name : put a placeholder.
                newCardListView.getItems().add(Objects.requireNonNullElse(name, "<Question>"));
            }
        }

        Card activeCard = app.getActiveCard();
        if (activeCard != null) {

            // We put the question if it exists
            if (activeCard.getQuestion() != null) {
                questionTextArea.setText(activeCard.getQuestion());
            } else {
                questionTextArea.clear();
            }

            // We put the answer if it exists
            if (activeCard.getAnswer() != null) {
                answerTextArea.setText(activeCard.getAnswer());
            } else {
                answerTextArea.clear();
            }

            // We select or not the twoSidedCheckBox accordingly to the twoSided variable of the card.
            // Since it's a boolean it can only be true or false and not null.
            twoSidedCheckBox.setSelected(activeCard.getTwoSided());

            // We add all the tags of the active deck in the ListView element.
            tags = app.getActiveDeck().getTagList();
            addTagListView.getItems().clear();
            addTagListView.getItems().addAll(tags);
        }
    }



    /*
     * Implementation of different methods for the pattern Command
     */

    @FXML
    public void changeToSelectCmd() {
        new ChangeSceneCommand(viewState,0).execute();
    }

    public void newCardCmd() {
        new NewCardCommand(app).execute();
    }

    public void validateChangeCmd() {

        String question = questionTextArea.getText();
        String answer = answerTextArea.getText();
        boolean twoSided = twoSidedCheckBox.isSelected();

        new ValidateInfoCardModCommand(app, question, answer, twoSided, tags).execute();
    }

    public void newTagCmd(){
        if (tagAddTextField.getText().equals("")){
            System.out.println("Veuillez entrer un tag");
        }
        else{
            String newTag = tagAddTextField.getText();
            tags.add(newTag);
            addTagListView.getItems().add(newTag);
            tagAddTextField.clear();
        }
    }



    public void searchByTag(){
        String searchTag = S_tag.getText();
        boolean isFound = false;
        ListView<Card> result = new ListView<>();
        for (Card card : app.getCards()) {
            for (String tag : card.getTagList()) {
                if (tag.equals(searchTag)) {
                    isFound = true;
                    result.getItems().add(card);
                    break;
                }
            }
            if (!isFound) {
                System.out.println("Card not found");
            }
        }
    }







    public void starDnd(MouseEvent event) {
        System.out.println("Début du drag");
        Dragboard db = newCardListView.startDragAndDrop(TransferMode.ANY);

        ClipboardContent content = new ClipboardContent();
        content.putString(newCardListView.getSelectionModel().getSelectedItem());
        System.out.println(newCardListView.getSelectionModel().getSelectedItem());
        db.setContent(content);
        event.consume();
    }



    public void endDnD(DragEvent event) {
        System.out.println("Début du drop");
        Dragboard db = event.getDragboard();
        if (db.hasString()) {
            String cardName = db.getString();
            Card draggedCard = app.getCard(cardName);
            if (draggedCard.getQuestion() != null) {
                app.getActiveDeck().addCard(draggedCard);
            }
        }
        event.consume();
        app.notifyObserver();
    }

    public void supprHandle(KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED && event.getCode() == KeyCode.BACK_SPACE) {
            int selectedIndex = addTagListView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < addTagListView.getItems().size()) {
                String toRemove = addTagListView.getSelectionModel().getSelectedItem();
                tags.remove(toRemove);
                update();
            }
        }
    }

    public void clickHandle(MouseEvent event) {
        int selectedIndex = selectedCardListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < selectedCardListView.getItems().size()) {
            app.setActiveCard(app.getActiveDeck().getCards().get(selectedIndex));
        }
    }
}
