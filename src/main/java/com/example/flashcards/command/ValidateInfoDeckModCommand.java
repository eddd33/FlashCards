package com.example.flashcards.command;

import com.example.flashcards.models.DeckContainer;
import com.example.flashcards.view.ViewState;

public class ValidateInfoDeckModCommand implements Command{

    private DeckContainer container;
    private ViewState viewState;
    private String name;
    private String author;
    private String description;

    public ValidateInfoDeckModCommand(DeckContainer container, ViewState viewState, String name, String author, String description) {
        this.container = container;
        this.viewState = viewState;
        this.name = name;
        this.author = author;
        this.description = description;
    }

    @Override
    public void execute() {
        container.getActiveDeck().setName(name);
        container.getActiveDeck().setAuthor(author);
        container.getActiveDeck().setDescription(description);
        container.notifyObserver();
        viewState.changeScene(2);
    }
}
