package com.example.flashcards.command;

import com.example.flashcards.models.DeckContainer;

public class NewCardNotActiveCommand implements Command{

    private DeckContainer container;


    public NewCardNotActiveCommand(DeckContainer container) {
        this.container = container;
    }


    @Override
    public void execute() {
        container.newCardNotInDeck();
    }
}
