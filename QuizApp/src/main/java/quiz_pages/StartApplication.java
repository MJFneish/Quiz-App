package quiz_pages;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class StartApplication extends Application {
    File currenUserJson = new File("json/currentUser.json");

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/index.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setMinHeight(700);
        stage.setMinWidth(1000);
        stage.setOnCloseRequest(event -> {
            event.consume();
            exit(stage);
        });
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Views/assets/images/favicon.png")));
        stage.setTitle("Quiz App");
        stage.setScene(scene);
        stage.show();
        if(currenUserJson.exists()){
            secondScreenRestriction(stage);
        }

        //this function is set to delete the currentUser.json even when you reBuild the software
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (currenUserJson.exists()) {
                currenUserJson.delete();
            }
        }));

        //this function only used in this scene
        //during debugging its very useful so that i add it
        scene.setOnKeyPressed(event -> {
            if (event.isControlDown() && event.getCode() == KeyCode.W) {
                exit(stage);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void secondScreenRestriction(Stage stage){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("NOTE!!");
        alert.setHeaderText("Previously signed up");
        alert.setContentText("you are previously signed up to this game please return to your page\nthis restriction occurred when currentUser.json in the json folder exists");
        if(alert.showAndWait().get() == ButtonType.OK){
            stage.close();
        }
    }
    public static void exit(Stage stage){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You're about to exit the application");
        alert.setContentText("Do you really want to quit the game?\nAll related information of yours will be deleted");
        if(alert.showAndWait().get() == ButtonType.OK ){
            (new File("json/currentUser.json")).delete();
            stage.close();
        }
    }

    //this function just for fun i just want to try it to shut down my laptop
//    public void shutDownWindow(){
//        Runtime runtime = Runtime.getRuntime();
//        try {
//            Process proc = runtime.exec("shutdown -s -t 0");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        System.exit(0);
//    }
}