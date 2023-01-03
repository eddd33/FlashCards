module com.example.flashcards {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.flashcards to javafx.fxml;
    exports com.example.flashcards;
}