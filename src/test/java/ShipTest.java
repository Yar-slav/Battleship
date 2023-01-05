import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.api.Test;

class ShipTest {

  @Test
  void checkAccordFieldSize_True() {
    boolean actual = Ship.checkAccordFieldSize(0, 5, 0, 9);
    assertTrue(actual);
  }

  @Test
  void checkAccordFieldSize_False() {
    boolean actual = Ship.checkAccordFieldSize(0, 7, 0, 11);
    assertFalse(actual);
  }

  @Test
  void checkShipSize_True() {
    boolean actual = Ship.checkShipSize(3, 0, 0, 1, 3);
    assertTrue(actual);
  }

  @Test
  void checkShipSize_False() {
    boolean actual = Ship.checkShipSize(3, 0, 0, 1, 5);
    assertFalse(actual);
  }

  @Test
  void checkPlaceIsEmpty_False() {
    String[][] playerBoard = Factory.playerBoard();
    playerBoard[4][2] = Board.SHIPS;
    playerBoard[4][3] = Board.SHIPS;

    Coordinates[] shipCoordinate = new Coordinates[3];
    shipCoordinate[0] = new Coordinates(4, 0);
    shipCoordinate[1] = new Coordinates(4, 1);
    shipCoordinate[2] = new Coordinates(4, 2);

    boolean actual = Ship.checkPlaceIsEmpty(shipCoordinate, playerBoard);
    assertFalse(actual);
  }

  @Test
  void checkAroundOfShip_False() {
    String[][] playerBoard = Factory.playerBoard();

    Coordinates[] shipCoordinate = new Coordinates[3];
    shipCoordinate[0] = new Coordinates(4, 0);
    shipCoordinate[1] = new Coordinates(4, 1);
    shipCoordinate[2] = new Coordinates(4, 2);

    boolean actual = Ship.checkAroundOfShip(shipCoordinate, playerBoard);
    assertFalse(actual);
  }

  @Test
  void getCoordinatesShip_InputCorrectCoordinated() {
    String input = "A3 A1\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    Ship ship = new Ship();
    String[][] playerBoard = Factory.playerBoard();

    Coordinates[] shipCoordinateExcepted = new Coordinates[3];
    shipCoordinateExcepted[0] = new Coordinates(0, 2);
    shipCoordinateExcepted[1] = new Coordinates(0, 1);
    shipCoordinateExcepted[2] = new Coordinates(0, 0);

    Coordinates[] coordinatesShip = ship.getCoordinatesShip(3, playerBoard);
    assertArrayEquals(shipCoordinateExcepted, coordinatesShip);
  }

  @Test
  void getCoordinatesShip_InputIncorrectCoordinatedWithoutNumber() {
    String input = "A A1\nA1 C1\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    Ship ship = new Ship();
    String[][] playerBoard = Factory.playerBoard();

    Coordinates[] shipCoordinateExcepted = new Coordinates[3];
    shipCoordinateExcepted[0] = new Coordinates(0, 0);
    shipCoordinateExcepted[1] = new Coordinates(1, 0);
    shipCoordinateExcepted[2] = new Coordinates(2, 0);

    Coordinates[] coordinatesShip = ship.getCoordinatesShip(3, playerBoard);
    assertEquals(Ship.INCORRECT_DATA_ENTRY + "\n", out.toString());
    assertArrayEquals(shipCoordinateExcepted, coordinatesShip);
  }

  @Test
  void getCoordinatesShip_InputIncorrectCoordinatedNumberBiggerThanBoard() {
    String input = "A9 A11\nC1 A1\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    Ship ship = new Ship();
    String[][] playerBoard = Factory.playerBoard();

    Coordinates[] shipCoordinateExcepted = new Coordinates[3];
    shipCoordinateExcepted[0] = new Coordinates(2, 0);
    shipCoordinateExcepted[1] = new Coordinates(1, 0);
    shipCoordinateExcepted[2] = new Coordinates(0, 0);

    Coordinates[] coordinatesShip = ship.getCoordinatesShip(3, playerBoard);
    assertEquals(Ship.INCORRECT_DATA_ENTRY + "\n", out.toString());
    assertArrayEquals(shipCoordinateExcepted, coordinatesShip);
  }

  @Test
  void getCoordinatesShip_InputIncorrectShipLength() {
    String input = "A5 A9\nC1 A1\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    Ship ship = new Ship();
    String[][] playerBoard = Factory.playerBoard();

    Coordinates[] shipCoordinateExcepted = new Coordinates[3];
    shipCoordinateExcepted[0] = new Coordinates(2, 0);
    shipCoordinateExcepted[1] = new Coordinates(1, 0);
    shipCoordinateExcepted[2] = new Coordinates(0, 0);

    Coordinates[] coordinatesShip = ship.getCoordinatesShip(3, playerBoard);
    assertEquals(Ship.WRONG_SHIP_LENGTH + "\n", out.toString());
    assertArrayEquals(shipCoordinateExcepted, coordinatesShip);
  }

  @Test
  void getCoordinatesShip_InputInOccupiedPlace() {
    String input = "D4 D6\nC1 A1\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    Ship ship = new Ship();
    String[][] playerBoard = Factory.playerBoard();

    Coordinates[] shipCoordinateExcepted = new Coordinates[3];
    shipCoordinateExcepted[0] = new Coordinates(2, 0);
    shipCoordinateExcepted[1] = new Coordinates(1, 0);
    shipCoordinateExcepted[2] = new Coordinates(0, 0);

    Coordinates[] coordinatesShip = ship.getCoordinatesShip(3, playerBoard);
    assertEquals(Ship.OCCUPIED_PLACE + "\n", out.toString());
    assertArrayEquals(shipCoordinateExcepted, coordinatesShip);
  }

  @Test
  void getCoordinatesShip_InputCoordinatesIsToCloseToTheExistShip() {
    String input = "C4 C6\nC1 A1\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    Ship ship = new Ship();
    String[][] playerBoard = Factory.playerBoard();

    Coordinates[] shipCoordinateExcepted = new Coordinates[3];
    shipCoordinateExcepted[0] = new Coordinates(2, 0);
    shipCoordinateExcepted[1] = new Coordinates(1, 0);
    shipCoordinateExcepted[2] = new Coordinates(0, 0);

    Coordinates[] coordinatesShip = ship.getCoordinatesShip(3, playerBoard);
    assertEquals(Ship.PLACE_TO_CLOSE + "\n", out.toString());
    assertArrayEquals(shipCoordinateExcepted, coordinatesShip);
  }

  @Test
  void createPlayerShips_CorrectInput_True() {
    String input = "A1 A5\nC1 C4\nE1 E3\nG1 G3\nI1 I2\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    List<Coordinates[]> playerShipsExcepted = Factory.createShipPlayer1();
    Board board = new Board();
    Ship ship = new Ship();
    String[][] playerBoard = board.player1Board;
    for (int i = 0; i < Board.LINE; i++) {
      for (int j = 0; j < Board.COLUMN; j++) {
        playerBoard[i][j] = Board.EMPTY;
      }
    }

    List<Coordinates[]> playerShipsResult = ship.createPlayerShips(playerBoard);
    assertEquals(playerShipsExcepted.size(), playerShipsResult.size());
    for (int i = 0; i < 5; i++) {
      assertArrayEquals(playerShipsExcepted.get(i), playerShipsResult.get(i));
    }

  }
}
