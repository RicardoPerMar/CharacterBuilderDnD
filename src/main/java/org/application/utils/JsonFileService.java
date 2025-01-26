package org.application.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JsonFileService {

    private final ObjectMapper objectMapper;

    public JsonFileService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> List<T> readJsonFile(String filePath, TypeReference<List<T>> typeReference) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
            return new ArrayList<>();
        }
        return objectMapper.readValue(file, typeReference);
    }

    public <T> void writeJsonFile(String filePath, List<T> data) throws IOException{
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), data);
    }
}
