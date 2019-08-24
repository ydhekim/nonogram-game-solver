package io.github.ydhekim;

public class Test {
	public static void main(String[] args) {

		int[][] row = { { 4 }, { 6 }, { 2, 4 }, { 6, 2 }, { 6, 1, 2 }, { 6, 1, 4 }, { 1, 4, 1, 7 }, { 7, 12 },
				{ 4, 15 }, { 20 }, { 19 }, { 2, 15 }, { 4, 2, 7 }, { 6, 1 }, { 6, 1 }, { 2, 3, 1 }, { 3, 2, 2 }, { 7 },
				{ 6 }, { 3 } };
		int rowLength = row.length;
		int[][] col = { { 6 }, { 12 }, { 9, 3, 5 }, { 13, 2 }, { 7, 3, 6 }, { 2, 13, 2 }, { 20 }, { 4, 4, 4 },
				{ 3, 6, 3 }, { 3, 6, 3 }, { 14 }, { 6 }, { 6 }, { 7 }, { 7 }, { 6 }, { 7 }, { 7 }, { 7 }, { 6 } };
		int colLength = col.length;

		Nonogram nonogram = new Nonogram(row, col, rowLength, colLength);
		Solver solver = new Solver(nonogram);
		nonogram.printRow();
		nonogram.printCol();

		solver.solve();

		nonogram.printCell();
	}
}
