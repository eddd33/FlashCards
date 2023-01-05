package com.example.flashcards;

import com.example.flashcards.models.DeckContainer;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class loadDeckContainerProcedure {
    private DeckContainer app;
    private String fileName;

    public loadDeckContainerProcedure(DeckContainer app,String fileName){
        this.app=app;
        this.fileName=fileName;
    }
    public void load() throws IOException {
        InputStream stream = getClass().getResourceAsStream(fileName);
        BufferedReader fichier = new BufferedReader(new InputStreamReader(stream));

        Gson gson = new Gson();
        app = gson.fromJson(fichier.readLine(), DeckContainer.class);
        app.notifyObserver();
        fichier.close();

    }
}
