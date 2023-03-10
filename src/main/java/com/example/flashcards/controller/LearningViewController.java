package com.example.flashcards.controller;

import com.example.flashcards.ImageGetter;
import com.example.flashcards.command.ChangeSceneCommand;
import com.example.flashcards.command.ExitCommand;
import com.example.flashcards.controller.StatViewController;
import com.example.flashcards.controller.studystrategy.*;
import com.example.flashcards.models.Card;
import com.example.flashcards.models.Deck;
import com.example.flashcards.models.DeckContainer;
import com.example.flashcards.models.Study;
import com.example.flashcards.view.*;
import javafx.scene.image.Image;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;

public class LearningViewController implements Observer, Initializable {

    private DeckContainer app;
    private ViewState viewState;
    private Deck deck;
    private Study study;

    private double current_score;

    @FXML private Button affiche_reponse;
    @FXML private Label answerLabel;
    @FXML private Label questionLabel;
    @FXML private AnchorPane buttonContainer;

    @FXML private Text goodCards;
    @FXML private Text mehCards;
    @FXML private Text badCards;


    /**
     * @param app
     * The DeckContainer that is used to control decks and cards.
     *
     * @param viewState
     * The ViewState used to know at any time in which Scene we are.
     */
    public LearningViewController(DeckContainer app, ViewState viewState){
        this.app=app;
        this.viewState=viewState;
        this.app.addObserver(this);
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
        System.out.print("entr??e init");
    }



