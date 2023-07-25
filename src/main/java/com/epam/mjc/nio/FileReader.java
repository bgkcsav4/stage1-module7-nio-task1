package com.epam.mjc.nio;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileReader {

    public Profile getDataFromFile(File file) throws FileReadException {
        String content;
        String[] res;

        try {
            Path filePath = Paths.get(file.getPath());
            byte[] bytes = Files.readAllBytes(filePath);
            content = new String(bytes);
            res = finalContent(content);
        } catch (IOException e) {
            throw new FileReadException("Error while reading file: " + e.getMessage(), e);
        }

        return new Profile(res[0], Integer.parseInt(res[1]), res[2], Long.parseLong(res[3]));
    }

    public static String[] finalContent(String content) {
        String[] finalContent = new String[4];
        String[] lines = content.split("\\r?\\n");
        for (int i = 0; i < lines.length; i++) {
            String[] parts = lines[i].split(":");
            if (parts.length == 2) {
                finalContent[i] = parts[1].trim();
            }
        }
        return finalContent;
    }
}
