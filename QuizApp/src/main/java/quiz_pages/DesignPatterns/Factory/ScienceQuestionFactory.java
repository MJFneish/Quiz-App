package quiz_pages.DesignPatterns.Factory;

import quiz_pages.Models.Question;

public class ScienceQuestionFactory extends QuizFactory{
    public ScienceQuestionFactory() {
        this.readJSONQuestions("ScienceQuestion.json");
    }

    @Override
    public Question createQuestion() {
        int index = (int) Math.floor(Math.random()* AllQuestions.size());
        Question q = AllQuestions.get(index);
        return new ScienceQuestion(q.getQuestion(), q.getCorrectAnswer(), q.getAnswerChoices());
    }
}
