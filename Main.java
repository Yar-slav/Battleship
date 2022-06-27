package battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static final String EMPTY = "~", SHIPS = "O", HIT_SHIP = "X", MISS = "M";
    public static final String PLAYER1 = "Player 1", PLAYER2 = "Player 2";
    public static String activePlayer;
    public static final int LINE = 10, COLUMN = 10;
    static String[][] player1Board = new String[LINE][COLUMN];
    static String[][] player2Board = new String[LINE][COLUMN];
    static String[][] player1BoardEnemy = new String[LINE][COLUMN];
    static String[][] player2BoardEnemy = new String[LINE][COLUMN];
    public static List<Coordinates[]> player1Ships = new ArrayList<>();
    public static List<Coordinates[]> player2Ships = new ArrayList<>();


    public static void main(String[] args) {
        fillTablesEmpty();

        System.out.println("Player 1, place your ships on the game field");
        playerBoard(player1Board);
        createPlayerShips(player1Board, player1Ships);
        moveToAnotherPlayer();

        System.out.println("Player 2, place your ships on the game field");
        playerBoard(player2Board);
        createPlayerShips(player2Board, player2Ships);
        moveToAnotherPlayer();

        activePlayer = PLAYER1;

        do {
            if(activePlayer.equals(PLAYER1)) {
                playerBoardEnemy(player1BoardEnemy);
                System.out.println("----------------------");
                playerBoard(player1Board);
                System.out.println(PLAYER1 + ", it's your turn:");
                takeShot(player2Ships, player1BoardEnemy, player1Board, player2Board);
            } else if(activePlayer.equals(PLAYER2)) {
                playerBoardEnemy(player2BoardEnemy);
                System.out.println("----------------------");
                playerBoard(player2Board);
                System.out.println(PLAYER2 + ", it's your turn:");
                takeShot(player1Ships, player2BoardEnemy, player2Board, player1Board);
            }
            moveToAnotherPlayer();
        } while (player1Ships.size() != 0 || player2Ships.size() != 0);
    }

    public static void playerBoard(String[][] playerBoard) {
        char cr = 'A';

        System.out.print("  ");
        for (int i = 1; i <= COLUMN; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int c = 0; c < LINE; c++) { //char
            System.out.print((cr) + " ");
            cr++;
            for (int i = 0; i < COLUMN; i++) {
                System.out.print(playerBoard[c][i] + " ");
            }
            System.out.println();
        }
    }

    public static void playerBoardEnemy(String[][] playerBoardEnemy) {
        char cr = 'A';

        System.out.print("  ");
        for (int i = 1; i <= COLUMN; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int c = 0; c < LINE; c++) {
            System.out.print((cr) + " ");
            cr++;
            for (int i = 0; i < COLUMN; i++) {
                System.out.print(playerBoardEnemy[c][i] + " ");
            }
            System.out.println();
        }
    }

    public static void fillTablesEmpty() {
        for (int i = 0; i < LINE; i++) {
            for (int j = 0; j < COLUMN; j++) {
                player1Board[i][j] = EMPTY;
                player2Board[i][j] = EMPTY;
                player1BoardEnemy[i][j] = EMPTY;
                player2BoardEnemy[i][j] = EMPTY;
            }
        }
    }

    public static void createPlayerShips(String[][] playerBoard, List<Coordinates[]> playerShips) {
        Ship ship = new Ship();

        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells): ");
        Coordinates[] ship5 = ship.getCoordinatesShip(5 , playerBoard);
        ship.addShipToPlayerBoard(ship5, playerBoard, playerShips);
        playerBoard(playerBoard);

        System.out.println("Enter the coordinates of the Battleship (4 cells): ");
        Coordinates[] ship4 = ship.getCoordinatesShip(4, playerBoard);
        ship.addShipToPlayerBoard(ship4, playerBoard, playerShips);
        playerBoard(playerBoard);

        System.out.println("Enter the coordinates of the Submarine (3 cells):): ");
        Coordinates[] ship31 = ship.getCoordinatesShip(3, playerBoard);
        ship.addShipToPlayerBoard(ship31, playerBoard, playerShips);
        playerBoard(playerBoard);

        System.out.println("Enter the coordinates of the Cruiser (3 cells): ");
        Coordinates[] ship32 = ship.getCoordinatesShip(3, playerBoard);
        ship.addShipToPlayerBoard(ship32, playerBoard, playerShips);
        playerBoard(playerBoard);

        System.out.println("Enter the coordinates of the Destroyer (2 cells): ");
        Coordinates[] ship2 = ship.getCoordinatesShip(2, playerBoard);
        ship.addShipToPlayerBoard(ship2, playerBoard, playerShips);
        playerBoard(playerBoard);
    }

    public static void takeShot(List<Coordinates[]> otherPlayerShips, String[][] playerBoardEnemy, String[][] playerBoard, String[][] otherPlayerBoard) {
        Shot shot = new Shot();
        shot.shotPlayer(otherPlayerShips, playerBoardEnemy, playerBoard, otherPlayerBoard);
    }

    public static void moveToAnotherPlayer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Enter and pass the move to another player");
        String enter = scanner.nextLine();
        activePlayer = activePlayer == PLAYER1 ? PLAYER2 : PLAYER1;
    }
}