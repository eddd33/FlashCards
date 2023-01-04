package com.example.flashcards.command;

import com.example.flashcards.models.*;

public class DuplicateDeckCommand implements Command{
    private Deck deck;
    private DeckContainer deckContainer;
    public DuplicateDeckCommand(Deck deck,DeckContainer deckContainer){
        this.deck=deck;
        this.deckContainer=deckContainer;
    }

    public void execute(){
        deckContainer.dupDeck(deck);
    }
}
