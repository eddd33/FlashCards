package com.example.flashcards;

import com.example.flashcards.models.*;
import com.example.flashcards.models.DeckContainer;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.util.ArrayList;
public class loadDeckContainerProcedure {
    public DeckContainer app;

    public loadDeckContainerProcedure(DeckContainer app){
        this.app=app;
    }
    public void load() throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("./src/main/resources/com/example/flashcards/save.json"));
            String temp;
            while ((temp = br.readLine()) != null)
                sb.append(temp);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br!=null) {
                br.close(); // stop reading
            }
        }
        String myjsonstring = sb.toString();
        if (myjsonstring!=null) {
            JSONArray jsonArray = new JSONArray(myjsonstring);
            ArrayList<Deck> decks = new ArrayList<>();
            ArrayList<Card> cards = new ArrayList<>();
            JSONArray jsonDecks = jsonArray.getJSONArray(0);
            JSONArray jsonCards = jsonArray.getJSONArray(1);
            for (int k = 0; k < jsonCards.length(); k++) {
                ArrayList<String> tags = new ArrayList<>();
                JSONObject jsonCard = jsonCards.getJSONObject(k);
                String answer = jsonCard.getString("answer");
                String question = jsonCard.getString("question");
                double difficulty = jsonCard.getDouble("difficulty");
                boolean twoSided = jsonCard.getBoolean("twoSided");
                JSONArray jsonTags = jsonCard.getJSONArray("tagList");
                for (int l = 0; l < jsonTags.length(); l++) {
                    tags.add(jsonTags.getString(l));
                }
                Card card = new Card(question, answer, twoSided, tags);
                card.setDifficulty(difficulty);
                cards.add(card);
            }
            for (int m = 0; m < jsonDecks.length(); m++) {
                JSONObject jsonDeck = jsonDecks.getJSONObject(m);
                // Cet objet représente un Deck
                String name = jsonDeck.getString("name");
                Deck deck = new Deck(name);
                String author = jsonDeck.getString("author");
                String description = jsonDeck.getString("description");
                String last_try = jsonDeck.getString("last_try");
                JSONArray jsonTagsDeck = jsonDeck.getJSONArray("tagList");
                JSONArray jsonCardsDeck = jsonDeck.getJSONArray("cards");
                double best_score = jsonDeck.getDouble("bestScore");
                double last_score = jsonDeck.getDouble("lastScore");
                ArrayList<String> tagsDeck = new ArrayList<>();
                for (int n = 0; n < jsonTagsDeck.length(); n++) {
                    tagsDeck.add(jsonTagsDeck.getString(n));
                }
                for (int o = 0; o < jsonCardsDeck.length(); o++) {
                    for (Card card : cards) {
                        if (card.getAnswer().equals(jsonCardsDeck.getJSONObject(o).getString("answer"))) {
                            deck.addCard(card);
                        }
                    }
                }
                deck.setTagList(tagsDeck);
                deck.setAuthor(author);
                deck.setAuthor(description);
                deck.setLast_try(last_try);
                deck.setBestScore(best_score);
                deck.setLastScore(last_score);
                decks.add(deck);
            }
            app.setActiveDeck(decks.get(0));
            app.setDecks(decks);
            app.setCards(cards);
        }
    }
}
