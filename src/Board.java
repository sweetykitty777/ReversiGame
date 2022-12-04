import java.util.ArrayList;
import java.util.Objects;

// игровая доска
public final class Board {
    // координаты точки
    class Coord {
        Coord(int i, int j){
            x = i;
            y = j;
        }
        public int x;
        public int y;
    }

    // доска
    private Field[][] board = new Field[8][8];
    // старая версия доски
    private Field[][] oldBoard = new Field[8][8];

    // обращение к полю по индексу
    public Field getFieldByIndex(int x, int y) {
        return board[x][y];
    }

    // сохранение старой доски
    public void saveOldBoard(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                oldBoard[i][j] = board[i][j];
            }
        }
    }

    // шаг назад
    public void goBack() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(oldBoard[i][j].getColor());
            }
            System.out.println("");
        }
    }

    // красим перешедшие к нам фишки
    private void brush(int x, int y, int color) throws Exception {
        for (int i = x; i >= 0; i--) {
            if (i != x && board[i][y].getColor() == -1) {
                break;
            }
            if (board[i][y].getColor() == color && x != i) {
                for (int j = x; j > i; j--) {
                    this.getFieldByIndex(j, y).setColor(color);
                }
                break;
            }
        }
        for (int i = x; i < 8; i++) {
            if (i != x && board[i][y].getColor() == -1) {
                break;
            }
            if (board[i][y].getColor() == color && x != i) {
                for (int j = x; j < i; j++) {
                    this.getFieldByIndex(j, y).setColor(color);
                }
                break;
            }
        }
        for (int i = y; i >= 0; i--) {
            if (i != y && board[x][i].getColor() == -1) {
                break;
            }
            if (board[x][i].getColor() == color && y != i) {
                for (int j = y; j > i; j--) {
                    this.getFieldByIndex(x, j).setColor(color);
                }
                break;
            }
        }
        for (int i = y; i < 8; i++) {
            if (i != y && board[x][i].getColor() == -1) {
                break;
            }
            if (board[x][i].getColor() == color && y != i) {
                for (int j = y; j < i; j++) {
                    this.getFieldByIndex(x, j).setColor(color);
                }
                break;
            }
        }
        int j = y;
        for (int i = x - 1; i >= 0; i--) {
            j--;
            if (j == -1)
                break;
                if (board[i][j].getColor() == -1 && i != x && j != y) {
                    break;
                }
                if (Objects.equals(board[i][j].getColor(), color) && x != i && y != j) {
                    int f = y;
                    for (int h = x - 1; h >= i; h--) {
                        f--;
                        this.getFieldByIndex(h, f).setColor(color);
                        if (f == j)
                            break;
                    }
                    break;
                }
        }
        j = y;
        for (int i = x + 1; i < 8; i++) {
            j++;
            if (j == 8)
                break;
                if (board[i][j].getColor() == -1 && i != x && j != y) {
                    break;
                }
                if (Objects.equals(board[i][j].getColor(), color) && x != i && y != j) {
                    int f = j;
                    for (int h = i - 1; h >= x; h--) {
                        f--;
                        this.getFieldByIndex(h, f).setColor(color);
                        if (f == y)
                            break;
                    }
                    break;
                }
        }
        j = y;
        for (int i = x + 1; i < 8; i++) {
            j--;
            if (j == -1)
                break;
                if (board[i][j].getColor() == -1 && i != x && j != y) {
                    break;
                }
                if (Objects.equals(board[i][j].getColor(), color) && x != i && y != j) {
                    int f = y;
                    for (int h = x + 1; h <= i; h++) {
                        f --;
                        this.getFieldByIndex(h, f).setColor(color);
                        if (f == j)
                            break;
                    }
                    break;
                }
        }
        j = y;
        for (int i = x - 1; i >= 0; i--) {
            j++;
            if (j == 8)
                break;
                if (board[i][j].getColor() == -1 && i != x && j != y) {
                    break;
                }
                if (Objects.equals(board[i][j].getColor(), color) && x != i && y != j) {
                    int f = j;
                    for (int h = i + 1; h <= x; h++) {
                        f--;
                            this.getFieldByIndex(h, f).setColor(color);
                        if (f == y)
                            break;
                    }
                    break;
                }
            }
    }

    // ставим на доску фигурку
    public void setColorOnBoard(int x, int y, int color) throws Exception {
        board[x][y].setColor(color);
        brush(x, y, color);
    }

    // изучаем возможные ходы
    public ArrayList<Coord> possibleTurns(int color, int anticolor) throws Exception {
        ArrayList<Coord> coords = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getColor() == anticolor) {
                    if (i >= 1 && board[i - 1][j].getColor() == -1) {
                        if (countHorizontal(i - 1, j, color) > 0 || countVertical(i - 1, j, color) > 0 || countDiagonal(i - 1, j, color) > 0) {
                            board[i - 1][j].setColor(3);
                            coords.add(new Coord(i - 1, j));
                        }
                    }
                    if (j >= 1 && board[i][j - 1].getColor() == -1) {
                        if (countHorizontal(i, j - 1, color) > 0 || countVertical(i, j - 1, color) > 0 || countDiagonal(i, j - 1, color) > 0) {
                            board[i][j - 1].setColor(3);
                            coords.add(new Coord(i, j - 1));
                        }
                    }
                    if (i >= 1 && j >= 1 && board[i - 1][j - 1].getColor() == -1) {
                        if (countHorizontal(i - 1, j - 1, color) > 0 || countVertical(i - 1, j - 1, color) > 0 || countDiagonal(i - 1, j - 1, color) > 0) {
                            board[i - 1][j - 1].setColor(3);
                            coords.add(new Coord(i - 1, j - 1));
                        }
                    }
                    if (i < 7 && j >= 1 && board[i + 1][j - 1].getColor() == -1) {
                        if (countHorizontal(i + 1, j - 1, color) > 0 || countVertical(i + 1, j - 1, color) > 0 || countDiagonal(i + 1, j - 1, color) > 0) {
                            board[i + 1][j - 1].setColor(3);
                            coords.add(new Coord(i + 1, j - 1));
                        }
                    }
                    if (j < 7 && i >= 1 && board[i - 1][j + 1].getColor() == -1) {
                        if (countHorizontal(i - 1, j + 1, color) > 0 || countVertical(i - 1, j + 1, color) > 0 || countDiagonal(i - 1, j + 1, color) > 0) {
                            board[i - 1][j + 1].setColor(3);
                            coords.add(new Coord(i - 1, j + 1));
                        }
                    }
                    if (i < 7 && j < 7 && board[i + 1][j + 1].getColor() == -1) {
                        if (countHorizontal(i + 1, j + 1, color) > 0 || countVertical(i + 1, j + 1, color) > 0 || countDiagonal(i + 1, j + 1, color) > 0) {
                            board[i + 1][j + 1].setColor(3);
                            coords.add(new Coord(i + 1, j + 1));
                        }
                    }
                    if (j < 7 && board[i][j + 1].getColor() == -1) {
                        if (countHorizontal(i, j + 1, color) > 0 || countVertical(i, j + 1, color) > 0 || countDiagonal(i, j + 1, color) > 0) {
                            board[i][j + 1].setColor(3);
                            coords.add(new Coord(i, j + 1));
                        }
                    }
                    if (i < 7 && board[i + 1][j].getColor() == -1) {
                        if (countHorizontal(i + 1, j, color) > 0 || countVertical(i + 1, j, color) > 0 || countDiagonal(i + 1, j, color) > 0) {
                            board[i + 1][j].setColor(3);
                            coords.add(new Coord(i + 1, j));
                        }
                    }
                }
            }
        }
        return coords;
    }

    // смотрим захватим ли мы чужие фигурки в следующих трех методах для проверки можем ли мы сделать ход
    public int countHorizontal(int x, int y, int color) throws Exception {
        int changed = 0;
        Boolean change = false;
        for (int i = x - 1; i >= 0; i--) {
            if (board[i][y].getColor() == -1 || board[i][y].getColor() == 3) {
                break;
            }
            if (Objects.equals(board[i][y].getColor(), color)) {
                change = true;
                break;
            }
            changed++;
        }
        if (!change)
            changed = 0;
        change = false;
        int changed2 = 0;
        for (int i = x + 1; i < 8; i++) {
            if (board[i][y].getColor() == -1 || board[i][y].getColor() == 3) {
                break;
            }
            if (board[i][y].getColor() == color) {
                change = true;
                break;
            }
            changed2++;
        }
        if (change) {
            changed += changed2;
        }
        return changed;
    }
    public int countVertical(int x, int y, int color) throws Exception {
        int changed = 0;
        Boolean change = false;
        for (int i = y - 1; i >= 0; i--) {
            if (board[x][i].getColor() == -1 || board[x][i].getColor() == 3) {
                break;
            }
            if (board[x][i].getColor() == color) {
                change = true;
                break;
            }
            changed++;
        }
        if (!change)
            changed = 0;
        int changed2 = 0;
        change = false;
        for (int i = y + 1; i < 8; i++) {
            if (board[x][i].getColor() == -1 || board[x][i].getColor() == 3) {
                break;
            }
            if (board[x][i].getColor() == color) {
                change = true;
                break;
            }
            changed2++;
        }
        if (change)
            changed += changed2;
        return changed;
    }


    public int countDiagonal(int x, int y, int color) throws Exception {
        int changed1 = 0;
        int changed2 = 0;
        int changed3 = 0;
        int changed4 = 0;
        Boolean change = false;

        int j = y;
        for (int i = x - 1; i > 0; i--) {
            j--;
            if (j == -1)
                break;
            if (board[i][j].getColor() == -1 && i != x && j != y) {
                break;
            }
            if (Objects.equals(board[i][j].getColor(), color) && x != i && y != j) {
                change = true;
                break;
            }
            changed1++;
        }
        if (!change)
            changed1 = 0;
        change = false;
        j = y;
        for (int i = x + 1; i < 8; i++) {
            j++;
            if (j == 8)
                break;
            if (board[i][j].getColor() == -1 && i != x && j != y) {
                break;
            }
            if (Objects.equals(board[i][j].getColor(), color) && x != i && y != j) {
                change = true;
                break;
            }
            changed2++;
        }
        if (!change)
            changed2 = 0;
        change = false;
        j = y;
        for (int i = x + 1; i < 8; i++) {
            j--;
            if (j == -1)
                break;
            if (board[i][j].getColor() == -1 && i != x && j != y) {
                break;
            }
            if (Objects.equals(board[i][j].getColor(), color) && x != i && y != j) {
                change = true;
                break;
            }
            changed3++;
        }
        if (!change)
            changed3 = 0;
        change = false;
        j = y;
        for (int i = x - 1; i > 0; i--) {
            j++;
            if (j == 8)
                break;
            if (board[i][j].getColor() == -1 && i != x && j != y) {
                break;
            }
            if (Objects.equals(board[i][j].getColor(), color) && x != i && y != j) {
                change = true;
                break;
            }
            changed4++;
        }
        if (!change)
            changed4 = 0;
        change = false;
        return changed1 + changed2 + changed3 + changed4;
    }
    Board() throws Exception {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Field();
            }
        }
        board[3][3].setColor(0);
        board[4][4].setColor(0);
        board[3][4].setColor(1);
        board[4][3].setColor(1);
    }

    // печать доски
    @Override
    public String toString() {
        String res = "  ";
        for (int i = 1; i <= 8; i++) {
            res += i + " ";
        }
        res += "\n";
        for (int i = 0; i < 8; i++) {
            res += 1 + i + " ";
            for (int j = 0; j < 8; j++) {
                if (this.getFieldByIndex(i, j).getColor() == 1) {
                    res += "▪ ";
                }
                if (this.getFieldByIndex(i, j).getColor() == 0) {
                    res += "▫ ";
                }
                if (this.getFieldByIndex(i, j).getColor() == -1) {
                    res += "- ";
                }
                if (this.getFieldByIndex(i, j).getColor() == 3) {
                    res += "+ ";
                }
            }
            res += "\n";
        }
        return res;
    }
}