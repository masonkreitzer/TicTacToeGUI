public class TicTacToe {

    public static final int ROW = 3;
    public static final int COL = 3;
    public static String[][] board = new String[ROW][COL];

    // Initialize or clear the board
    public static void clearBoard() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                board[row][col] = " ";
            }
        }
    }

    // Check if a move is valid
    public static boolean isValidMove(int row, int col) {
        return board[row][col] != null && board[row][col].equals(" ");
    }

    // Check if a player has won
    public static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROW; row++) {
            if (player.equals(board[row][0]) &&
                    player.equals(board[row][1]) &&
                    player.equals(board[row][2])) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int col = 0; col < COL; col++) {
            if (player.equals(board[0][col]) &&
                    player.equals(board[1][col]) &&
                    player.equals(board[2][col])) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        return (player.equals(board[0][0]) &&
                player.equals(board[1][1]) &&
                player.equals(board[2][2])) ||
                (player.equals(board[0][2]) &&
                player.equals(board[1][1]) &&
                player.equals(board[2][0]));
    }

    // Check for tie, even if the board isn't full
    public static boolean isTie() {

        // If there's already a winner, not a tie
        if (isWin("X") || isWin("O")) return false;

        // Check if the board is full — classic tie case
        boolean full = true;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c] == null) {
                    full = false;
                    break;
                }
            }
        }
        if (full) return true;

        // If all 8 lines have both X and O, then no one can ever win.
        boolean xPossible = false;
        boolean oPossible = false;

        String[][] lines = {
                {board[0][0], board[0][1], board[0][2]}, // row 1
                {board[1][0], board[1][1], board[1][2]}, // row 2
                {board[2][0], board[2][1], board[2][2]}, // row 3
                {board[0][0], board[1][0], board[2][0]}, // col 1
                {board[0][1], board[1][1], board[2][1]}, // col 2
                {board[0][2], board[1][2], board[2][2]}, // col 3
                {board[0][0], board[1][1], board[2][2]}, // diag 1
                {board[0][2], board[1][1], board[2][0]}  // diag 2
        };

        for (String[] line : lines) {
            boolean hasX = false, hasO = false;
            for (String cell : line) {
                if ("X".equals(cell)) hasX = true;
                if ("O".equals(cell)) hasO = true;
            }

            if (!hasO) xPossible = true;
            if (!hasX) oPossible = true;
        }

        // If neither player has a potential win line, it's a tie
        return !xPossible && !oPossible;
    }
}