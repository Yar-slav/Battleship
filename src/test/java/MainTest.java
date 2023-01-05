import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.Test;

class MainTest {

  @Test
  void putShipsToBoard() {
    String input = "A1 A5\nC1 C4\nE1 E3\nG1 G3\nI1 I2\n\nA1 E1\nA3 D3\nA5 C5\nA7 C7\nA9 B9\n\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    String result = Main.putShipsToBoard();
    assertEquals(Main.PLAYER1, result);
  }

}