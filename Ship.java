package battleship;

import java.util.List;
import java.util.Scanner;

public class Ship {
    static Scanner scanner = new Scanner(System.in);

    // check correctly introduction
    public boolean checkCorrectIntroduction(int startLine, int finishLine, int startColumn, int finishColumn) {
        if(startLine >= 0 && startLine < 10 && finishLine >= 0 && finishLine < 10 &&
                startColumn >= 0 && startColumn < 10 && finishColumn >= 0 && finishColumn < 10) {
            return false;
        } else {
            System.out.println("Error! Incorrect data entry. Try again");
            return true;
        }
    }

    // check correct size ship
    boolean checkShipSize (int sizeShip, int startLine, int finishLine, int startColumn, int finishColumn) {
        if(Math.abs(finishLine - startLine) + 1 == sizeShip || Math.abs(startColumn - finishColumn) +1 == sizeShip) {
            return  false;
        } else {
            System.out.println("Error! Wrong length of the Submarine. Try again");
            return true;
        }
    }

    // check place is empty and the ability to insert a ship
    boolean checkPlaceIsEmpty (Coordinates[] coordinates, String[][] playerBoard) {
        for(Coordinates c : coordinates) {
            if(playerBoard[c.line][c.column].equals(Main.SHIPS)) {
                System.out.println("Error! Place is occupied. Try again");
                return true;
            }
        }
        return false;
    }

    // check place around ship and the ability to insert a ship
    boolean checkAroundOfShip(Coordinates[] coordinates, String[][] playerBoard) {
        for(Coordinates c : coordinates) {
            int line = c.line;
            int column = c.column;
            for (int i = column - 1; i < column + 2; i++) {
                for (int j = line - 1; j < line + 2 ; j++) {
                    if(i > -1 && i < 10 && j > -1 && j < 10){
                        if(playerBoard[j][i].equals(Main.SHIPS)) {
                            System.out.println("Error! You placed it too close to another one. Try again");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public Coordinates[] getCoordinatesShip(int sizeShip, String[][] playerBoard){
        Coordinates[] coordinates;
        coordinates = new Coordinates[sizeShip];
        char direction = 0;
        String sLine;
        String fLine;
        int startColumn;
        int startLine;
        int finishColumn;
        int finishLine;
        boolean check = true;

        do {
            try {
                String coordinate = scanner.nextLine();
                String[] coordinateArray = coordinate.split(" ");

                sLine = coordinateArray[0].substring(0, 1);
                startColumn = Integer.parseInt(coordinateArray[0].substring(1))-1;
                fLine = coordinateArray[1].substring(0, 1);
                finishColumn = Integer.parseInt(coordinateArray[1].substring(1))-1;

                startLine = sLine.charAt(0) - 'A';
                finishLine = fLine.charAt(0) - 'A';

                if(checkCorrectIntroduction(startLine, finishLine, startColumn, finishColumn)) {
                    continue;
                }
                if (checkShipSize(sizeShip, startLine, finishLine, startColumn, finishColumn)) {
                    continue;
                }

                if(startLine < finishLine && startColumn == finishColumn) {
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
                        case 'd':
                            coordinates[i] = new Coordinates(startLine + i, startColumn);
                            break;
                        case 'u':
                            coordinates[i] = new Coordinates(startLine - i, startColumn);
                            break;
                        case 'r':
                            coordinates[i] = new Coordinates(startLine, startColumn + i);
                            break;
                        case 'l':
                            coordinates[i] = new Coordinates(startLine, startColumn - i);
                            break;
                    }
                }

                if(checkPlaceIsEmpty(coordinates, playerBoard)) {
                    continue;
                }
                if (checkAroundOfShip(coordinates, playerBoard)) {
                    continue;
                }

                check = false;
            } catch (Exception e) {
                System.out.println("Error!!!! Incorrect data entry. Try again");
            }

        } while (check);

        return coordinates;
    }

    public void addShipToPlayerBoard(Coordinates[] coordinates, String[][] playerBoard, List<Coordinates[]> playerShips) {
        for(Coordinates c : coordinates) {
            playerBoard[c.line][c.column] = Main.SHIPS;
        }
        playerShips.add(coordinates);
    }
}

