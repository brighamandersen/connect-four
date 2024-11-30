package com.brighamandersen.connectfour;

import static com.brighamandersen.connectfour.Constants.*;


public class Board {
    String[][] board = new String[NUM_ROWS][NUM_COLS];

    public Board() {
        initializeBoard();
    }

    private void initializeBoard() {
        for (int r = 0; r < NUM_ROWS; r++) {
            for (int c = 0; c < NUM_COLS; c++) {
                board[r][c] = NO_PLAYER;
            }
        }
    }

    public Boolean checkIfColumnIsFull(int column) {
        if (board[NUM_ROWS - 1][column] == NO_PLAYER) {
            return false;
        }

        return true;
    }

    public void dropChip(int column, String currentPlayer) {
        int row = 0; // Start on bottom row

        while (row < NUM_ROWS) {
            if (board[row][column] == NO_PLAYER) {
                board[row][column] = currentPlayer;
                return;
            }

            row++;
        }
    }

    private boolean isFull() {
        for (int c = 0; c < NUM_COLS; c++) {
            if (board[NUM_ROWS - 1][c] == NO_PLAYER) {
                return false;
            }
        }

        return true;
    }

    public String checkWinner() {
        if (isFull()) {
            return "DRAW";
        }

        for (int r = 0; r < NUM_ROWS; r++) {
            for (int c = 0; c < NUM_COLS; c++) {
                String player = board[r][c];

                if (!player.equals(NO_PLAYER)) {
                    // Check horizontal (right)
                    if (c + 3 < NUM_COLS && board[r][c] == board[r][c + 1] && board[r][c] == board[r][c + 2] && board[r][c] == board[r][c + 3]) {
                        return player;
                    }
                    // Check vertical (down)
                    if (r + 3 < NUM_ROWS && board[r][c] == board[r + 1][c] && board[r][c] == board[r + 2][c] && board[r][c] == board[r + 3][c]) {
                        return player;
                    }
                    // Check diagonal (down-right direction)
                    if (r + 3 < NUM_ROWS && c + 3 < NUM_COLS && board[r][c] == board[r + 1][c + 1] && board[r][c] == board[r + 2][c + 2] && board[r][c] == board[r + 3][c + 3]) {
                        return player;
                    }
                    // Check diagonal (up-right direction)
                    if (r - 3 >= 0 && c + 3 < NUM_COLS && board[r][c] == board[r - 1][c + 1] && board[r][c] == board[r - 2][c + 2] && board[r][c] == board[r - 3][c + 3]) {
                        return player;
                    }
                }
            }
        }

        return null;
    }

    /**
     * Renders them from bottom left to top right.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Print columns numbers starting from 1
        for (int c = 1; c < NUM_COLS + 1; c++) {
            sb.append(String.format("%d\t", c));
        }
        sb.append("\n");

        for (int r = NUM_ROWS - 1; r >= 0; r--) {
            for (int c = 0; c < NUM_COLS; c++) {
                sb.append(String.format("%s\t", board[r][c]));
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}