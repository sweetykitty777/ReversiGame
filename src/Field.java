// клетка поля
public class Field {

    // цвет клетки, -1 - незакрашенная, 0 - черная, 1 - белая
    private int color = -1;

    public int getColor() {
        return this.color;
    }

    public void setColor(int value) throws Exception {
        this.color = value;
    }

    Field() {

    }
}
