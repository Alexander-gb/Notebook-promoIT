package org.example;

import java.time.LocalDateTime;
import java.util.Scanner;

public class ConsoleMessager {
    private NotebookManager notebook = new NotebookManager();
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String command;
        System.out.println("Добро пожаловать в записную книжку!");

        do {
            System.out.print("Введите команду:");
            command = scanner.nextLine();
            command = command.toLowerCase();

            switch (command) {
                case "#exit" -> System.out.println("Выход из программы...");
                case "#read" -> notebook.readNotes();
                case "#write" -> {
                    System.out.print("Введите текст записи: ");
                    String text = scanner.nextLine();
                    Note note = new Note(text, LocalDateTime.now());
                    notebook.saveNotes(note, "notebook.txt");
                }
                case "#commands" -> {
                    System.out.println("#read - прочитать все записи");
                    System.out.println("#write - добавить новую запись");
                    System.out.println("#exit - выйти из программы");
                    System.out.println("#commands - вывести все команды");
                    System.out.println("#statistics - вывести статистику");
                }
                case "#statistics" -> notebook.printStatistics();
                default -> System.out.println("Неизвестная команда, введите #commands для просмотра комманд");
            }
        } while (!command.equals("#exit"));

        scanner.close();
    }
}
