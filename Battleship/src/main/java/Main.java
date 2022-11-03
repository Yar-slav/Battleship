import java.util.ArrayList;
import java.util.List;

class Main {
    public static final String PLAYER1 = "Player 1";
    public static final String PLAYER2 = "Player 2";
    public static String activePlayer = PLAYER1;
    public static List<Coordinates[]> player1Ships = new ArrayList<>();
    public static List<Coordinates[]> player2Ships = new ArrayList<>();
    static Board board = new Board();
    static Shot shot = new Shot();
    static Ship ship = new Ship();


    public static void main(String[] args) {
        activePlayer = putShipsToBoard();
        do {
            if(activePlayer.equals(PLAYER1)) {
                Board.showPlayerBoard(board.player1BoardEnemy);
                System.out.println("----------------------");
                Board.showPlayerBoard(board.player1Board);
                System.out.println(PLAYER1 + ", it's your turn:");
                System.out.println(shot.shotPlayer(player2Ships, board.player1BoardEnemy, board.player2Board));
            } else {
                Board.showPlayerBoard(board.player2BoardEnemy);
                System.out.println("----------------------");
                Board.showPlayerBoard(board.player2Board);
                System.out.println(PLAYER2 + ", it's your turn:");
                System.out.println(shot.shotPlayer(player1Ships, board.player2BoardEnemy, board.player1Board));
            }
            if (player1Ships.size() != 0 && player2Ships.size() != 0) {
                activePlayer = moveToAnotherPlayer();
            }
        } while (player1Ships.size() != 0 && player2Ships.size() != 0);
    }

    public static String putShipsToBoard() {
        board.fillTablesEmptyValues();

        System.out.println("Player 1, place your ships on the game field");
        Board.showPlayerBoard(board.player1Board);
        player1Ships = ship.createPlayerShips(board.player1Board);
        activePlayer = moveToAnotherPlayer();

        System.out.println("Player 2, place your ships on the game field");
        Board.showPlayerBoard(board.player2Board);
        player2Ships = ship.createPlayerShips(board.player2Board);
        activePlayer = moveToAnotherPlayer();

        return PLAYER1;
    }

    public static String moveToAnotherPlayer() {
        System.out.println("Press Enter and pass the move to another player");
        ship.scanner.nextLine();
        return activePlayer.equals(PLAYER1) ? PLAYER2 : PLAYER1;
    }
}