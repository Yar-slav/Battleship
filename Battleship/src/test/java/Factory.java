import java.util.ArrayList;
import java.util.List;

public class Factory {
  public static String[][] player1Board = new String[Board.LINE][Board.COLUMN];
  public static String[][] player2Board = new String[Board.LINE][Board.COLUMN];
  public static String[][] player1BoardEnemy = new String[Board.LINE][Board.COLUMN];
  public static String[][] player2BoardEnemy = new String[Board.LINE][Board.COLUMN];
  public static String[][] playerBoard() {
    Board board = new Board();
    String[][] playerBoard = board.player1Board;
    for (int i = 0; i < Board.LINE; i++) {
      for (int j = 0; j < Board.COLUMN; j++) {
        playerBoard[i][j] = Board.EMPTY;
      }
    }
    playerBoard[3][3] = Board.SHIPS;
    playerBoard[3][4] = Board.SHIPS;
    return playerBoard;
  }

  public void fillTablesEmptyValues() {
    for (int i = 0; i < Board.LINE; i++) {
      for (int j = 0; j < Board.COLUMN; j++) {
        player1Board[i][j] = Board.EMPTY;
        player2Board[i][j] = Board.EMPTY;
        player1BoardEnemy[i][j] = Board.EMPTY;
        player2BoardEnemy[i][j] = Board.EMPTY;
      }
    }
  }

  public static List<Coordinates[]> createShipPlayer1() {
    List<Coordinates[]> playerShipsExcepted = new ArrayList<>();
    Coordinates[] ship1 = new Coordinates[5];
    ship1[0] = new Coordinates(0, 0);
    ship1[1] = new Coordinates(0, 1);
    ship1[2] = new Coordinates(0, 2);
    ship1[3] = new Coordinates(0, 3);
    ship1[4] = new Coordinates(0, 4);
    playerShipsExcepted.add(ship1);

    Coordinates[] ship2 = new Coordinates[4];
    ship2[0] = new Coordinates(2, 0);
    ship2[1] = new Coordinates(2, 1);
    ship2[2] = new Coordinates(2, 2);
    ship2[3] = new Coordinates(2, 3);
    playerShipsExcepted.add(ship2);

    Coordinates[] ship3 = new Coordinates[3];
    ship3[0] = new Coordinates(4, 0);
    ship3[1] = new Coordinates(4, 1);
    ship3[2] = new Coordinates(4, 2);
    playerShipsExcepted.add(ship3);

    Coordinates[] ship4 = new Coordinates[3];
    ship4[0] = new Coordinates(6, 0);
    ship4[1] = new Coordinates(6, 1);
    ship4[2] = new Coordinates(6, 2);
    playerShipsExcepted.add(ship4);

    Coordinates[] ship5 = new Coordinates[2];
    ship5[0] = new Coordinates(8, 0);
    ship5[1] = new Coordinates(8, 1);
    playerShipsExcepted.add(ship5);

    return playerShipsExcepted;
  }

  public static List<Coordinates[]> createShipPlayer2() {
    List<Coordinates[]> playerShipsExcepted = new ArrayList<>();
    Coordinates[] ship1 = new Coordinates[5];
    ship1[0] = new Coordinates(0, 0);
    ship1[1] = new Coordinates(1, 0);
    ship1[2] = new Coordinates(2, 0);
    ship1[3] = new Coordinates(3, 0);
    ship1[4] = new Coordinates(4, 0);

    playerShipsExcepted.add(ship1);

    Coordinates[] ship2 = new Coordinates[4];
    ship2[0] = new Coordinates(0, 2);
    ship2[1] = new Coordinates(1, 2);
    ship2[2] = new Coordinates(2, 2);
    ship2[3] = new Coordinates(3, 2);
    playerShipsExcepted.add(ship2);

    Coordinates[] ship3 = new Coordinates[3];
    ship3[0] = new Coordinates(0, 4);
    ship3[1] = new Coordinates(1, 4);
    ship3[2] = new Coordinates(2, 4);
    playerShipsExcepted.add(ship3);

    Coordinates[] ship4 = new Coordinates[3];
    ship4[0] = new Coordinates(0, 6);
    ship4[1] = new Coordinates(1, 6);
    ship4[2] = new Coordinates(2, 6);
    playerShipsExcepted.add(ship4);

    Coordinates[] ship5 = new Coordinates[2];
    ship5[0] = new Coordinates(0, 8);
    ship5[1] = new Coordinates(1, 8);
    playerShipsExcepted.add(ship5);

    return playerShipsExcepted;
  }

  public static String[][] getPlayer2BoardWithShips() {
    for (Coordinates[] c: createShipPlayer2()) {
      for (Coordinates coordinates: c) {
        player2Board[coordinates.line][coordinates.column] = Board.SHIPS;
      }
    }
    return player2Board;
  }
}
