package org.example;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotebookManager {
    public void saveNotes(Note note, String filename) {
        FileManager.saveNotes(note, filename);
    }
    public void readNotes() {
        List<Note> loadedNotes = FileManager.loadNotes("notebook.txt");
        for (Note note : loadedNotes) {
            System.out.println("Время заметки: " + note.getTimestamp() + ", Заметка: " + note.getText());
        }
    }

    public void printStatistics() {
        List<Note> notes = FileManager.loadNotes("notebook.txt");
        int numNotes = notes.size();
        int numCharacters = 0;
        Map<LocalDate, Integer> notesPerDay = new HashMap<>();

        for (Note note : notes) {
            numCharacters += note.getText().length();
            LocalDate noteDate = note.getTimestamp().toLocalDate();
            notesPerDay.put(noteDate, notesPerDay.getOrDefault(noteDate, 0) + 1);
        }

        System.out.println("Количество записей: " + numNotes);
        System.out.println("Количество символов в файле: " + numCharacters);

        LocalDate mostActiveDay = null;
        int maxNumNotes = 0;
        for (Map.Entry<LocalDate, Integer> entry : notesPerDay.entrySet()) {
            if (entry.getValue() > maxNumNotes) {
                maxNumNotes = entry.getValue();
                mostActiveDay = entry.getKey();
            }
        }

        if (mostActiveDay != null) {
            System.out.println("Самый активный день: " + mostActiveDay);
        } else {
            System.out.println("Нет записей");
        }
    }

}