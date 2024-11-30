import java.util.Scanner;
import java.util.Random;

public class GuessTheNumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Rules: Guess the number between 1 and 100. You will have a limited number of attempts.");

        int totalRounds = 5;
        int totalScore = 0;

        for (int round = 1; round <= totalRounds; round++) {
            System.out.println("\n--- Round " + round + " ---");
            int targetNumber = random.nextInt(100) + 1; // Random number between 1 and 100
            int maxAttempts = 7;
            int attempts = 0;
            boolean isGuessed = false;

            System.out.println("I have chosen a number between 1 and 100. You have " + maxAttempts + " attempts to guess it!");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == targetNumber) {
                    System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                    int roundScore = (maxAttempts - attempts + 1) * 10; // Points decrease with more attempts
                    totalScore += roundScore;
                    System.out.println("You earned " + roundScore + " points in this round.");
                    isGuessed = true;
                    break;
                } else if (userGuess < targetNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }

            if (!isGuessed) {
                System.out.println("Sorry, you've used all your attempts. The number was: " + targetNumber);
            }

            System.out.println("Your total score so far: " + totalScore);
        }

        System.out.println("\nGame Over!");
        System.out.println("Your final score: " + totalScore);
        scanner.close();
    }
}
