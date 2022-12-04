import java.util.ArrayList;
import java.util.Objects;

public class ComputerPlayer extends Player {
    // уровень сложности
    private int level;

    public ComputerPlayer(int lvl) {
        level = lvl;
    }

    // выбор куда пойти
    @Override
    public Boolean choice(Board board) throws Exception {
        ArrayList<Board.Coord> a = board.possibleTurns(color, oppositeColor);
        if (a.isEmpty()) {
            System.out.println("Player skips the turn");
            return false;
        }
        double maxi = 0;
        Board.Coord coordinats = a.get(0); //!!! а если 0
        for (int i = 0; i < a.size(); i++) {
            if (estimate(a.get(i).x, a.get(i).y, board) > maxi) {
                coordinats = a.get(i);
            }
        }
        for (int i = 0; i < a.size(); i++) {
            board.getFieldByIndex(a.get(i).x, a.get(i).y).setColor(-1);
        }
        board.setColorOnBoard(coordinats.x, coordinats.y, color);
        return true;
    }
    // оценочная функция
    private double estimate(int x, int y, Board board) throws Exception {
        double result = 0;
        if ((x == 0 && y == 0) || (x == 8 && y == 8) || (x == 0 && y == 8) || (x == 8 || y == 0)) {
            result = 0.8;
        } else if (x == 0 || x == 8 || y == 0 || y == 8) {
            result = 0.4;
        }
        int changed = 0;
        Boolean change = false;
        for (int i = x - 1; i >= 0; i--) {
            if (board.getFieldByIndex(i, y).getColor() == -1 || board.getFieldByIndex(i, y).getColor() == 3) {
                break;
            }
            if (Objects.equals(board.getFieldByIndex(i, y).getColor(), color)) {
                change = true;
                break;
            }
            if (x == 0 || x == 8 || y == 0 || y == 8) {
                changed += 2;
            } else {
                changed++;
            }
        }
        if (!change)
            changed = 0;
        change = false;
        int changed2 = 0;
        for (int i = x + 1; i < 8; i++) {
            if (board.getFieldByIndex(i, y).getColor() == -1 || board.getFieldByIndex(i, y).getColor() == 3) {
                break;
            }
            if (board.getFieldByIndex(i, y).getColor() == color) {
                change = true;
                break;
            }
            if (x == 0 || x == 8 || y == 0 || y == 8) {
                changed2 += 2;
            } else {
                changed2++;
            }
        }
        if (change) {
            changed += changed2;
        }
        // actually I hate my code in this project it is terrible
        result += changed;
        changed = 0;
        change = false;
        for (int i = y - 1; i >= 0; i--) {
            if (board.getFieldByIndex(x, i).getColor() == -1 || board.getFieldByIndex(x, i).getColor() == 3) {
                break;
            }
            if (board.getFieldByIndex(x, i).getColor() == color) {
                change = true;
                break;
            }
            if (x == 0 || x == 8 || y == 0 || y == 8) {
                changed += 2;
            } else {
                changed++;
            }
        }
        if (!change)
            changed = 0;
        changed2 = 0;
        change = false;
        for (int i = y + 1; i < 8; i++) {
            if (board.getFieldByIndex(x, i).getColor() == -1 || board.getFieldByIndex(x, i).getColor() == 3) {
                break;
            }
            if (board.getFieldByIndex(x, i).getColor() == color) {
                change = true;
                break;
            }
            if (x == 0 || x == 8 || y == 0 || y == 8) {
                changed2 += 2;
            } else {
                changed2++;
            }
        }
        if (change)
            changed += changed2;
        result += changed;

        int changed1 = 0;
        changed2 = 0;
        int changed3 = 0;
        int changed4 = 0;
        change = false;

        int j = y;
        for (int i = x - 1; i > 0; i--) {
            j--;
            if (j == -1)
                break;
            if (board.getFieldByIndex(i, j).getColor() == -1 && i != x && j != y) {
                break;
            }
            if (Objects.equals(board.getFieldByIndex(i, j).getColor(), color) && x != i && y != j) {
                change = true;
                break;
            }
            if (x == 0 || x == 8 || y == 0 || y == 8) {
                changed1 += 2;
            } else {
                changed1++;
            }
        }
        if (!change)
            changed1 = 0;
        change = false;
        j = y;
        for (int i = x + 1; i < 8; i++) {
            j++;
            if (j == 8)
                break;
            if (board.getFieldByIndex(i, j).getColor() == -1 && i != x && j != y) {
                break;
            }
            if (Objects.equals(board.getFieldByIndex(i, j).getColor(), color) && x != i && y != j) {
                change = true;
                break;
            }
            if (x == 0 || x == 8 || y == 0 || y == 8) {
                changed2 += 2;
            } else {
                changed2++;
            }
        }
        if (!change)
            changed2 = 0;
        change = false;
        j = y;
        for (int i = x + 1; i < 8; i++) {
            j--;
            if (j == -1)
                break;
            if (board.getFieldByIndex(i, j).getColor() == -1 && i != x && j != y) {
                break;
            }
            if (Objects.equals(board.getFieldByIndex(i, j).getColor(), color) && x != i && y != j) {
                change = true;
                break;
            }
            if (x == 0 || x == 8 || y == 0 || y == 8) {
                changed3 += 2;
            } else {
                changed3++;
            }
        }
        if (!change)
            changed3 = 0;
        change = false;
        j = y;
        for (int i = x - 1; i > 0; i--) {
            j++;
            if (j == 8)
                break;
            if (board.getFieldByIndex(i, j).getColor() == -1 && i != x && j != y) {
                break;
            }
            if (Objects.equals(board.getFieldByIndex(i, j).getColor(), color) && x != i && y != j) {
                change = true;
                break;
            }
            if (x == 0 || x == 8 || y == 0 || y == 8) {
                changed4 += 2;
            } else {
                changed4++;
            }
        }
        if (!change)
            changed4 = 0;
        change = false;
        result += (changed1 + changed2 + changed3 + changed4);
        return result;
    }
}
