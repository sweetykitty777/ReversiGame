import java.util.Objects;
import java.util.Scanner;

// точка входа
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int bestDiff = 0;
        String bestScore = "No games";
        while (true) {
            try {
                Game game = new Game();
                game.start();
                game.play();
                game.finish();
                if (game.playerOne.getPoints() - game.playerTwo.getPoints() >= bestDiff) {
                    bestScore = "Player One: " + game.playerOne.getPoints() + "\n" + "Player Two: " +
                            game.playerTwo.getPoints() + "\n";
                }
                if (game.playerTwo.getPoints() - game.playerOne.getPoints() >= bestDiff) {
                    bestScore = "Player Two: " + game.playerTwo.getPoints() + "\n" + "Player One: " +
                            game.playerOne.getPoints() + "\n";
                }
                Boolean end = false;
                while (true) {
                    System.out.println("Do you want to play again?");
                    System.out.println("y/n/s (to see the best score)");
                    String ans = in.next();
                    if (Objects.equals(ans, "y") || Objects.equals(ans, "n") || Objects.equals(ans, "s")) {
                        if (Objects.equals(ans, "y")) {
                            break;
                        }
                        if (Objects.equals(ans, "n")) {
                            end = true;
                            break;
                        }
                        if (Objects.equals(ans, "s")) {
                            System.out.println(bestScore);
                        }
                    } else {
                        System.out.println("try again");
                    }
                }
                if (end)
                    break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Lets try again");
            }
        }
    }
}