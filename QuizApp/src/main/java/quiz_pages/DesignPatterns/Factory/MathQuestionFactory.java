package quiz_pages.DesignPatterns.Factory;

import quiz_pages.Models.Question;

public class MathQuestionFactory extends QuizFactory{

    public MathQuestionFactory() {
        this.readJSONQuestions("MathQuestion.json");
    }

    @Override
    public Question createQuestion() {
        int index = (int) Math.floor(Math.random()* AllQuestions.size());
        Question q = AllQuestions.get(index);
        return new MathQuestion(q.getQuestion(), q.getCorrectAnswer(), q.getAnswerChoices());
    }
}
