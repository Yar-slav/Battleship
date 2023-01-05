public class Board {
    public static final String EMPTY = "~", SHIPS = "O", HIT_SHIP = "X", MISS = "M";
    public static final int LINE = 10, COLUMN = 10;
    String[][] player1Board = new String[LINE][COLUMN];
    String[][] player2Board = new String[LINE][COLUMN];
    String[][] player1BoardEnemy = new String[LINE][COLUMN];
    String[][] player2BoardEnemy = new String[LINE][COLUMN];

    public static void showPlayerBoard(String[][] playerBoard) {
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
                System.out.print(playerBoard[c][i] + " ");
            }
            System.out.println();
        }
    }

    public void fillTablesEmptyValues() {
        for (int i = 0; i < LINE; i++) {
            for (int j = 0; j < COLUMN; j++) {
                player1Board[i][j] = EMPTY;
                player2Board[i][j] = EMPTY;
                player1BoardEnemy[i][j] = EMPTY;
                player2BoardEnemy[i][j] = EMPTY;
            }
        }
    }
}
