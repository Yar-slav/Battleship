import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShotTest {

  Factory factory = new Factory();
  Shot shot = new Shot();

  @BeforeEach
  void fillTablesEmptyValuesTest() {
    factory.fillTablesEmptyValues();
  }

  @Test
  void shotPlayer_IncorrectCoordinates() {
    String input = "A12\nA\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
    Shot shot = new Shot();

    List<Coordinates[]> otherPlayerShips = Factory.createShipPlayer2();
    String[][] playerBoardEnemy = Factory.player1BoardEnemy;
    String[][] otherPlayerBoard = Factory.getPlayer2BoardWithShips();

    String result = shot.shotPlayer(otherPlayerShips, playerBoardEnemy, otherPlayerBoard);

    assertEquals(Shot.WRONG_COORDINATE, result);
  }

  @Test
  void shotPlayer_PlaceWasEmptySoItWasMiss() {
    String input = "A10\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
    Shot shot = new Shot();

    List<Coordinates[]> otherPlayerShips = Factory.createShipPlayer2();
    String[][] playerBoardEnemy = Factory.player1BoardEnemy;
    String[][] otherPlayerBoard = Factory.getPlayer2BoardWithShips();

    String result = shot.shotPlayer(otherPlayerShips, playerBoardEnemy, otherPlayerBoard);

    assertEquals(Shot.SHOT_MISS, result);
  }

  @Test
  void shotPlayer_ShipSankAndThanMiss() {
    String input = "A9\nB9\nA10\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
    Shot shot = new Shot();

    List<Coordinates[]> otherPlayerShips = Factory.createShipPlayer2();
    String[][] playerBoardEnemy = Factory.player1BoardEnemy;
    String[][] otherPlayerBoard = Factory.getPlayer2BoardWithShips();

    String result = shot.shotPlayer(otherPlayerShips, playerBoardEnemy, otherPlayerBoard);

    assertEquals(Shot.SHOT_MISS, result);
  }

  @Test
  void shotPlayer_PlayerWin() {
    String input = "A1\nB1\nC1\nD1\nE1\nA3\nB3\nC3\nD3\nA5\nB5\nC5\nA7\nB7\nC7\nA9\nB9";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
    Shot shot = new Shot();

    List<Coordinates[]> otherPlayerShips = Factory.createShipPlayer2();
    String[][] playerBoardEnemy = Factory.player1BoardEnemy;
    String[][] otherPlayerBoard = Factory.getPlayer2BoardWithShips();

    String result = shot.shotPlayer(otherPlayerShips, playerBoardEnemy, otherPlayerBoard);

    assertEquals(Shot.SHOT_WIN, result);
  }

  @Test
  void checkShot_WrongCoordinate() {
    boolean actual = shot.checkShot(new Coordinates(11, 1), Factory.player1BoardEnemy);
    assertFalse(actual);
  }

  @Test
  void checkShot_YouAlreadyShotHereThisPlaceIsMiss() {
    String[][] player1BoardEnemy = Factory.player1BoardEnemy;
    player1BoardEnemy[1][1] = Board.MISS;
    boolean actual = shot.checkShot(new Coordinates(1, 1), player1BoardEnemy);
    assertFalse(actual);
  }

  @Test
  void checkShot_YouAlreadyShotHereThisPlaceShip() {
    String[][] player1BoardEnemy = Factory.player1BoardEnemy;
    player1BoardEnemy[1][1] = Board.HIT_SHIP;
    boolean actual = shot.checkShot(new Coordinates(1, 1), player1BoardEnemy);
    assertFalse(actual);
  }
}