package quiz_pages.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import quiz_pages.DesignPatterns.Adapter.JsonUserAdapter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ChooseCategoriesController implements Initializable {
    @FXML
    Button generalKnowledgeButton;
    @FXML
    Button mathButton;
    @FXML
    Button scienceButton;
    @FXML
    Button geographyButton;
    @FXML
    Label username;

    public void goToQuiz(ActionEvent event) {
        String quizTypeText = ((Button) event.getSource()).getText();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/quiz.fxml"));
            Parent root = loader.load();

            ((Label)root.lookup("#quizType")).setText(quizTypeText);

            Scene scene = new Scene(root);
            Stage stage = (Stage) username.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Error occurred: "+e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username.setText("Hello "+ (new JsonUserAdapter()).getUser().getName());
    }
}
