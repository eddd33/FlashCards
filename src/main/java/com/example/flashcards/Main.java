package com.example.flashcards;

import com.example.flashcards.controller.*;
import com.example.flashcards.models.DeckContainer;
import com.example.flashcards.view.ViewState;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        SceneController controller = new SceneController(stage);
        DeckContainer container = new DeckContainer();
        ViewState state = new ViewState(controller);


        /*
         * Deck selection view.
         */
        FXMLLoader selectionViewLoader = new FXMLLoader();
        selectionViewLoader.setLocation(getClass().getResource("SelectionView.fxml"));
        selectionViewLoader.setControllerFactory(iC->new SelectionViewController(container,state));
        Parent selectionView = selectionViewLoader.load();
        Scene selectionScene = new Scene(selectionView);
        controller.addScene(selectionScene);


        /*
         * Deck learning view.
         */
        FXMLLoader learningViewLoader = new FXMLLoader();
        learningViewLoader.setLocation(getClass().getResource("LearningView.fxml"));
        learningViewLoader.setControllerFactory(iC->new LearningViewController(container,state));
        Parent learningView = learningViewLoader.load();
        Scene learningScene = new Scene(learningView);
        controller.addScene(learningScene);


        /*
         * Deck creation view.
         */
        FXMLLoader createViewLoader = new FXMLLoader();
        createViewLoader.setLocation(getClass().getResource("CreateView.fxml"));
        createViewLoader.setControllerFactory(iC->new CreateViewController(container,state));
        Parent createView = createViewLoader.load();
        Scene createScene = new Scene(createView);
        controller.addScene(createScene);


        /*
         * Deck creation view.
         */
        FXMLLoader editViewLoader = new FXMLLoader();
        editViewLoader.setLocation(getClass().getResource("DeckEditView.fxml"));
        editViewLoader.setControllerFactory(iC->new DeckEditViewController(container,state));
        Parent editView = editViewLoader.load();
        Scene editScene = new Scene(editView);
        controller.addScene(editScene);

        /*
         * Deck stats view.
         */
        FXMLLoader statViewLoader = new FXMLLoader();
        statViewLoader.setLocation(getClass().getResource("StatView.fxml"));
        statViewLoader.setControllerFactory(iC->new StatViewController(container,state));
        Parent statView = statViewLoader.load();
        Scene statScene = new Scene(statView);
        controller.addScene(statScene);

        stage.setTitle("TELECOM Nancy FlashCard");
        stage.setScene(selectionScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}