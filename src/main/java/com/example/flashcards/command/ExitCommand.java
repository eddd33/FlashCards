package com.example.flashcards.command;

import javafx.application.Platform;

public class ExitCommand implements Command {

    public ExitCommand() {}

    @Override
    public void execute() {
        Platform.exit();
    }
}
