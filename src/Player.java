import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

// игрок
public class Player {
    protected int color = -1;
    Scanner in = new Scanner(System.in);
    private int points = 0;
    protected int oppositeColor = -1;

    // даем ему цвет
    void setPlayerColor(int i) {
        color = i;
    }

    // считаем сколько у него очков и записываем
    void setPlayerPoints(int i) {
        points = i;
    }

    // цвет противника
    void setOppositeColor(int i) {
        oppositeColor = i;
    }
    Player() {}

    // считываем ввод
    int read() {
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

    String readString() {
        String ans = in.next();
        return ans;
    }

    // выбор хода
    public Boolean choice(Board board) throws Exception {
        board.saveOldBoard();
        ArrayList<Board.Coord> a = board.possibleTurns(color, oppositeColor);
        if (a.isEmpty()) {
            System.out.println("Player skips the turn");
            return false;
        }
        System.out.println("Possible turns for you: ");
        for (int i = 0; i < a.size(); i++) {
            System.out.println("(" + (1 + a.get(i).x) + ", " + (1 + a.get(i).y) + ")");
        }
        System.out.println(board.toString());
        int x = read();
        int y = read();
        x--;
        y--;
        while (board.getFieldByIndex(x, y).getColor() != 3) {
            System.out.println("The field is not able for your turn. Try again");
            x = read();
            y = read();
            x--;
            y--;
        }
        for (int i = 0; i < a.size(); i++) {
            board.getFieldByIndex(a.get(i).x, a.get(i).y).setColor(-1);
        }
        board.setColorOnBoard(x, y, color);
        System.out.println(board.toString());
        return true;
    }

    // узнаем сколько очков
    public int getPoints(){
        return points;
    }
}
