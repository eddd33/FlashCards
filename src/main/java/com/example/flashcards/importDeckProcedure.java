package com.example.flashcards;

import java.io.*;

import com.example.flashcards.models.DeckContainer;
import com.example.flashcards.models.Deck;
import com.example.flashcards.models.Card;
import com.google.gson.Gson;

public class importDeckProcedure {

    private String fileName;
    private DeckContainer deckContainer;

    public importDeckProcedure(String fileName,DeckContainer deckContainer){
        this.deckContainer=deckContainer;
        this.fileName=fileName;
    }

    public void charger() throws IOException{
        BufferedReader fichier = new BufferedReader(new FileReader(fileName));

        Gson gson = new Gson();
        Deck deck = gson.fromJson(fichier.readLine(), Deck.class);
        deck.setName(deckContainer.getUniqueName(deck.getName()));
        Boolean dedans;
        for (Card card : deck.getCards()){
            dedans=false;
            for (Card cardList : deckContainer.getCards()){
                if (card.getQuestion().equals(cardList.getQuestion())){
                    dedans=true;
                    deck.removeCard(card);
                    deck.addCard(cardList);
                }
            }
            if (!dedans){
                deckContainer.getCards().add(card);
            }
        }
        deckContainer.getDecks().add(deck);
        fichier.close();
    }
}
