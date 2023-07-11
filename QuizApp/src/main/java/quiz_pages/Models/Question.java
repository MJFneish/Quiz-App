package quiz_pages.Models;

import java.util.ArrayList;

public class Question {
    private String question;
    private String correctAnswer;
    private ArrayList<String> answerChoices;

    public Question() {
        answerChoices = new ArrayList<>();
    }

    public Question(String question, String correctAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        answerChoices = new ArrayList<>();
    }

    public Question(String question, String correctAnswer, ArrayList<String> answerChoices) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.answerChoices = answerChoices;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public ArrayList<String> getAnswerChoices() {
        return answerChoices;
    }

    public void setAnswerChoices(ArrayList<String> answerChoices) {
        this.answerChoices = answerChoices;
    }

    @Override
    public String toString() {
        String s="";
        s = "question: "+question+ "\ncorrectAnswer: "+correctAnswer+ "\nchoices: [\n\t";
        for (String str :
                answerChoices) {
            s+= str + "\n\t";
        }
        s+="\r]\n";
        return s;
    }
}
