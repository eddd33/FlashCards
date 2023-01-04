package com.example.flashcards.controller;

import com.example.flashcards.command.*;
import com.example.flashcards.models.Card;
import com.example.flashcards.models.DeckContainer;
import com.example.flashcards.view.*;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.*;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateViewController implements Observer, Initializable {

    private DeckContainer app;
    private ViewState viewState;
    private NewCardCommand newCardCommand;
    private ModifyTwoSidedCardCommand modifyTwoSidedCardCommand;

    @FXML
    private TextField S_tag;
    @FXML
    private TextField tagAddTextField;
    @FXML
    private TextArea questionTextArea;
    @FXML
    private TextArea answerTextArea;
    @FXML
    private CheckBox twoSidedCheckBox;
    @FXML
    private ListView<String> selectedCardListView; //liste des decks
    @FXML
    private ListView<String> newCardListView; //cartes créés mais pas dans un deck
    @FXML
    private ListView<String> addTagListView; //liste des tags


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
        //this.newCardCommand = new NewCardCommand(app);
        //newCardCommand.execute();
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
        //int taille_deck = app.getActiveDeck().getCards().size();
        this.modifyTwoSidedCardCommand = new ModifyTwoSidedCardCommand(app.getCards().get(0), false);
        modifyTwoSidedCardCommand.execute();
        newCardListView.getItems().add(app.getCards().get(0).getQuestion());
        newCardListView.setOnDragDetected(this::starDnd);
        selectedCardListView.getItems().add(app.getCards().get(0).getQuestion());
        selectedCardListView.setOnDragEntered(this::endDnD);
    }



    /**
     * Implementation of update() method for Observer interface
     */
    public void update(){
        for (Card card : app.getCards()){
            selectedCardListView.getItems().add(card.getQuestion());
        }
        //this.newCardCommand = new NewCardCommand(app);
        //newCardCommand.execute();
    }



    /*
     * Implementation of different methods for the pattern Command
     */

    @FXML
    public void changeToSelecCmd() {
        questionTextArea.clear();
        answerTextArea.clear();
        twoSidedCheckBox.setSelected(false);
        tagAddTextField.clear();
        addTagListView.getItems().clear();
        new ChangeSceneCommand(viewState,0).execute();

    }



    public void nouvelleCarte() {
        //int taille_deck = app.getActiveDeck().getCards().size();
        this.newCardCommand = new NewCardCommand(app);
        newCardCommand.execute();
        newCardListView.getItems().add(null);
        questionTextArea.clear();
        answerTextArea.clear();
        twoSidedCheckBox.setSelected(false);
        tagAddTextField.clear();
        addTagListView.getItems().clear();
    }



    public void change() {
        int nb_cartes = app.getActiveDeck().getCards().size();
        ModifyAnswerCardCommand modifyAnswerCardCommand = new ModifyAnswerCardCommand(app.getActiveDeck().getCards().get(nb_cartes-1), answerTextArea.getText());
        modifyAnswerCardCommand.execute();
        ModifyQuestionCardCommand modifyQuestionCardCommand = new ModifyQuestionCardCommand(app.getActiveDeck().getCards().get(nb_cartes-1), questionTextArea.getText());
        modifyQuestionCardCommand.execute();

        System.out.println(app.getActiveDeck().getCards().get(0).getAnswer());
        System.out.println(app.getActiveDeck().getCards().get(0).getQuestion());
        System.out.println(app.getActiveDeck().getCards().get(0).getTwoSided());
        System.out.println(app.getActiveDeck().getCards().get(0).getTagList());
        System.out.println(app.getActiveDeck().getCards().get(0).getDifficulty());

        //app.getActiveDeck().addCard(app.getCards().get(0));
        ObservableList<String> items = newCardListView.getItems();
        items.set(nb_cartes-1, app.getCards().get(nb_cartes).getQuestion());
        //newCardListView.getItems().add(app.getActiveDeck().getCards().get(0).getQuestion());
        //selectedCardListView.getItems().add(app.getActiveDeck().getCards().get(taille_deck-1).getQuestion());
        System.out.println("taille du deck "+app.getCards().size());
        for (int i = 0; i < app.getCards().size(); i++) {
            System.out.println("liste de cartes du deck : "+app.getCards().get(i).getQuestion());
        }
    }



    public void changeSide(){
        if (twoSidedCheckBox.isSelected()){
            modifyTwoSidedCardCommand = new ModifyTwoSidedCardCommand(app.getCards().get(0), true);
            modifyTwoSidedCardCommand.execute();
        }
        else{
            modifyTwoSidedCardCommand = new ModifyTwoSidedCardCommand(app.getCards().get(0), false);
            modifyTwoSidedCardCommand.execute();
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



    public void newTag(){
        if (tagAddTextField.getText().equals("")){
            System.out.println("Veuillez entrer un tag");
        }
        else{
            String newTag = tagAddTextField.getText();
            Card card = app.getCards().get(0);
            card.getTagList().add(newTag);
            addTagListView.getItems().add(newTag);
            tagAddTextField.clear();
            //System.out.println(card.getTagList());
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
}
