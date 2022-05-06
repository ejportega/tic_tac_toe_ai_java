package com.ej.tictactoe;

import com.ej.tictactoe.enums.Seed;
import com.ej.tictactoe.enums.State;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {
    private Board board;
    private State currentState;
    private Seed currentPlayer;

    public TicTacToe() {
    }

    // Start the game
    public void init() {
        Scanner sc = new Scanner(System.in);

        this.board = new Board();
        this.currentState = State.PLAYING;
        this.currentPlayer = Seed.CROSS;
        this.board.printBoard();

        System.out.println("\nGame Start! May the best Player win!");
        System.out.println("Player 1 - X");
        System.out.println("Player 2 - O");

        this.runGame(this.board);

        sc.close();
    }

    public void runGame(Board board) {
        Scanner sc = new Scanner(System.in);

        while (this.currentState == State.PLAYING) {
            System.out.println("\nPlayer " + this.currentPlayer.getIcon() + " turn. Please enter a move: ");
            int move = this.validatePlayerMove(sc, board);
            board.playerMove(board, move, this.currentPlayer);
            board.printBoard();
            this.currentState = board.getState(board, this.currentPlayer);

            if (this.currentState == State.DRAW) {
                System.out.println("\nThe game was DRAW. Please press anything to play again.");
                this.resetGame(sc);
            }
            else if (this.currentState == State.CROSS_WON || this.currentState == State.NOUGHT_WON) {
                System.out.println("\nThe winner is " + this.currentPlayer + "! Please press anything to play again.");
                this.resetGame(sc);
            }
            else {
                this.currentPlayer = this.currentPlayer == Seed.CROSS ? Seed.NOUGHT : Seed.CROSS;
            }
        }

        sc.close();
    }

    private void resetGame(Scanner sc) {
        sc.next();
        String s = sc.nextLine();
        System.out.println(s);
        this.init();
    }

    private int validatePlayerMove(Scanner sc, Board board) {
        int move = 0;

        do {
            try {
                move = sc.nextInt();

                if (move <= 0 || move > this.board.getTotalCells() || !board.validateCell(board, move)) {
                    throw new InputMismatchException("Invalid input! Please try again.");
                }

                return move;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please try again.");
                sc.nextLine();
            }
        } while (true);
    }
}
