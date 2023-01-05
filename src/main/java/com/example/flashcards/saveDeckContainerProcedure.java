package com.example.flashcards;

import com.example.flashcards.models.*;
import com.google.gson.Gson;

import java.io.*;

public class saveDeckContainerProcedure {

    private DeckContainer deckContainer;
    private String fileName;


    public saveDeckContainerProcedure(DeckContainer deckContainer, String fileName){
        this.deckContainer=deckContainer;
        this.fileName=fileName;
    }


    public void save() throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(deckContainer);
        FileWriter fichier = new FileWriter(fileName);
        fichier.write(json);
        fichier.close();
    }
}