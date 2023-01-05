module com.example.flashcards {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens com.example.flashcards to javafx.fxml,com.google.gson;
    opens com.example.flashcards.controller;
    exports com.example.flashcards;
}