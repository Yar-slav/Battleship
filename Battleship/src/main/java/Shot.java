import java.util.List;
import java.util.Scanner;

public class Shot {
    Scanner scanner = new Scanner(System.in);
    public static final String SHOT_WIN = "You sank the last ship. You won. Congratulations!";
    public static final String SHOT_SHIP_SANK = "You sank a ship! Specify a new target:";
    public static final String SHOT_SHIP_HIT = "You hit a ship!";
    public static final String SHOT_MISS = "You missed!";
    public static final String ALREADY_SHOT_HERE = "You've already shot here! Try again";
    public static final String WRONG_COORDINATE = "Error! You entered the wrong coordinates! Try again";

    public String checkShipIsSank(List<Coordinates[]> otherPlayerShips,
                                  String[][] otherPlayerBoard) {
        for (Coordinates[] ship : otherPlayerShips) {
            int partOfDestroyedShip = 0;
            for (Coordinates coordinate : ship) {
                if (otherPlayerBoard[coordinate.line][coordinate.column].equals(Board.HIT_SHIP)) {
                    partOfDestroyedShip++;
                    if (partOfDestroyedShip == ship.length) {
                        otherPlayerShips.remove(ship);
                        if (otherPlayerShips.size() == 0) {
                            return SHOT_WIN;
                        } else {
                            return SHOT_SHIP_SANK;
                        }
                    }
                }
            }
        }
        return SHOT_SHIP_HIT;
    }

    public String shotPlayer(List<Coordinates[]> otherPlayerShips,
                             String[][] playerBoardEnemy,
                             String[][] otherPlayerBoard) {
        boolean check = true;
        do {
            try {
                String coordinate = scanner.nextLine();
                String lineForInt = coordinate.substring(0, 1);
                int line = lineForInt.charAt(0) - 'A';
                int column = Integer.parseInt(coordinate.substring(1)) - 1;
                Coordinates coordinatesShot = new Coordinates(line, column);

                if (!checkShot(coordinatesShot, playerBoardEnemy)) {
                    continue;
                }

                if (otherPlayerBoard[line][column].equals(Board.SHIPS)) {
                    playerBoardEnemy[line][column] = Board.HIT_SHIP;
                    otherPlayerBoard[line][column] = Board.HIT_SHIP;
                    String status = checkShipIsSank(otherPlayerShips, otherPlayerBoard);
                    switch (status) {
                        case SHOT_WIN -> {
                            Board.showPlayerBoard(playerBoardEnemy);
                            return SHOT_WIN;
                        }
                        case SHOT_SHIP_SANK -> {
                            Board.showPlayerBoard(playerBoardEnemy);
                            System.out.println(SHOT_SHIP_SANK);
                            continue;
                        }
                        case SHOT_SHIP_HIT -> {
                            Board.showPlayerBoard(playerBoardEnemy);
                            System.out.println(SHOT_SHIP_HIT);
                            continue;
                        }
                    }
                }
                if (otherPlayerBoard[line][column].equals(Board.EMPTY)) {
                    playerBoardEnemy[line][column] = Board.MISS;
                    otherPlayerBoard[line][column] = Board.MISS;
                }
            } catch (Exception e) {
                return WRONG_COORDINATE;
            }
            check = false;
        } while (check);
        return SHOT_MISS;
    }

    public boolean checkShot(Coordinates coordinatesShot, String[][] playerBoardEnemy) {

        if (coordinatesShot.line < 0 || coordinatesShot.line >= 10 || coordinatesShot.column < 0 || coordinatesShot.column >= 10) {
            System.out.println(WRONG_COORDINATE);
            return false;
        }

        if (playerBoardEnemy[coordinatesShot.line][coordinatesShot.column].equals(Board.MISS)
                || playerBoardEnemy[coordinatesShot.line][coordinatesShot.column].equals(Board.HIT_SHIP)) {
            System.out.println(ALREADY_SHOT_HERE);
            return false;
        }
        return true;
    }
}

