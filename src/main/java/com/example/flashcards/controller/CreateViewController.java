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
        this.newCardCommand = new NewCardCommand(app);
        newCardCommand.execute();
        this.modifyTwoSidedCardCommand = new ModifyTwoSidedCardCommand(app.getCards().get(0), false);
        modifyTwoSidedCardCommand.execute();
        newCardListView.getItems().add(app.getCards().get(0).getQuestion());
    }



    /**
     * Implementation of update() method for Observer interface
     */
    public void update(){}



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
        new NewCardCommand(app).execute();
        Card card = app.getCards().get(0);
        newCardListView.getItems().add(app.getCards().get(0).getQuestion());
        questionTextArea.clear();
        answerTextArea.clear();
        twoSidedCheckBox.setSelected(false);
        tagAddTextField.clear();
        addTagListView.getItems().clear();
    }

    public void change() {
        ModifyAnswerCardCommand modifyAnswerCardCommand = new ModifyAnswerCardCommand(app.getCards().get(0), answerTextArea.getText());
        modifyAnswerCardCommand.execute();
        ModifyQuestionCardCommand modifyQuestionCardCommand = new ModifyQuestionCardCommand(app.getCards().get(0), questionTextArea.getText());
        modifyQuestionCardCommand.execute();
        System.out.println(app.getCards().get(0).getAnswer());
        System.out.println(app.getCards().get(0).getQuestion());
        System.out.println(app.getCards().get(0).getTwoSided());
        System.out.println(app.getCards().get(0).getTagList());
        System.out.println(app.getCards().get(0).getDifficulty());
        app.getActiveDeck().addCard(app.getCards().get(0));
        ObservableList<String> items = newCardListView.getItems();
        items.set(0, app.getCards().get(0).getQuestion());
        /*newCardListView.getItems().add(app.getCards().get(0).getQuestion());
        selectedCardListView.getItems().add(app.getCards().get(0).getQuestion());*/
        for (int i = 0; i < app.getActiveDeck().getCards().size(); i++) {
            System.out.println("liste de cartes du deck:"+app.getCards().get(i).getQuestion());
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
        String newTag = tagAddTextField.getText();
        Card card = app.getCards().get(0);
        card.getTagList().add(newTag);
        addTagListView.getItems().add(newTag);
        tagAddTextField.clear();
        //System.out.println(card.getTagList());
    }

}
