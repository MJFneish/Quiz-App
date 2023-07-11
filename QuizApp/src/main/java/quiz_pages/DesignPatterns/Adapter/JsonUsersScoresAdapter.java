package quiz_pages.DesignPatterns.Adapter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import quiz_pages.Models.User;

public class JsonUsersScoresAdapter extends JsonAdapter<User> {

    public JsonUsersScoresAdapter() {
        this.jsonFile = new File("json/usersScores.json");
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public List<User> getItems() {
        List<User> userScores = new ArrayList<>();
        try {
            if (jsonFile.exists()) {
                userScores = objectMapper.readValue(jsonFile, new TypeReference<List<User>>() {});
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        return userScores;
    }

    @Override
    public void addItem(User item) {
        User user = new User(item.getName(),item.getEmail(), item.getPassword(), item.getGender(), item.getDate(), item.getScore());
        List<User> userScores = getItems();
        userScores.add(user);
        try {
            objectMapper.writeValue(jsonFile, userScores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public User checkUserRegistration(String name_email, String password){
//        List<User> users = getItems();
//        for (User user :
//                users) {
//            if (password.equals(user.getPassword()) && (name_email.equals(user.getName()) || name_email.equals(user.getEmail()))){
//                return user;
//            }
//        }
//        return null;
//    }
}
