package quiz_pages.DesignPatterns.Adapter;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

public abstract class JsonAdapter<T> {
    protected File jsonFile;
    protected ObjectMapper objectMapper;

    public abstract List<T> getItems();

    public abstract void addItem(T item);
}
