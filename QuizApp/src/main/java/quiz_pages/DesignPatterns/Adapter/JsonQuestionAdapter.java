package quiz_pages.DesignPatterns.Adapter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import quiz_pages.Models.Question;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonQuestionAdapter extends JsonAdapter<Question>{

    public JsonQuestionAdapter(String filePath) {
        this.jsonFile = new File(filePath);
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public List<Question> getItems() {
        List<Question> questions = null;
        try {
            if (jsonFile.exists()) {
                questions = objectMapper.readValue(jsonFile, new TypeReference<>() {});
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Collections.shuffle(questions);
        return new ArrayList<>(questions); // we didn't return questions directly to avoid potentially modify the list outside of the adapter, which could cause unexpected behavior By returning a copy of the list, we ensure that the original list remains unchanged and any modifications to the returned list only affect the copy.

    }

    @Override
    public void addItem(Question item) {
        Question user = new Question(item.getQuestion(), item.getCorrectAnswer(), item.getAnswerChoices());
        List<Question> userScores = getItems();
        userScores.add(user);
        try {
            objectMapper.writeValue(jsonFile, userScores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
