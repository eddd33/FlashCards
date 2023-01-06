package com.example.flashcards.controller;

import com.example.flashcards.command.*;
import com.example.flashcards.models.Card;
import com.example.flashcards.models.DeckContainer;
import com.example.flashcards.view.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class CreateViewController implements Observer, Initializable {

    private DeckContainer app;
    private ViewState viewState;
    private ArrayList<String> tags;
    private ArrayList<Card> taggedCards;

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
        tags = new ArrayList<>();
        taggedCards = new ArrayList<>();
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
        newCardListView.setStyle("-fx-selection-bar:blue;");
        newCardListView.setOnMouseClicked(this::clickHandle);
        newCardListView.setOnKeyPressed(this::deleteCardHandle);
        newCardListView.setOnDragDetected(this::starDnd);

        // selectedCardListView is the ListView of all the cards in the deck
        selectedCardListView.setStyle("-fx-selection-bar:green;");
        selectedCardListView.setOnMouseClicked(this::clickSelectedHandle);
        selectedCardListView.setOnKeyPressed(this::deleteSelectedCardHandle);
        selectedCardListView.setOnDragEntered(this::endDnD);

        //
        tagAddTextField.setOnAction(event -> newTagCmd());
        S_tag.setOnAction(event -> searchByTag());
        addTagListView.setOnKeyPressed(this::deleteTagHandle);
    }



    /**
     * Implementation of update() method for Observer interface
     */
    public void update() {

        selectedCardListView.getItems().clear();
        newCardListView.getItems().clear();

        // search by tag
        taggedCards.clear();
        taggedCards.addAll(app.getCards());
        String search = S_tag.getText();
        if (! search.isEmpty()) {
            taggedCards.removeIf(card -> !card.getTagList().contains(search));
        }

        // We put cards in newCardListView except those already in the deck which go in selectedCardListView
        for (Card card : taggedCards) {
            if (!app.getActiveDeck().isInDeck(card)) {
                // if there is no name : put a placeholder.
                newCardListView.getItems().add(Objects.requireNonNullElse(card.getQuestion(), "New Card"));
                if (card.equals(app.getActiveCard())) newCardListView.getSelectionModel().selectLast();
            }
        }

        for (Card card : app.getActiveDeck().getCards()) {
            // if there is no name : put a placeholder.
            selectedCardListView.getItems().add(Objects.requireNonNullElse(card.getQuestion(), "New Card"));
            if (card.equals(app.getActiveCard())) selectedCardListView.getSelectionModel().selectLast();
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
            tags.clear();
            tags.addAll(app.getActiveCard().getTagList());
            addTagListView.getItems().clear();
            addTagListView.getItems().addAll(tags);
        }
    }



    /*
     * Implementation of different methods for the pattern Command
     */

    @FXML
    public void changeToSelectCmd() {
        viewState.changeName("FlashMcCards");
        new ChangeSceneCommand(viewState,0).execute();
    }

    @FXML
    public void changeToEditDeckCmd() {
        new ChangeSceneCommand(viewState,3).execute();
    }

    public void newCardCmd() {
        new NewCardCommand(app).execute();
    }

    public void newCardNotInActiveDeckCmd() {
        new NewCardNotActiveCommand(app).execute();
    }

    public void deleteCardCmd() {
        new DeleteCardCommand(app).execute();
    }

    public void validateChangeCmd() {

        String question = questionTextArea.getText();
        String answer = answerTextArea.getText();
        boolean twoSided = twoSidedCheckBox.isSelected();

        new ValidateInfoCardModCommand(app, question, answer, twoSided, tags).execute();
    }

    public void newTagCmd() {
        if (tagAddTextField.getText().equals("")){
            System.out.println("Veuillez entrer un tag");
        }
        else{
            String newTag = tagAddTextField.getText();
            tags.add(newTag);
            new SetTagListToCardCommand(app, tags).execute();
            tagAddTextField.clear();
        }
    }



    public void searchByTag(){
        update();
        /*String searchTag = S_tag.getText();
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
        }*/
    }







    public void starDnd(MouseEvent event) {
        System.out.println("Début du drag");
        Dragboard db = newCardListView.startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        content.putString(newCardListView.getSelectionModel().getSelectedItem());
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
        update();
    }



    /*
     * The following methods are used to handle the KeyEvent of BackSpace being pressed.
     */

    public void deleteTagHandle(KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED && event.getCode() == KeyCode.BACK_SPACE) {
            int selectedIndex = addTagListView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < addTagListView.getItems().size()) {
                String toRemove = addTagListView.getSelectionModel().getSelectedItem();
                tags.remove(toRemove);
                new SetTagListToCardCommand(app, tags);
            }
        }
    }

    public void deleteSelectedCardHandle(KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED && event.getCode() == KeyCode.BACK_SPACE && app.getActiveDeck().getCards().size() > 1) {
            int selectedIndex = selectedCardListView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < selectedCardListView.getItems().size()) {
                app.supprCardFromActiveDeck(app.getActiveDeck().getCards().get(selectedIndex));
            }
            app.setActiveCard(app.getActiveDeck().getCards().get(Math.min(selectedIndex, app.getActiveDeck().getCards().size()-1)));
        }
    }

    public void deleteCardHandle(KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED && event.getCode() == KeyCode.BACK_SPACE && app.getCards().size() > 1) {
            String selectedName = newCardListView.getSelectionModel().getSelectedItem();
            int i = app.getCardIndex(selectedName);
            app.supprCard(app.getCards().get(i));
            app.setActiveCard(app.getCards().get(Math.min(i, app.getCards().size()-1)));
        }
    }



    /*
     * The following methods are used to handle the MouseEvent of BackSpace being pressed.
     */

    public void clickSelectedHandle(MouseEvent event) {
        int selectedIndex = selectedCardListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < selectedCardListView.getItems().size()) {
            app.setActiveCard(app.getActiveDeck().getCards().get(selectedIndex));
        }
    }

    public void clickHandle(MouseEvent event) {
        String selectedName = newCardListView.getSelectionModel().getSelectedItem();
        app.setActiveCard(app.getCards().get(app.getCardIndex(selectedName)));
    }
}