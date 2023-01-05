package com.example.flashcards.models;

import com.example.flashcards.controller.studystrategy.StudyStrategy;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Study {

    private ArrayList<Card> studyList;
    private StudyStrategy strategy;
    private DeckContainer app;

    public Study(ArrayList<Card> studyList, StudyStrategy strategy, DeckContainer app) {
        this.studyList = studyList;
        this.strategy = strategy;
        this.app = app;
    }

    public ArrayList<Card> getStudyList() {
        return studyList;
    }
    public DeckContainer getApp() {
        return app;
    }

    public void nextCard(double coef) {
        strategy.nextCard(coef, this);
    }


    public void insertByDiff(ArrayList<Card> List, Card element) {
        int i = 0;
        while (i < List.size() && element.getDifficulty() < List.get(i).getDifficulty()) {
            i ++;
        }
        if (i+1 >= List.size()) {
            List.add(element);
        }
        else {
            List.add(i+1,element);
        }
    }
}
