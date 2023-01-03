package com.example.flashcards.command;

import com.example.flashcards.models.*;

public class DuplicateDeckCommand implements Command{
    Deck deck;
    DeckContainer deckContainer;
    public DuplicateDeckCommand(Deck deck,DeckContainer deckContainer){
        this.deck=deck;
        this.deckContainer=deckContainer;
    }

    public void execute(){
        Deck deckCopy = new Deck(this.deck.getName(),this.deck.getDescription());
        deckCopy.setAuthor(this.deck.getAuthor());
        for (Card card : this.deck.getCards()){
            deckCopy.addCard(card);
        }
        for (String tag : this.deck.getTagList()){
            deckCopy.addTag(tag);
        }
        deckContainer.addDeck(deckCopy);
    }
}
