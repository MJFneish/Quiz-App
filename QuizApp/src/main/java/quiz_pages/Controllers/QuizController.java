package quiz_pages.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import quiz_pages.DesignPatterns.Adapter.JsonUserAdapter;
import quiz_pages.DesignPatterns.Adapter.JsonUsersScoresAdapter;
import quiz_pages.DesignPatterns.Factory.*;
import quiz_pages.Models.Question;
import quiz_pages.Models.User;
import quiz_pages.SceneChanger;

import java.util.ArrayList;
import java.util.List;

public class QuizController {
    @FXML
    Label resultText, questionNBR, question, username, quizType;
    @FXML
    Button startQuizButton, checkButton, nextButton;
    @FXML
    RadioButton answer1, answer2, answer3, answer4;
    @FXML
    ToggleGroup answer;
    User user=null;
    JsonUsersScoresAdapter jsonUsersScoresAdapter= null;
    JsonUserAdapter jsonUserAdapter = null;
    List<Question> quiz=null;
    ArrayList<String> choices= new ArrayList<>();
    private int QuestionNBR=0;

    public void startQuiz(ActionEvent event) {
        QuizFactory quizFactory;
        String s= quizType.getText();
        jsonUserAdapter = new JsonUserAdapter();
        user = jsonUserAdapter.getUser();
        username.setText("User: "+user.getName());
        answer1.setVisible(true);answer1.setDisable(false);
        answer2.setVisible(true);answer2.setDisable(false);
        answer3.setVisible(true);answer3.setDisable(false);
        answer4.setVisible(true);answer4.setDisable(false);

        switch (s) {
            case ("Math") -> {
                quizFactory = new MathQuestionFactory();
            }
            case ("Science") -> {
                quizFactory = new ScienceQuestionFactory();
            }
            case ("Geography") -> {
                quizFactory = new GeographyQuestionFactory();
            }
            default -> {
                quizFactory = new GeneralKnowledgeQuestionFactory();
            }
        }
        quizFactory.createQuiz();
        quiz = quizFactory.getQuiz();
        choices = quiz.get(0).getAnswerChoices();

        startQuizButton.setDisable(true);
        startQuizButton.setVisible(false);
        nextButton.setVisible(true);
        checkButton.setVisible(true);
        changeQuestion();


    }

    public void changeQuestion(){
        questionNBR.setText("Question :"+(QuestionNBR+1)+"/10");
        question.setText(quiz.get(QuestionNBR).getQuestion());

        choices = quiz.get(QuestionNBR).getAnswerChoices();
        Toggle selectedToggle = answer.getSelectedToggle();
        if(selectedToggle != null){
           selectedToggle.setSelected(false);
        }
        answer1.setText(choices.get(0));
        answer2.setText(choices.get(1));
        answer3.setText(choices.get(2));
        answer4.setText(choices.get(3));

        checkButton.setDisable(true);
        nextButton.setDisable(true);

        resultText.setText("");
    }
    public void radioButtonPress(ActionEvent event){
        if(nextButton.isDisable())
            checkButton.setDisable(false);
    }
    public void checkAnswer(ActionEvent event){
        Toggle selectedToggle = answer.getSelectedToggle();
        String text="";
        if (selectedToggle.equals(answer1)) {
            text=answer1.getText();
        } else if (selectedToggle.equals(answer2)) {
            text=answer2.getText();
        } else if (selectedToggle.equals(answer3)) {
            text=answer3.getText();

        } else if (selectedToggle.equals(answer4)) {
            text=answer4.getText();
        }
        if (text.equals(quiz.get(QuestionNBR).getCorrectAnswer())) {
            resultText.setText("Your answer is correct!");
            user.incrementScore();
        }else{
            resultText.setText("Your answer is false The correct answer is :"+quiz.get(QuestionNBR).getCorrectAnswer());
        }
        checkButton.setDisable(true);
        nextButton.setDisable(false);
    }

    public void nextQuestion(ActionEvent event){
        QuestionNBR+=1;
        if(QuestionNBR<10) {
            changeQuestion();
        } else {
            jsonUsersScoresAdapter = new JsonUsersScoresAdapter();
            jsonUsersScoresAdapter.addItem(user);
            jsonUserAdapter.changeUser(user);
            // move to another page:
            SceneChanger sceneChanger = new SceneChanger();
            sceneChanger.changeScene(checkButton, "/Views/score.fxml");
        }
    }
}
