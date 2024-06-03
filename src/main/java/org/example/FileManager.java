package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class FileManager {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static void saveNotes(Note note, String filename) {
        File log = new File(filename);
        try (FileWriter fileWriter = new FileWriter(log, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            bufferedWriter.write(DATE_TIME_FORMATTER.format(note.getTimestamp()));
            bufferedWriter.write(" ");
            bufferedWriter.write(note.getText());
            bufferedWriter.newLine();
        } catch (IOException e) {
            System.err.println("Error writing notes: " + e.getMessage());
        }
    }

    public static List<Note> loadNotes(String filename) {
        Path filePath = Paths.get(filename);
        List<Note> notes = new ArrayList<>();
        if (Files.exists(filePath)) {
            try (BufferedReader bufferedReader = Files.newBufferedReader(filePath)) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    LocalDateTime timestamp = LocalDateTime.parse(line.substring(0, 19), DATE_TIME_FORMATTER);
                    String text = line.substring(20);
                    Note note = new Note(text, timestamp);
                    notes.add(note);
                }
            } catch (IOException e) {
                System.err.println("Error reading notes: " + e.getMessage());
            }
        } else {
            System.out.println("Notes file not found.");
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                System.err.println("Error creating notes file: " + e.getMessage());
            }
        }
        return notes;
    }
}