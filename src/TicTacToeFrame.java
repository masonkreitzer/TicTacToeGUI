import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame {

    private final TicTacToeTile[][] tiles;
    private String currentPlayer = "X";
    private int moveCount = 0;
    private final int ROWS = 3, COLS = 3;

    public TicTacToeFrame() {
        TicTacToe.clearBoard();

        setTitle("Tic Tac Toe");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(ROWS, COLS));
        tiles = new TicTacToeTile[ROWS][COLS];

        TileListener listener = new TileListener();

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                tiles[row][col] = new TicTacToeTile(row, col);
                tiles[row][col].setFont(new Font("Arial", Font.BOLD, 60));
                tiles[row][col].addActionListener(listener);
                boardPanel.add(tiles[row][col]);
            }
        }

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));

        add(boardPanel, BorderLayout.CENTER);
        add(quitButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class TileListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            TicTacToeTile tile = (TicTacToeTile) e.getSource();
            int row = tile.getRow();
            int col = tile.getCol();

            if (!tile.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Illegal Move! Try again.");
                return;
            }

            tile.setText(currentPlayer);
            TicTacToe.board[row][col] = currentPlayer;
            moveCount++;

            if (moveCount >= 5 && TicTacToe.isWin(currentPlayer)) {
                JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins!");
                playAgain();
                return;
            }

            if (moveCount >= 7 && TicTacToe.isTie()) {
                JOptionPane.showMessageDialog(null, "It's a tie!");
                playAgain();
                return;
            }

            currentPlayer = currentPlayer.equals("X") ? "O" : "X";
        }
    }

    private void playAgain() {
        int option = JOptionPane.showConfirmDialog(null, "Play again?", "Tic Tac Toe",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            resetBoard();
        } else {
            System.exit(0);
        }
    }

    private void resetBoard() {
        TicTacToe.clearBoard();
        moveCount = 0;
        currentPlayer = "X";
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                tiles[row][col].setText("");
            }
        }
    }
}
