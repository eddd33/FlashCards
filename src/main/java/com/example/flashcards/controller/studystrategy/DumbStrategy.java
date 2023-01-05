package com.example.flashcards.controller.studystrategy;

import com.example.flashcards.models.Card;
import com.example.flashcards.models.DeckContainer;
import com.example.flashcards.models.Study;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DumbStrategy implements StudyStrategy {

    public DumbStrategy(){}

    public void nextCard(double coef, Study study){
        study.getStudyList().remove(0);
    }
}
