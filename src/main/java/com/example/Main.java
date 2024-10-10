package com.example;

import com.example.service.FilmService;
import com.example.service.SWAPIClient;
import com.example.starwarsapp.model.Film;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final SWAPIClient swapiClient = new SWAPIClient();
    private static final FilmService filmService = new FilmService();

    public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
            boolean exit = false;

            while (!exit) {
                System.out.println("\n=== Star Wars Films Menu ===");
                System.out.println("1. A New Hope");
                System.out.println("2. The Empire Strikes Back");
                System.out.println("3. Return of the Jedi");
                System.out.println("4. The Phantom Menace");
                System.out.println("5. Attack of the Clones");
                System.out.println("6. Revenge of the Sith");
                System.out.println("7. Exit");

                System.out.print("Select a film by number: ");
                int choice;

                try {
                    choice = scanner.nextInt();

                    if (choice >= 1 && choice <= 6) {
                        // Fetch and display film details
                        String jsonResponse = swapiClient.getFilmData(choice);
                        Film film = filmService.parseFilm(jsonResponse);
                        System.out.println("Film Details: " + film.getTitle());

                        // Export the film data to JSON automatically
                        filmService.saveFilmAsJson(film);
                        System.out.println("Film data saved as " + film.getTitle() + ".json");
                        System.out.println("Exit to watch the .json file");

                    } else if (choice == 7) {
                        exit = true;
                        System.out.println("Goodbye!");
                    } else {
                        System.out.println("Invalid choice, please try again.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid number.");
                    scanner.next(); // Clear the invalid input
                } catch (IOException | InterruptedException e) {
                    System.err.println("Error fetching film data: " + e.getMessage());
                }
            }

            scanner.close();
        }
}