package quiz_pages.DesignPatterns.Factory;

import quiz_pages.Models.Question;

import java.util.ArrayList;

public class GeneralKnowledgeQuestion extends Question {
    public GeneralKnowledgeQuestion(String question, String correctAnswer, ArrayList<String> answerChoices) {
        super(question, correctAnswer, answerChoices);
    }
}
