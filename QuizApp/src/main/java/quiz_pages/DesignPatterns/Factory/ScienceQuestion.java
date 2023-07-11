package quiz_pages.DesignPatterns.Factory;

import quiz_pages.Models.Question;

import java.util.ArrayList;

public class ScienceQuestion extends Question {
    public ScienceQuestion(String question, String correctAnswer, ArrayList<String> answerChoices) {
        super(question, correctAnswer, answerChoices);
    }
}
