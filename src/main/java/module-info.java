module com.example.flashcards {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires org.json;
    requires javafx.graphics;
    opens com.example.flashcards to javafx.fxml,com.google.gson,javafx.graphics;
    opens com.example.flashcards.controller;
    opens com.example.flashcards.models;
    opens com.example.flashcards.view;
    exports com.example.flashcards;
    exports com.example.flashcards.models;
    exports com.example.flashcards.view;
    exports com.example.flashcards.controller;
    exports com.example.flashcards.command;
}