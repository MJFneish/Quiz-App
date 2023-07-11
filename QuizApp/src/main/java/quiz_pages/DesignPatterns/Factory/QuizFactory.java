package quiz_pages.DesignPatterns.Factory;

import quiz_pages.DesignPatterns.Adapter.JsonQuestionAdapter;
import quiz_pages.Models.Question;
import java.util.ArrayList;
import java.util.List;

public abstract class QuizFactory{
    List<Question> quiz = new ArrayList<>(), AllQuestions;

    public void readJSONQuestions(String fileName) {
        JsonQuestionAdapter jsonQuestionAdapter = new JsonQuestionAdapter("json/"+fileName);
        AllQuestions = jsonQuestionAdapter.getItems();
    }

    public abstract Question createQuestion();
    public void createQuiz(){
        for(int i=0; i<10; i++){
            quiz.add(createQuestion());
        }
    }

    public List<Question> getQuiz() {
        return quiz;
    }
}
