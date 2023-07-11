package quiz_pages.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import quiz_pages.DesignPatterns.Adapter.JsonUserAdapter;
import quiz_pages.Models.User;
import quiz_pages.SceneChanger;
import java.net.URL;
import java.util.ResourceBundle;

public class ScoreController implements Initializable {

    @FXML
    private Button newQuizButton;
    @FXML
    Label username, scoreLabel, resultText;
    private SceneChanger sceneChanger= null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sceneChanger = new SceneChanger();
        JsonUserAdapter jsonUserAdapter = new JsonUserAdapter();
        User user = jsonUserAdapter.getUser();
        if(user !=null){
            username.setText("Username: "+ user.getName());
            int score = user.getScore();
            scoreLabel.setText(score+"/100 pts");
            if(score >=50){
                resultText.setText("Congratulations You Have Passed The Text");
                resultText.setTextFill(Color.web("#148a14"));
            }else{
                resultText.setText("Oh no you failed! good luck in next quiz :)");
                resultText.setTextFill(Color.web("#da2c2c"));
            }
        }
    }
    public void newQuiz(ActionEvent event){
        // move to another page:
        sceneChanger.changeScene(newQuizButton, "/Views/choose_categories.fxml");
    }
    public void showUsersScores(ActionEvent event){
        // move to another page:
        sceneChanger.changeScene(newQuizButton, "/Views/users_scores.fxml");
    }

}
