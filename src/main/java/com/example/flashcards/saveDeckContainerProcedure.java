package com.example.flashcards;

import com.example.flashcards.models.*;
import org.json.JSONArray;

import java.io.FileWriter;
import java.io.IOException;
public class saveDeckContainerProcedure {

    private DeckContainer deckContainer;


    public saveDeckContainerProcedure(DeckContainer deckContainer){
        this.deckContainer=deckContainer;
    }


    public void save() throws IOException {
        JSONArray array = new JSONArray();
        array.put(deckContainer.getDecks());
        array.put(deckContainer.getCards());
        String jsonString = array.toString();
        try (FileWriter writer = new FileWriter("./src/main/resources/com/example/flashcards/save.json")) {
            writer.write(jsonString);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}