package quiz_pages.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import quiz_pages.DesignPatterns.Adapter.JsonUserAdapter;
import quiz_pages.Models.User;
import quiz_pages.SceneChanger;
import quiz_pages.StartApplication;

import java.io.File;

public class IndexController {
    // Strings which hold css elements to easily re-use in the application
    protected
    String successMessage = String.format("-fx-text-fill: GREEN;");
    String errorMessage = String.format("-fx-text-fill: RED;");
    String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;");
    String successStyle = String.format("-fx-border-color: #A9A9A9; -fx-border-width: 2; -fx-border-radius: 5;");

    String name, email, password, gender;

    // Import the application's controls
    @FXML
    private Label invalidSignupCredentials;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField
            signUpUsernameTextField,
            signUpEmailTextField,
            signUpPasswordPasswordField,
            signUpRepeatPasswordPasswordField;
    @FXML
    private ToggleGroup Gender;
    @FXML
    private RadioButton maleRadioButton;



    // Creation of methods which are activated on events in the forms
    @FXML
    protected void onCancelButtonClick() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        StartApplication.exit(stage);
    }

    @FXML
    protected void onSignUpButtonClick() {

        if (signUpUsernameTextField.getText().isBlank() || signUpEmailTextField.getText().isBlank() || signUpPasswordPasswordField.getText().isBlank() || signUpRepeatPasswordPasswordField.getText().isBlank()) {
            invalidSignupCredentials.setText("Please fill in all fields!");
            invalidSignupCredentials.setStyle(errorMessage);

            if (signUpUsernameTextField.getText().isBlank()) {
                signUpUsernameTextField.setStyle(errorStyle);
            } else if (signUpEmailTextField.getText().isBlank()) {
                signUpEmailTextField.setStyle(errorStyle);
            } else if (signUpPasswordPasswordField.getText().isBlank()) {
                signUpPasswordPasswordField.setStyle(errorStyle);
            } else if (signUpRepeatPasswordPasswordField.getText().isBlank()) {
                signUpRepeatPasswordPasswordField.setStyle(errorStyle);
            }
        } else if (signUpRepeatPasswordPasswordField.getText().equals(signUpPasswordPasswordField.getText())) {
            invalidSignupCredentials.setText("You are set!");
            invalidSignupCredentials.setStyle(successMessage);
            signUpUsernameTextField.setStyle(successStyle);
            signUpEmailTextField.setStyle(successStyle);
            signUpPasswordPasswordField.setStyle(successStyle);
            signUpRepeatPasswordPasswordField.setStyle(successStyle);

            if((new File("json/currentUser.json")).exists()){
                Stage stage = (Stage)cancelButton.getScene().getWindow();
                StartApplication.secondScreenRestriction(stage);
            }
            name = signUpUsernameTextField.getText();
            email = signUpEmailTextField.getText();
            password = signUpPasswordPasswordField.getText();
            gender = Gender.getSelectedToggle().equals(maleRadioButton)? "male" : "female";

            moveToNextPage(new User(name, email, password, gender));
        } else {
            invalidSignupCredentials.setText("The Passwords didn't match!");
            invalidSignupCredentials.setStyle(errorMessage);
            signUpPasswordPasswordField.setStyle(errorStyle);
            signUpRepeatPasswordPasswordField.setStyle(errorStyle);
        }
    }
    private void moveToNextPage(User user){
        // save all thing to database:
        JsonUserAdapter jsonUserAdapter = new JsonUserAdapter();
        jsonUserAdapter.changeUser(user);

        // move to another page:
        SceneChanger sceneChanger = new SceneChanger();
        sceneChanger.changeScene(cancelButton, "/Views/choose_categories.fxml");

    }
}
