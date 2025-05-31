import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int maxAttempts = 7;
        int lowerBound = 1;
        int upperBound = 100;
        int score = 0;
        boolean playAgain;

        System.out.println("🎯 Welcome to the Number Guessing Game!");

        do {
            int targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("\n🔢 I have picked a number between " + lowerBound + " and " + upperBound + ".");
            System.out.println("You have " + maxAttempts + " attempts to guess it!");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int guess = scanner.nextInt();
                attempts++;

                if (guess == targetNumber) {
                    System.out.println("✅ Correct! You've guessed the number in " + attempts + " attempts.");
                    guessedCorrectly = true;
                    score++;
                    break;
                } else if (guess < targetNumber) {
                    System.out.println("⬆ Too low!");
                } else {
                    System.out.println("⬇ Too high!");
                }

                System.out.println("Attempts left: " + (maxAttempts - attempts));
            }

            if (!guessedCorrectly) {
                System.out.println("❌ You've run out of attempts! The number was: " + targetNumber);
            }

            // Ask to play again
            System.out.print("Do you want to play another round? (yes/no): ");
            String answer = scanner.next().toLowerCase();
            playAgain = answer.equals("yes");

        } while (playAgain);

        System.out.println("\n🏁 Game Over. You won " + score + " round(s). Thanks for playing!");
        scanner.close();
    }
}
