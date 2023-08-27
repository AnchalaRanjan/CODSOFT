import java.util.Scanner;
import java.util.Random;

public class NumberGame {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        int minimumrange = 1;
        int maximumrange = 100;
        int maximumattempts = 5;
        int score = 0;

        System.out.println("Welcome to the Number Guessing Game");
        boolean playAgain = true;

        while (playAgain) {
            int targetNumber = random.nextInt(maximumrange - minimumrange + 1) + minimumrange;
            System.out.println("I have generated a number between 1 and 100. Try to guess it");

            int attempts = 0;
            boolean guessedCorrectly = false;

            while (attempts < maximumattempts) {
                System.out.print("Guess the number: ");
                int userGuess = input.nextInt();
                attempts++;

                if (userGuess == targetNumber) {
                    System.out.println("Many many Congratulations! You guessed it right!");
                    guessedCorrectly = true;
                    score += maximumattempts - attempts + 1;
                    break;
                } else if (userGuess < targetNumber) {
                    System.out.println("Too low. Try again.");
                } else {
                    System.out.println("Too high. Try again.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you have used all your attempts. The number was: " + targetNumber);
            }

            System.out.print("Your score: " + score + "\nPlay again ? (yes/no): ");
            String playAgainResponse = input.next().toLowerCase();

            if (!playAgainResponse.equals("yes")) {
                playAgain = false;
            }
        }

        System.out.println("Thank you for playing! Your Final score: " + score);
        input.close();
    }
}
