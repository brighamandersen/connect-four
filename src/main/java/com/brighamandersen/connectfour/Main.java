package com.brighamandersen.connectfour;

import java.util.Scanner;
import static com.brighamandersen.connectfour.Constants.*;

public class Main {
    private static String changeTurn(String currentPlayer) {
        if (currentPlayer.equals(RED_PLAYER)) {
            return YELLOW_PLAYER;
        }

        return RED_PLAYER;
    }

    public static void main(String[] args) {
        Board board = new Board();
        String currentPlayer = RED_PLAYER;
        Scanner scanner = new Scanner(System.in);
        String winner = null;

        System.out.println("Welcome to Connect 4!\n");

        System.out.println(board);

        while (winner == null) {
            System.out.printf("%s's turn.\n", currentPlayer);

            int selectedColumn;
            while (true) {
                System.out.print("Pick a column (1-7): ");

                if (scanner.hasNextInt()) {
                    selectedColumn = scanner.nextInt();
                    selectedColumn = selectedColumn - 1; // Subtract 1 to convert to 0 index

                    if (selectedColumn >= 0 && selectedColumn < NUM_COLS) {
                        boolean isFull = board.checkIfColumnIsFull(selectedColumn);

                        if (isFull) {
                            System.out.println("Column already full.");
                        } else {
                            break;
                        }
                    } else {
                        System.out.println("Invalid column. Must be between 1 and 7.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next(); // Consume the invalid input to avoid an infinite loop
                }
            }

            board.dropChip(selectedColumn, currentPlayer);

            System.out.println(board);

            currentPlayer = changeTurn(currentPlayer);

            winner = board.checkWinner();
        }

        if (winner.equals("DRAW")) {
            System.out.println("The board is full, so it's a draw!");
        } else {
            System.out.printf("%s connected 4! %s wins! 🎊", winner, winner);
        }
    }
}