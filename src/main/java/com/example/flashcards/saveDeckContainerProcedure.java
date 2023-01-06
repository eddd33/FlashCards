package com.example.flashcards;

import com.example.flashcards.models.*;
import org.json.JSONArray;

import java.io.FileWriter;
import java.io.IOException;
public class saveDeckContainerProcedure {

    private DeckContainer deckContainer;
    public String fileName;

    public saveDeckContainerProcedure(DeckContainer deckContainer,String fileName){
        this.deckContainer=deckContainer;
        this.fileName=fileName;
    }


    public void save() throws IOException {
        JSONArray array = new JSONArray();
        array.put(deckContainer.getDecks());
        array.put(deckContainer.getCards());
        String jsonString = array.toString();
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(jsonString);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}