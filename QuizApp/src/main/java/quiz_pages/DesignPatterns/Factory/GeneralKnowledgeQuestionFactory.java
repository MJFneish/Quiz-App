package quiz_pages.DesignPatterns.Factory;
import quiz_pages.Models.Question;


public class GeneralKnowledgeQuestionFactory extends QuizFactory{


    public GeneralKnowledgeQuestionFactory() {
        this.readJSONQuestions("GeneralKnowledgeQuestion.json");
    }

    @Override
    public Question createQuestion() {
        int index = (int) Math.floor(Math.random()* AllQuestions.size());
        Question q = AllQuestions.get(index);
        return new GeneralKnowledgeQuestion(q.getQuestion(), q.getCorrectAnswer(), q.getAnswerChoices());
    }
}
