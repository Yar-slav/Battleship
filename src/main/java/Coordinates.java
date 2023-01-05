import java.util.Objects;

public class Coordinates {
    final int line;
    final int column;

    public Coordinates(int letter, int number) {
        this.line = letter;
        this.column = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return line == that.line && column == that.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(line, column);
    }
}