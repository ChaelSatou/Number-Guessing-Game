package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;

// Author: Chael Candeza
public class Main {

    static int answers = (int) (Math.random() * 100);
    static int attempts;
    static List<Score> highScores = readHighScore();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Number Guessing Game!");
        System.out.println("I'm thinking of a number between 1 and 100");
        System.out.println("You have a different chances according to the difficulty you've selected.");
        System.out.println("Please select the difficulty level: ");
        System.out.println("""
                1. Easy (10 chances)
                2. Medium (5 chances)
                3. Hard (3 chances)
                4. Leaderboard """);
        System.out.print("Enter your choice: ");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                easyLevel();
                break;
            case 2:
                mediumLevel();
                break;
            case 3:
                hardLevel();
                break;
            case 4:
                displayHighScores();
                break;
            default:
                System.out.println("Invalid choice try again!");
        }
        input.close();
    }

    private static void easyLevel() {
        System.out.println("Great! You have selected the Easy difficulty level");
        System.out.println("Let's start the game ");
        attempts = 10;
        //System.out.println("Random number! "+answers);
        int i = 1;
        while (i <= attempts) {
            System.out.print("Enter your guess!: ");
            int easyLevelGuess = input.nextInt();

            if (easyLevelGuess < answers) {
                System.out.println("Incorrect! The number is greater than " + easyLevelGuess);
                i++;
            } else if (easyLevelGuess > answers) {
                System.out.println("Incorrect! The number is less than " + easyLevelGuess);
                i++;
            } else {
                System.out.println("Congratulations! You guessed the correct number in " + i + " attempts.");
                if (highScores.size() < 10 || i < highScores.get(highScores.size() - 1).attempts) {
                    System.out.print("You made it to the top 10! Enter your username: ");
                    String username = input.next();
                    highScores.add(new Score(username, i));
                    Collections.sort(highScores);
                    if (highScores.size() > 10) {
                        highScores.remove(highScores.size() - 1);
                    }
                    writeHighScores(highScores);
                }
                break;
            }
            if (i > attempts) {
                System.out.println("You have out of chances. Thanks for playing!");
            }
        }

    }

    private static void mediumLevel() {
        System.out.println("Great! You have selected the Medium difficulty level");
        System.out.println("Let's start the game ");
        attempts = 5;
        //System.out.println("Random number! "+answers);
        int i = 1;
        while (i <= attempts) {
            System.out.print("Enter your guess!: ");
            int mediumLevelGuess = input.nextInt();

            if (mediumLevelGuess < answers) {
                System.out.println("Incorrect! The number is greater than " + mediumLevelGuess);
                i++;
            } else if (mediumLevelGuess > answers) {
                System.out.println("Incorrect! The number is less than " + mediumLevelGuess);
                i++;
            } else {
                System.out.println("Congratulations! You guessed the correct number in " + i + " attempts.");
                if (highScores.size() < 10 || i < highScores.get(highScores.size() - 1).attempts) {
                    System.out.print("You made it to the top 10! Enter your username: ");
                    String username = input.next();
                    highScores.add(new Score(username, i));
                    Collections.sort(highScores);
                    if (highScores.size() > 10) {
                        highScores.remove(highScores.size() - 1);
                    }
                    writeHighScores(highScores);
                }
                break;
            }
            if (i > attempts) {
                System.out.println("You have out of chances. Thanks for playing!");
            }
        }

    }

    private static void hardLevel() {
        System.out.println("Great! You have selected the Hard difficulty level");
        System.out.println("Let's start the game ");
        attempts = 3;
        //System.out.println("Random number! "+answers);
        int i = 1;
        while (i <= attempts) {
            System.out.print("Enter your guess!: ");
            int hardLevelGuess = input.nextInt();
            System.out.println("Answer " + answers);
            if (hardLevelGuess < answers) {
                System.out.println("Incorrect! The number is greater than " + hardLevelGuess);
                i++;
            } else if (hardLevelGuess > answers) {
                System.out.println("Incorrect! The number is less than " + hardLevelGuess);
                i++;
            } else {
                System.out.println("Congratulations! You guessed the correct number in " + i + " attempts.");
                if (highScores.size() < 10 || i < highScores.get(highScores.size() - 1).attempts) {
                    System.out.print("You made it to the top 10! Enter your username: ");
                    String username = input.next();
                    highScores.add(new Score(username, i));
                    Collections.sort(highScores);
                    if (highScores.size() > 10) {
                        highScores.remove(highScores.size() - 1);
                    }
                    writeHighScores(highScores);
                }
                break;
            }

            if (i > attempts) {
                System.out.println("You have out of chances. Thanks for playing!");
            }

        }

    }

    private static List<Score> readHighScore() {
        List<Score> highScores = new ArrayList<>();
        try {
            File file = new File("highscores.txt");
            if (file.exists()) {
                Scanner fileReader = new Scanner(file);
                while (fileReader.hasNextLine()) {
                    String line = fileReader.nextLine();
                    String[] parts = line.split(":");
                    highScores.add(new Score(parts[0], Integer.parseInt(parts[1].trim())));
                }
                fileReader.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the high scores.");
            e.printStackTrace();
        }
        return highScores;
    }

    private static void writeHighScores(List<Score> highScores) {
        try {
            FileWriter writer = new FileWriter("highscores.txt");
            for (Score score : highScores) {
                writer.write(score.username + ": " + score.attempts + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing the high scores.");
            e.printStackTrace();
        }
    }

    private static void displayHighScores() {
        System.out.println("Top 10 High Scores:");
        for (Score score : highScores) {
            System.out.println(score.username + ": " + score.attempts + " attempts");
        }
    }

    private static class Score implements Comparable<Score> {
        String username;
        int attempts;

        Score(String username, int attempts) {
            this.username = username;
            this.attempts = attempts;
        }

        @Override
        public int compareTo(Score other) {
            return Integer.compare(this.attempts, other.attempts);
        }
    }
}
