import java.util.Random;
import java.util.Scanner;

class NumberGuessingGame {
    private int secretNumber;
    private int userGuess;
    private int attempts = 0;
    private static final int MAX_ATTEMPTS = 5;

    NumberGuessingGame() {
        Random rand = new Random();
        secretNumber = rand.nextInt(100);
    }

    void takeUserGuess() {
        System.out.println("Guess the number between 0 and 99:");
        Scanner scanner = new Scanner(System.in);
        userGuess = scanner.nextInt();
        attempts++;
    }

    boolean isGameOver() {
        return userGuess == secretNumber || attempts >= MAX_ATTEMPTS;
    }

    void displayResult() {
        if (userGuess == secretNumber) {
            System.out.printf("Congratulations! You guessed it right, the number was %d.\n", secretNumber);
            System.out.printf("You guessed it in %d attempts.\n", attempts);
        } else {
            System.out.println("Sorry, you've run out of attempts. The secret number was " + secretNumber + ".");
        }
    }
}

public class NumberGuessingGameApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to play the game? (1 for yes, 0 for no)");
        int playAgain = scanner.nextInt();

        while (playAgain == 1) {
            NumberGuessingGame game = new NumberGuessingGame();
            boolean gameOver = false;

            while (!gameOver) {
                game.takeUserGuess();
                gameOver = game.isGameOver();
            }

            game.displayResult();

            System.out.println("Do you want to play again? (1 for yes, 0 for no)");
            playAgain = scanner.nextInt();
        }

        System.out.println("Thank you for playing. See you next time!");
    }
}
