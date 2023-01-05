package com.example.flashcards.controller.studystrategy;

import com.example.flashcards.models.Card;
import com.example.flashcards.models.DeckContainer;
import com.example.flashcards.models.Study;

import java.util.ArrayList;

public class LearningStrategy implements StudyStrategy {

    public LearningStrategy(){}

    public void nextCard(double coef, Study study) {
        if (study.getStudyList().size() >0) {
            Card firstCard = study.getStudyList().get(0);
            firstCard.setDifficulty(firstCard.getDifficulty() * coef);
            study.getApp().getCard(firstCard.getQuestion()).setDifficulty(firstCard.getDifficulty());
            study.getStudyList().remove(0);
            if (firstCard.getDifficulty() >= 1) {
                study.insertByDiff(study.getStudyList(), firstCard);
            }
        }
    }

}
