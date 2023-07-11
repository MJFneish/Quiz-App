package quiz_pages.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import quiz_pages.DesignPatterns.Adapter.JsonUsersScoresAdapter;
import quiz_pages.Models.User;
import quiz_pages.SceneChanger;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class UsersScoresController implements Initializable {
    @FXML
    ListView<String> scoresListView;
    List<User> users;
    SceneChanger sceneChanger = new SceneChanger();
    private int sortByName=0, sortByDate=0, sortByScore=0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JsonUsersScoresAdapter jsonUsersScoresAdapter = new JsonUsersScoresAdapter();
        users = jsonUsersScoresAdapter.getItems();
        users.sort(Comparator.comparing(User::getName));
        changeListView(users);
    }

    private void changeListView(List<User> users) {
        scoresListView.getItems().clear();
        for (User u : users) {
            scoresListView.getItems().add(u.toString());
        }
    }

    public void sortByName(ActionEvent event){
        if(sortByName ==0) {
            users.sort(Comparator.comparing(User::getName).reversed());
            sortByName=1;
        } else {
            users.sort(Comparator.comparing(User::getName));
            sortByName = 0;
        }
        changeListView(users);
    }
    public void sortByDate(ActionEvent event){
        if(sortByDate ==0) {
            users.sort(Comparator.comparing(User::getDate).reversed());
            sortByDate=1;
        } else {
            users.sort(Comparator.comparing(User::getDate));
            sortByDate = 0;
        }
        changeListView(users);
    }
    public void sortByScore(ActionEvent event){
        if(sortByScore ==0) {
            users.sort(Comparator.comparing(User::getScore).reversed());
            sortByScore=1;
        } else {
            users.sort(Comparator.comparing(User::getScore));
            sortByScore = 0;
        }
        changeListView(users);
    }

    public void startNewGame(ActionEvent event){
        sceneChanger.changeScene(scoresListView, "/Views/choose_categories.fxml");
    }
    public void yourResult(ActionEvent event){
        sceneChanger.changeScene(scoresListView, "/Views/score.fxml");
    }

}
