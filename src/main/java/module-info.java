module com.example.flashcards {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.flashcards to javafx.fxml;
    opens com.example.flashcards.controller;
    exports com.example.flashcards;
}