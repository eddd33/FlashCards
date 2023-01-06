package com.example.flashcards;

import com.example.flashcards.models.Deck;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;

public class exportDeckProcedure {
    private Deck deck;
    private String fileName;
    public exportDeckProcedure(Deck deck, String fileName){
        this.deck=deck;
        this.fileName=fileName;
    }
    public void exportDeck() throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(deck);
        try {
            FileWriter fichier = new FileWriter(fileName);
            fichier.write(json);
            fichier.close();
        }
        catch (IOException e) {
        e.printStackTrace();
    }
    }
}
