import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ship {
    Scanner scanner = new Scanner(System.in);
    public static final String INCORRECT_DATA_ENTRY = "Error! Incorrect data entry. Try again";
    public static final String OCCUPIED_PLACE = "Error! Place is occupied. Try again";
    public static final String WRONG_SHIP_LENGTH = "Error! Wrong length of the Submarine. Try again";
    public static final String PLACE_TO_CLOSE = "Error! You placed it too close to another one. Try again";
    private int size;
    private String name;

    public Ship(int size, String name) {
        this.size = size;
        this.name = name;
    }

    public Ship() {
    }

    // check correctly introduction
    static boolean checkCorrectIntroduction(int startLine, int finishLine, int startColumn, int finishColumn) {
        if (startLine < 0 || startLine >= 10 || finishLine < 0 || finishLine >= 10
                || startColumn < 0 || startColumn >= 10 || finishColumn < 0 || finishColumn >= 10) {
            System.out.println(INCORRECT_DATA_ENTRY);
            return true;
        }
        return false;
    }

    // check correct size ship
    static boolean checkShipSize(int sizeShip, int startLine, int finishLine, int startColumn, int finishColumn) {
        if (!(Math.abs(finishLine - startLine) + 1 == sizeShip
                || Math.abs(startColumn - finishColumn) + 1 == sizeShip)) {
            System.out.println(WRONG_SHIP_LENGTH);
            return true;
        }
        return false;
    }

    // check place is empty and the ability to insert a ship
    static boolean checkPlaceIsEmpty(Coordinates[] coordinates, String[][] playerBoard) {
        for (Coordinates c : coordinates) {
            if (playerBoard[c.line][c.column].equals(Board.SHIPS)) {
                System.out.println(OCCUPIED_PLACE);
                return true;
            }
        }
        return false;
    }

    // check place around ship and the ability to insert a ship
    static boolean checkAroundOfShip(Coordinates[] coordinates, String[][] playerBoard) {
        for (Coordinates c : coordinates) {
            int line = c.line;
            int column = c.column;
            for (int i = column - 1; i < column + 2; i++) {
                for (int j = line - 1; j < line + 2; j++) {
                    if (i > -1 && i < 10 && j > -1 && j < 10) {
                        if (playerBoard[j][i].equals(Board.SHIPS)) {
                            System.out.println(PLACE_TO_CLOSE);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public Coordinates[] getCoordinatesShip(int sizeShip, String[][] playerBoard) {
        Coordinates[] coordinates;
        coordinates = new Coordinates[sizeShip];
        char direction = 0;
        boolean check = true;

        do {
            try {
                String coordinate = scanner.nextLine();
                String[] coordinateArray = coordinate.split("\\s+");
                String startLineString = coordinateArray[0].substring(0, 1);
                String finishLineString = coordinateArray[1].substring(0, 1);

                int startColumn = Integer.parseInt(coordinateArray[0].substring(1)) - 1;
                int finishColumn = Integer.parseInt(coordinateArray[1].substring(1)) - 1;
                int startLine = startLineString.charAt(0) - 'A';
                int finishLine = finishLineString.charAt(0) - 'A';

                if (checkCorrectIntroduction(startLine, finishLine, startColumn, finishColumn)) {
                    continue;
                }
                if (checkShipSize(sizeShip, startLine, finishLine, startColumn, finishColumn)) {
                    continue;
                }

                if (startLine < finishLine && startColumn == finishColumn) {
                    direction = 'd';
                } else if (finishLine < startLine && startColumn == finishColumn) {
                    direction = 'u';
                } else if (startColumn < finishColumn && startLine == finishLine) {
                    direction = 'r';
                } else if (finishColumn < startColumn && startLine == finishLine) {
                    direction = 'l';
                }

                for (int i = 0; i < sizeShip; i++) {
                    switch (direction) {
                        case 'd' -> coordinates[i] = new Coordinates(startLine + i, startColumn);
                        case 'u' -> coordinates[i] = new Coordinates(startLine - i, startColumn);
                        case 'r' -> coordinates[i] = new Coordinates(startLine, startColumn + i);
                        case 'l' -> coordinates[i] = new Coordinates(startLine, startColumn - i);
                        default -> {
                        }
                    }
                }
                if (checkPlaceIsEmpty(coordinates, playerBoard)) {
                    continue;
                }
                if (checkAroundOfShip(coordinates, playerBoard)) {
                    continue;
                }
                check = false;
            } catch (Exception e) {
                System.out.println(INCORRECT_DATA_ENTRY);
            }
        } while (check);
        return coordinates;
    }

    public List<Coordinates[]> createPlayerShips(String[][] playerBoard) {
        List<Coordinates[]> shipsCoordinate = new ArrayList<>();
        List<Ship> ships = new ArrayList<>();

        Ship Aircraft = new Ship(5, "Aircraft Carrier");
        Ship Battleship = new Ship(4, "Battleship");
        Ship Submarine = new Ship(3, "Submarine");
        Ship Cruiser = new Ship(3, "Cruiser");
        Ship Destroyer = new Ship(2, "Destroyer");

        ships.add(Aircraft);
        ships.add(Battleship);
        ships.add(Submarine);
        ships.add(Cruiser);
        ships.add(Destroyer);

        for (Ship ship : ships) {
            System.out.println("Enter the coordinates of the " + ship.name + " (" + ship.size + " cells): ");
            Coordinates[] shipCoordinate = getCoordinatesShip(ship.size, playerBoard);
            for (Coordinates c : shipCoordinate) {
                playerBoard[c.line][c.column] = Board.SHIPS;
            }
            shipsCoordinate.add(shipCoordinate);
            Board.showPlayerBoard(playerBoard);
        }
        return shipsCoordinate;
    }

}

