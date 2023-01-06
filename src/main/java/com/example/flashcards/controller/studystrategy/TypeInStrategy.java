package com.example.flashcards.controller.studystrategy;

import com.example.flashcards.models.Card;
import com.example.flashcards.models.Study;

public class TypeInStrategy implements StudyStrategy{

    public TypeInStrategy(){}
    @Override
    public void nextCard(double coef, Study study) {
        if (study.getStudyList().size() >0) {
            Card firstCard = study.getStudyList().get(0);
            if (firstCard.getDifficulty() * coef > 2) {
                firstCard.setDifficulty(2);
            } else if (firstCard.getDifficulty() * coef < 0.8) {
                firstCard.setDifficulty(0.8);
            }
            else {
                firstCard.setDifficulty(firstCard.getDifficulty() * coef);
            }
            study.getApp().getCard(firstCard.getQuestion()).setDifficulty(firstCard.getDifficulty());
            study.getStudyList().remove(0);
            if (firstCard.getDifficulty() >= 1) {
                study.insertByDiff(study.getStudyList(), firstCard);
            }
        }
    }
}
