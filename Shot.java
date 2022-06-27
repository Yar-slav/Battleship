package battleship;

import java.util.List;
import java.util.Scanner;

public class Shot {
    Scanner scanner = new Scanner(System.in);

    // check correct introduction
    public boolean checkCorrectIntroduction(int line, int column) {
        if(line >= 0 && line < 10 && column >= 0 && column < 10) {
            return false;
        } else {
            System.out.println("Error! You entered the wrong coordinates! Try again");
            return true;
        }
    }

    // check if they have already shot here
    public boolean checkShotPlayer(Coordinates coordinates, String[][] playerBoardEnemy, String[][] playerBoard) {
        if(playerBoardEnemy[coordinates.line][coordinates.column].equals(Main.MISS) ||
                playerBoardEnemy[coordinates.line][coordinates.column].equals(Main.HIT_SHIP)) {
            return true;
        }
        return false;
    }

    public boolean checkShipIsSankPlayer(List<Coordinates[]> otherPlayerShips, String[][] otherPlayerBoard, String[][] playerBoardEnemy) {
        for (Coordinates[] ship : otherPlayerShips) {
            int partOfDestroyedShip = 0;
            for(Coordinates coordinate : ship) {
                if(otherPlayerBoard[coordinate.line][coordinate.column].equals(Main.HIT_SHIP)) {
                    partOfDestroyedShip++;
                    if(partOfDestroyedShip == ship.length) {
                        otherPlayerShips.remove(ship);
                        if(otherPlayerShips.size() == 0) {
                            Main.playerBoardEnemy(playerBoardEnemy);
                            System.out.println("You sank the last ship. You won. Congratulations!");
                            return false;
                        }else {
                            Main.playerBoardEnemy(playerBoardEnemy);
                            System.out.println("You sank a ship! Specify a new target:");
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    // return coordinates Shot
    public Coordinates shotPlayer(List<Coordinates[]> otherPlayerShips, String[][] playerBoardEnemy, String[][] playerBoard, String[][] otherPlayerBoard) {
        Coordinates coordinatesShot = null;
        int line;
        String lineForInt;
        int column;
        boolean check = true;

        do {
            try {
                String coordinate = scanner.nextLine();
                lineForInt = coordinate.substring(0, 1);
                line = lineForInt.charAt(0) - 'A';
                column = Integer.parseInt(coordinate.substring(1)) - 1;
                coordinatesShot = new Coordinates(line, column);

                if(checkCorrectIntroduction(line, column)) {
                    continue;
                }

                checkShotPlayer(coordinatesShot, playerBoardEnemy, playerBoard);
                if(otherPlayerBoard[coordinatesShot.line][coordinatesShot.column].equals(Main.EMPTY)){ // тут має бути дошка супротивника можлива помилка
                    playerBoardEnemy[coordinatesShot.line][coordinatesShot.column] = Main.MISS;
                    otherPlayerBoard[coordinatesShot.line][coordinatesShot.column] = Main.MISS; // тут має бути дошка супротивника можлива помилка
                    System.out.println("You missed!");
//                    continue;
                }else if (otherPlayerBoard[coordinatesShot.line][coordinatesShot.column].equals(Main.SHIPS)) {
                    playerBoardEnemy[coordinatesShot.line][coordinatesShot.column] = Main.HIT_SHIP;
                    otherPlayerBoard[coordinatesShot.line][coordinatesShot.column] = Main.HIT_SHIP;
                    if (checkShipIsSankPlayer(otherPlayerShips, otherPlayerBoard, playerBoardEnemy)) {
                        System.out.println("You hit a ship!");
                    }
                }
//

            } catch (Exception e) {
                System.out.println("Error! You entered the wrong coordinates! Try again");
            }

            check = false;
        } while (check);

        return coordinatesShot;
    }
}

