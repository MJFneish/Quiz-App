package quiz_pages.DesignPatterns.Factory;

import quiz_pages.Models.Question;

public class GeographyQuestionFactory extends QuizFactory{

    public GeographyQuestionFactory() {
        this.readJSONQuestions("GeographyQuestion.json");
    }

    @Override
    public Question createQuestion() {
        int index = (int) Math.floor(Math.random()* AllQuestions.size());
        Question q = AllQuestions.get(index);
        return new GeographyQuestion(q.getQuestion(), q.getCorrectAnswer(), q.getAnswerChoices());
    }
}
