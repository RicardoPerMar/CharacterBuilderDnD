package org.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class JsonFileReader {

    private final ObjectMapper objectMapper;

    @Autowired
    public JsonFileReader(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> T readJsonFile(String filePath, Class<T> type) throws IOException {
        return objectMapper.readValue(new File(filePath), type);
    }

    public <T> T[] readJsonArrayFile(String filePath, Class<T[]> type) throws IOException {
        return objectMapper.readValue(new File(filePath), type);
    }
}
