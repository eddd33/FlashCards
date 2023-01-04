package com.example.flashcards.command;

import com.example.flashcards.models.Card;
import com.example.flashcards.models.Deck;
import com.example.flashcards.models.DeckContainer;

public class NewCardCommand implements Command{
    private String question;
    private String answer;
    private Deck deck;
    Boolean twoSided;
    private DeckContainer deckContainer;
    public NewCardCommand(Deck deck, DeckContainer deckContainer, String question, String answer,Boolean twoSided){

        this.deck=deck;
        this.deckContainer=deckContainer;
        this.question=question;
        this.answer=answer;
        this.twoSided=twoSided;
    }
    public void execute(){
        Card card=new Card();
        card.setTwoSided(twoSided);
        card.setQuestion(question);
        card.setAnswer(answer);
        deck.addCard(card);
        deckContainer.addCard(card);
    }
}
