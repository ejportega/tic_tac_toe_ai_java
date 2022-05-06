package com.ej.tictactoe;

import com.ej.tictactoe.enums.Seed;

public class Cell {
    private Seed content;
    private int row;
    private int column;
    private int position;

    // init
    public Cell(int row, int column, int position) {
        this.row = row;
        this.column = column;
        this.position = position;
        this.content = Seed.NO_SEED;
    }

    public Seed getContent() {
        return content;
    }

    public void setContent(Seed content) {
        this.content = content;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "content=" + content +
                ", row=" + row +
                ", column=" + column +
                ", position=" + position +
                '}';
    }
}
