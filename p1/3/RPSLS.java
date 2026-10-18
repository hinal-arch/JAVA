import java.util.Random;
import java.util.Scanner;

public class RPSLS {
    enum Move { ROCK, PAPER, SCISSORS, LIZARD, SPOCK }

    static int winner(Move a, Move b) {
        if (a == b) return 0;

        return switch (a) {
            case ROCK -> (b == Move.SCISSORS || b == Move.LIZARD) ? 1 : -1;
            case PAPER -> (b == Move.ROCK || b == Move.SPOCK) ? 1 : -1;
            case SCISSORS -> (b == Move.PAPER || b == Move.LIZARD) ? 1 : -1;
            case LIZARD -> (b == Move.PAPER || b == Move.SPOCK) ? 1 : -1;
            case SPOCK -> (b == Move.ROCK || b == Move.SCISSORS) ? 1 : -1;
        };
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        Move[] moves = Move.values();
        int playerScore = 0, computerScore = 0;

        for (int round = 1; round <= 5; round++) {
            System.out.println("\nRound " + round);
            System.out.print("Enter ROCK, PAPER, SCISSORS, LIZARD, or SPOCK: ");
            Move player = Move.valueOf(sc.next().toUpperCase());
            Move computer = moves[random.nextInt(moves.length)];

            System.out.println("You: " + player + " | Computer: " + computer);
            int result = winner(player, computer);

            if (result == 1) {
                System.out.println("You win this round!");
                playerScore++;
            } else if (result == -1) {
                System.out.println("Computer wins this round!");
                computerScore++;
            } else {
                System.out.println("Tie!");
            }
        }

        System.out.println("Final score: You " + playerScore + " - "
                + computerScore + " Computer");
        if (playerScore > computerScore) {
            System.out.println("You win overall!");
        } else if (computerScore > playerScore) {
            System.out.println("Computer wins overall!");
        } else {
            System.out.println("Overall tie!");
        }
        sc.close();
    }
}