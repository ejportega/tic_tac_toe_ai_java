package com.ej.tictactoe;

import com.ej.tictactoe.enums.Seed;
import com.ej.tictactoe.enums.State;

import java.util.Arrays;

public class Board {
    private int rows = Constants.ROWS;
    private int columns = Constants.COLUMNS;
    private int totalCells = Constants.TOTAL_CELLS;

    private Cell[][] cells;

    /** Constructor to initialize the game board */
    public Board() {
        init();
    }

    public void init() {
        int pos = this.totalCells;
        cells = new Cell[rows][columns];
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < columns; ++col) {
                cells[row][col] = new Cell(row, col, pos--);
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getTotalCells() {
        return totalCells;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public void printBoard() {
        int pos = this.totalCells;

        System.out.println();
        for (int row = 0; row < this.rows; ++row) {
            System.out.print("\t");
            for (int col = 0; col < this.columns; ++col) {
                Cell cell = this.getCells()[row][col];

                if (!cell.getContent().equals(Seed.NO_SEED)) {
                    System.out.printf(" %s ", cell.getContent().getIcon());
                } else {
                    System.out.printf(" %d ", pos);
                }

                if (col != this.columns - 1) {
                    // print vertical partition
                    System.out.print("|");
                }

                pos--;
            }
            System.out.println("\n\t-----------");
        }
    }

    public void playerMove(Board board, int movePosition, Seed player) {
        Cell cell = this.findCell(board, movePosition);
        cell.setContent(player);
    }

    // Check if there's a winner or draw
    public State getState(Board board, Seed player) {
        Cell[][] cells = board.getCells();
        State state = State.DRAW;

        if (
            // First diagonal
            (isPlayerCell(cells[0][0], player) &&
            isPlayerCell(cells[1][1], player) &&
            isPlayerCell(cells[2][2], player)) ||

            // Second diagonal
            (isPlayerCell(cells[2][0], player) &&
            isPlayerCell(cells[1][1], player) &&
            isPlayerCell(cells[0][2], player)) ||

            // Horizontal first row
            (isPlayerCell(cells[0][0], player) &&
            isPlayerCell(cells[0][1], player) &&
            isPlayerCell(cells[0][2], player)) ||

            // Horizontal second row
            (isPlayerCell(cells[1][0], player) &&
            isPlayerCell(cells[1][1], player) &&
            isPlayerCell(cells[1][2], player)) ||

            // Horizontal first row
            (isPlayerCell(cells[2][0], player) &&
            isPlayerCell(cells[2][1], player) &&
            isPlayerCell(cells[2][2], player)) ||

            // Vertical first column
            (isPlayerCell(cells[0][0], player) &&
            isPlayerCell(cells[1][0], player) &&
            isPlayerCell(cells[2][0], player)) ||

            // Vertical first column
            (isPlayerCell(cells[0][1], player) &&
            isPlayerCell(cells[1][1], player) &&
            isPlayerCell(cells[2][1], player)) ||

            // Vertical first column
            (isPlayerCell(cells[0][2], player) &&
            isPlayerCell(cells[1][2], player) &&
            isPlayerCell(cells[2][2], player)))
        {
            state = player == Seed.CROSS ? State.CROSS_WON : State.NOUGHT_WON;
        } else {
            for (int row = 0; row < rows; ++row) {
                for (int col = 0; col < columns; ++col) {
                    if (cells[row][col].getContent() == Seed.NO_SEED) {
                        state = State.PLAYING;
                        break;
                    }
                }
            }
        }

        return state;
    }

    public boolean validateCell(Board board, int movePosition) {
        for (int row = 0; row < this.rows; ++row) {
            for (int col = 0; col < this.columns; ++col) {
                Cell cell = board.getCells()[row][col];
                if (
                    cell.getPosition() == movePosition &&
                    cell.getContent() != Seed.NO_SEED)
                    return false;
            }
        }

        return true;
    }

    private boolean isPlayerCell(Cell cell, Seed player) {
        return cell.getContent() == player;
    }

    private Cell findCell(Board board, int movePosition) {
        for (int row = 0; row < this.rows; ++row) {
            for (int col = 0; col < this.columns; ++col) {
                Cell cell = board.getCells()[row][col];
                if (cell.getPosition() == movePosition)
                    return cell;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "Board{" +
                "rows=" + rows +
                ", columns=" + columns +
                ", cells=" + Arrays.toString(cells) +
                '}';
    }
}