    /**
     * Implementation of update() method for Observer interface
     */
    public void update(){
        System.out.println("Entr??e update " + viewState.getState());
        if (viewState.getState() == 0) {
            System.out.println("J'initialise Study'");
            StudyStrategy strat = initStrategy(app.getLearningStrategy());
            study = new Study(new ArrayList<>(app.getActiveDeck().getCards()), strat, app);
            sortByDiff(study.getStudyList());
            current_score = 0;
        }
        if (viewState.getState() <= 1) {
            buttonContainer.getChildren().clear();
            Button answerBut = new Button();
            if (study.getStudyList().size() != 0) {
                System.out.println("J'affiche la premi??re carte");
                if (study.getStudyList().get(0).getTwoSided()) {
                    double randomNumber = Math.random();
                    if (randomNumber>0.5) {
                        questionLabel.setText(study.getStudyList().get(0).getQuestion());
                        answerLabel.setText(study.getStudyList().get(0).getAnswer());
                    }
                    else {
                        answerLabel.setText(study.getStudyList().get(0).getQuestion());
                        questionLabel.setText(study.getStudyList().get(0).getAnswer());
                    }
                }
                else {
                    questionLabel.setText(study.getStudyList().get(0).getQuestion());
                    answerLabel.setText(study.getStudyList().get(0).getAnswer());
                }
                answerLabel.setOpacity(0);
                if (study.getStrategy() instanceof TimedStrategy) {
                    PauseTransition pause = new PauseTransition(Duration.seconds(5));
                    pause.setOnFinished(event -> {
                        reveal();
                    });
                    pause.play();
                } else if (study.getStrategy() instanceof TypeInStrategy) {
                    TextField typeIn = new TextField();
                    buttonContainer.getChildren().add(typeIn);
                    typeIn.setOnKeyPressed(new EventHandler<KeyEvent>() {
                        @Override
                        public void handle(KeyEvent event) {
                            if (event.getCode() == KeyCode.ENTER) {
                                String userAnswer = typeIn.getText();
                                buttonContainer.getChildren().clear();
                                HBox buttonBox = new HBox();
                                buttonBox.setSpacing(10);
                                Label bravoLabel = new Label();
                                bravoLabel.setStyle("-fx-text-fill: white;");
                                Button next = new Button();
                                next.setText("Prochaine carte");
                                if (userAnswer.equals(study.getStudyList().get(0).getAnswer())) {
                                    bravoLabel.setText("Bonne r??ponse");
                                    next.setOnAction(e -> nextCard(0.9));
                                }
                                else {
                                    bravoLabel.setText("Mauvaise r??ponse");
                                    next.setOnAction(e -> nextCard(1.2));
                                }
                                buttonBox.getChildren().addAll(bravoLabel,next);
                                buttonContainer.getChildren().add(buttonBox);
                            }
                        }
                    });
                } else {
                    answerBut.setOnAction(event -> reveal());
                    answerBut.setText("Afficher la r??ponse");
                    buttonContainer.getChildren().add(answerBut);
                }
            }
            else {
                double score = calcScore();
                if (app.getActiveDeck().getBestScore() > score) {
                    app.getActiveDeck().setBestScore(score);
                }
                app.getActiveDeck().setLastScore(score);

                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String aujourdhui = formatter.format(date);
                app.getActiveDeck().setLast_try(aujourdhui);


                System.out.println("Score : " + score);

                questionLabel.setText("R??vision termin??e");
                answerLabel.setText("");
                URL imageFile=new ImageGetter().getImage();
                Image image = new Image(imageFile.toString());
                ImageView cardj = new ImageView();
                cardj.setImage(image);
                cardj.setFitHeight(500);
                cardj.setFitWidth(500);
                answerLabel.setGraphic(cardj);
                answerBut.setOnAction(event -> changeToSelecCmd());
                answerBut.setText("Retourner ?? la selection des paquets");
                buttonContainer.getChildren().add(answerBut);
            }
            int goodNum= 0;
            int midNum = 0;
            int hardNum =0;
            for (Card card :
                    study.getStudyList()) {
                if (card.getDifficulty()<1) {
                    goodNum++;
                } else if (card.getDifficulty()<1.7) {
                    midNum++;
                }
                else {
                    hardNum++;
                }
            }
            goodCards.setText(Integer.toString(goodNum));
            mehCards.setText(Integer.toString(midNum));
            badCards.setText(Integer.toString(hardNum));
        } else {
            study = null;
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
    public void changeToSelecCmd() {
        if(answerLabel.getGraphic() != null) {
            answerLabel.setGraphic(null);
        }
        new ChangeSceneCommand(viewState,0).execute();
    }

    @FXML
    public void reveal() {
            answerLabel.setOpacity(1);
            buttonContainer.getChildren().clear();
            HBox buttonBox = new HBox();
            Button easyButton = new Button();
            easyButton.setText("Facile");
            easyButton.setOnAction(event -> {
                current_score = current_score +1;
                nextCard(0.9);
                System.out.println("Score courant: " + current_score);
            });
            Button midButton = new Button();
            midButton.setText("Moyen");
            midButton.setOnAction(event -> {
                    current_score = current_score + 2;
                    nextCard(1);
            });
            Button hardButton = new Button();
            hardButton.setText("Difficile");
            hardButton.setOnAction(event -> {
                current_score = current_score + 3;
                nextCard(1.2);
            });
            buttonBox.getChildren().addAll(easyButton,midButton,hardButton);
            buttonContainer.getChildren().add(buttonBox);
    }

    public void nextCard(double coef) {
        study.nextCard(coef);
        app.notifyObserver();
    }

    private void sortByDiff(ArrayList<Card> List) {
        ArrayList<Card> bufferList = new ArrayList<Card>();
        for (Card card : List) {
            if (bufferList.size() == 0){
                bufferList.add(card);
            }
            else {
                int i =0;
                while (i<bufferList.size() && card.getDifficulty() <= bufferList.get(i).getDifficulty()){
                    i++;
                }
                if (i == bufferList.size()) {
                    bufferList.add(card);
                }
                else {
                    bufferList.add(i,card);
                }
            }
        }
        List = bufferList;
    }

    private void insertByDiff(ArrayList<Card> List, Card element) {
        int i = 0;
        while (i < List.size() && element.getDifficulty() < List.get(i).getDifficulty()) {
            i ++;
        }
        if (i+1 >= List.size()) {
            List.add(element);
        }
        else {
            List.add(i+1,element);
        }
    }

    private double calcScore() {
        int cardNum = app.getActiveDeck().getCards().size();
        System.out.println("Nombre de cartes : " + cardNum);
        return current_score/cardNum;
    }

    private StudyStrategy initStrategy(int learningStrategy) {
        switch (learningStrategy) {
            case 0:
                return new DumbStrategy();
            case 1:
                return new LearningStrategy();
            case 2:
                return new TimedStrategy();
            case 3:
                return new TypeInStrategy();
            default:
                return new LearningStrategy();
        }
    }
}
