package org.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonReader {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> List<T> readJsonFile(String filePath, Class<T> clazz) throws IOException {
        return objectMapper.readValue(
                new File(filePath),
                objectMapper.getTypeFactory().constructCollectionType(List.class, clazz)
        );
    }

}
