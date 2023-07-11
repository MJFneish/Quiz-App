package quiz_pages.DesignPatterns.Adapter;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import quiz_pages.Models.User;

public class JsonUserAdapter {

    private final File jsonFile;
    private final ObjectMapper objectMapper;

    public JsonUserAdapter() {
        this.jsonFile = new File("json/currentUser.json");
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    public User getUser() {
        User user = new User();
        try {
            if (jsonFile.exists()) {
                user = objectMapper.readValue(jsonFile, User.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void changeUser(User item) {
        try {
            objectMapper.writeValue(jsonFile, item);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
