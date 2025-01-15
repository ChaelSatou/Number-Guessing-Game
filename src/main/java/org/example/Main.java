package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// Author: Chael Candeza
public class Main {

    static int answers = (int)(Math.random() * 100);
    static int attempts;
    static int highScore = readHighScore();

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Number Guessing Game!");
        System.out.println("I'm thinking of a number between 1 and 100");
        System.out.println("You have a different chances according to the difficulty you've selected.");
        System.out.println("Please select the difficulty level: ");
        System.out.println("""
                1. Easy (10 chances)
                2. Medium (5 chances)
                3. Hard (3 chances)""");
        System.out.print("Enter your choice: ");
        int choice = input.nextInt();
        switch(choice){
            case 1:
                easyLevel();
                break;
            case 2:
                mediumLevel();
                break;
            case 3:
                hardLevel();
                break;
            default:
                System.out.println("Invalid choice try again!");
        }
        input.close();
    }
    public static void easyLevel(){
        System.out.println("Great! You have selected the Easy difficulty level");
        System.out.println("Let's start the game ");
        attempts = 10;
        //System.out.println("Random number! "+answers);
        int i = 1;
        while ( i <=attempts ){
            System.out.print("Enter your guess!: ");
            int easyLevelGuess = input.nextInt();

            if (easyLevelGuess < answers){
                System.out.println("Incorrect! The number is greater than "+easyLevelGuess);
                i++;
            }
            else if (easyLevelGuess > answers) {
                System.out.println("Incorrect! The number is less than "+easyLevelGuess);
                i++;
            }
            else {
                System.out.println("Congratulations! You guessed the correct number in "+ i +" attempts.");
                if (i < highScore || highScore == -1 ){
                    System.out.println("New High Score!");
                    writeHighScores(i);
                }
                break;
            }
            if (i > attempts){
                System.out.println("You have out of chances. Thanks for playing!");
            }
        }

    }

    public static void mediumLevel(){
        System.out.println("Great! You have selected the Medium difficulty level");
        System.out.println("Let's start the game ");
        attempts = 5;
        //System.out.println("Random number! "+answers);
        int i = 1;
        while ( i <=attempts ){
            System.out.print("Enter your guess!: ");
            int mediumLevelGuess = input.nextInt();
            
            // I remove the static value for answers and used the math.random
            if (mediumLevelGuess < answers){
                System.out.println("Incorrect! The number is greater than "+mediumLevelGuess);
                i++;
            }
            else if (mediumLevelGuess > answers) {
                System.out.println("Incorrect! The number is less than "+mediumLevelGuess);
                i++;
            }
            else {
                System.out.println("Congratulations! You guessed the correct number in "+ i +" attempts.");
                break;
            }
            if (i > attempts){
                System.out.println("You have out of chances. Thanks for playing!");
            }
        }

    }

    public static void hardLevel(){
        System.out.println("Great! You have selected the Hard difficulty level");
        System.out.println("Let's start the game ");
        attempts = 3;
        //System.out.println("Random number! "+answers);
        int i = 1;
        while ( i <=attempts ){
            System.out.print("Enter your guess!: ");
            int hardLevelGuess = input.nextInt();

            if (hardLevelGuess < answers){
                System.out.println("Incorrect! The number is greater than "+hardLevelGuess);
                i++;
            }

            else if (hardLevelGuess > answers) {
                System.out.println("Incorrect! The number is less than "+hardLevelGuess);
                i++;
            }

            else {
                System.out.println("Congratulations! You guessed the correct number in "+ i +" attempts.");
                break;
            }

            if (i > attempts){
                System.out.println("You have out of chances. Thanks for playing!");
            }

        }

    }

    public static int readHighScore(){
        try{
            File file = new File("highscore.txt");
            if (file.exists()){
                Scanner fileReader = new Scanner(file);
                if (fileReader.hasNextInt()){
                    return fileReader.nextInt();
                }
                fileReader.close();
            }
        }
        catch (IOException e) {
            System.out.println("Error on reading the high scores.");
            e.printStackTrace();
        }
        return -1;
    }

    public static void writeHighScores (int score){
        try {
            FileWriter fileWriter = new FileWriter("highscore.txt");
            fileWriter.write(Integer.toString(score));
            fileWriter.close();
        }
        catch (IOException e){
            System.out.println("Error on writing the highscore.");
            e.printStackTrace();

        }
    }
}