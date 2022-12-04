import java.util.Objects;
import java.util.Scanner;

public class Game {
    public Board board = new Board();
    Player playerOne = new Player();
    int level = 0;
    int empty = 60;

    Scanner in = new Scanner(System.in);
    Player playerTwo;
    Game() throws Exception {

    }
    private int read() {
        int index;
        try {
            index = in.nextInt();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("plz try again");
            in = new Scanner(System.in);
            return read();
        }
        return index;
    }
    private int choice(){
        int ans = 1;
        while (true) {
            System.out.println("Who is going to be the second player?");
            System.out.println("1 - computer, easy level");
            System.out.println("2 - computer, hard level");
            System.out.println("3 - a person");
            ans = read();
            if (Objects.equals(ans, 1) || Objects.equals(ans, 2) || Objects.equals(ans, 3))
                break;
            else
                System.out.println("try again");
        }
        return ans;
    }
    public void start() {
        int var = choice();
        if (var == 1 || var == 2) {
            playerTwo = new ComputerPlayer(var);
        }
        if (var == 3) {
            playerTwo = new Player();
        }
        playerOne.setPlayerColor(1);
        playerOne.setOppositeColor(0);
        playerTwo.setPlayerColor(0);
        playerTwo.setOppositeColor(1);
    }
    public void play() throws Exception {
        Boolean first = playerOne.choice(board);
        empty--;
        Boolean second = playerTwo.choice(board);
        empty--;
        updatePoints();
        if (empty > 0 && (first || second)) {
            play();
        }
    }
    public void updatePoints() {
        int pointsOne = 0;
        int pointsTwo = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getFieldByIndex(i, j).getColor() == 1) {
                    pointsOne++;
                }
                if (board.getFieldByIndex(i, j).getColor() == 0) {
                    pointsTwo++;
                }
            }
        }
        System.out.println("PlayerOne: " + pointsOne);
        System.out.println("PlayerTwo: " + pointsTwo);
        playerOne.setPlayerPoints(pointsOne);
        playerTwo.setPlayerPoints(pointsTwo);

    }
    public void finish() {
        System.out.println("Игра окончена со счетом " + playerOne.getPoints() + " " + playerTwo.getPoints());
    }
}

