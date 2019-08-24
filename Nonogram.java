package io.github.ydhekim;

import java.util.Arrays;

public class Nonogram {

	private int[][] cell = null;
	private int[][] row = null;
	private int[][] col = null;
	private int rowLength = 0;
	private int colLength = 0;

	public Nonogram() {
	}

	public Nonogram(int[][] row, int[][] col, int rowLength, int colLength) {
		this.row = row;
		this.col = col;
		this.rowLength = rowLength;
		this.colLength = colLength;
		this.cell = new int[this.colLength][this.rowLength];
	}

	public void setRowLength(int rowLength) {
		this.rowLength = rowLength;
	}

	public void setColLength(int colLength) {
		this.colLength = colLength;
	}

	public void setRow(int[][] row) {
		this.row = row;
	}

	public void setCol(int[][] col) {
		this.col = col;
	}

	public int getRowLength() {
		return this.rowLength;
	}

	public int getColLength() {
		return this.colLength;
	}

	public int[][] getRow() {
		return row;
	}

	public int[][] getCol() {
		return this.col;
	}

	public int[][] getCell() {
		return this.cell;
	}

	public void printRow() {
		System.out.println("ROW is " + Arrays.deepToString(getRow()) + "\n");
	}

	public void printCol() {
		System.out.println("COL is " + Arrays.deepToString(getCol()) + "\n");
	}

	public void printCell() {
		for (int i = 0; i < this.rowLength; i++) {
			for (int j = 0; j < this.colLength; j++) {
				System.out.print(cell[i][j] + "  ");
			}
			System.out.println("");
		}
	}

}
