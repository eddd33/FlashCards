package com.example.flashcards.command;

import com.example.flashcards.models.DeckContainer;

public class DeleteCardCommand implements Command {

    private DeckContainer container;


    public DeleteCardCommand(DeckContainer container) {
        this.container = container;
    }



    @Override
    public void execute() {
        container.deleteCard();
    }
}
