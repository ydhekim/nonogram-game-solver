package io.github.ydhekim;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class Solver {

	Nonogram nonogram = new Nonogram();

	public Solver() {
	}

	public Solver(Nonogram nonogram) {
		this.nonogram = nonogram;
	}

	public void solve() {
		solveCol1(nonogram);
		solveCol2(nonogram);
		solveCol3(nonogram);
		solveCol4(nonogram);
		solveRow1(nonogram);
		solveRow2(nonogram);
		solveRow3(nonogram);
		solveRow4(nonogram);
	}

	public int sumStackElements(Stack<Integer> stack) {
		int total = 0;
		Iterator<Integer> iterator = stack.iterator();
		while (iterator.hasNext()) {
			total += iterator.next();
		}
		return total;
	}

	public HashMap<Integer, Stack<Integer>> createMap(int[][] arr, int blank) {
		HashMap<Integer, Stack<Integer>> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			Stack<Integer> stack = new Stack<>();
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] < blank)
					continue;
				else
					stack.push(arr[i][j]);
			}
			map.put(i, stack);
		}
		return map;
	}

	public void solveCol1(Nonogram nonogram) {
		int[][] cell = nonogram.getCell();
		int[][] col = nonogram.getCol();
		int rowLength = nonogram.getRowLength();

		for (int i = 0; i < col.length; i++) {
			for (int j = 0; j < col[i].length; j++) {
				if (col[i][j] == rowLength) {
					for (int k = 0; k < cell.length; k++) {
						cell[i][k] = 1;
					}
				}
			}
		}
	}

	public void solveCol2(Nonogram nonogram) {
		int[][] cell = nonogram.getCell();
		int[][] col = nonogram.getCol();
		int rowLength = nonogram.getRowLength();
		int sum = 0;
		int tot = 0;

		for (int i = 0; i < col.length; i++) {
			for (int j = 0; j < col[i].length; j++) {
				sum += col[i][j];
				if ((sum == rowLength - (col[i].length - 1)) && col[i].length != 1) {
					int index = i;

					for (int k = 0; k < cell.length; k++)
						cell[index][k] = 1;

					for (int k = 0; k < col[i].length - 1; k++) {
						if (k == 0)
							tot += col[i][k];
						else
							tot += col[i][k] + 1;
						cell[index][tot] = 0;
					}
					tot = 0;
				}
			}
			sum = 0;
		}
	}

	public void solveCol3(Nonogram nonogram) {
		int[][] cell = nonogram.getCell();
		int[][] col = nonogram.getCol();
		int rowLength = nonogram.getRowLength();

		for (int i = 0; i < col.length; i++) {
			for (int j = 0; j < col[i].length; j++) {
				if (col[i][j] >= (rowLength / 2) && col[i].length == 1 && col[i][j] != rowLength) {
					int index = i;
					int sub = rowLength - col[i][j];

					for (int k = sub; k < (rowLength - sub); k++) {
						cell[index][k] = 1;
					}
				}
			}
		}
	}

	public void solveCol4(Nonogram nonogram) {
		int[][] cell = nonogram.getCell();
		int[][] col = nonogram.getCol();
		int[] subColTotalValues = new int[col.length];
		int rowLength = nonogram.getRowLength();
		int sum = 0;

		for (int i = 0; i < col.length; i++) {
			for (int j = 0; j < col[i].length; j++) {
				sum += col[i][j];
			}
			subColTotalValues[i] = sum;
			sum = 0;
		}

		for (int i = 0; i < col.length; i++) {
			int blank = rowLength - (subColTotalValues[i] + col[i].length - 1);
			HashMap<Integer, Stack<Integer>> hmap = createMap(col, blank);
			for (int j = 0; j < col[i].length; j++) {
				if ((col[i][j] > blank) && col[i].length != 1 && blank != 0) {
					Stack<Integer> s = hmap.get(i);
					int stackSum = sumStackElements(s);
					int stackSiz = s.size() - 1;
					int forInit = stackSum + stackSiz - 1;
					int forTerm = s.lastElement() - blank;
					for (int k = forInit; k > (forInit - forTerm); k--) {
						cell[i][k] = 1;
					}
					s.pop();
				}
			}
		}

	}

	public void solveRow1(Nonogram nonogram) {

		int[][] cell = nonogram.getCell();
		int[][] row = nonogram.getRow();
		int colLength = nonogram.getColLength();

		for (int i = 0; i < row.length; i++) {
			for (int j = 0; j < row[i].length; j++) {
				if (row[i][j] == colLength) {
					int index = i;
					for (int k = 0; k < cell.length; k++) {
						cell[k][index] = 1;
					}
				}
			}
		}
	}

	public void solveRow2(Nonogram nonogram) {
		int[][] cell = nonogram.getCell();
		int[][] row = nonogram.getRow();
		int colLength = nonogram.getColLength();
		int sum = 0;
		int tot = 0;

		for (int i = 0; i < row.length; i++) {
			for (int j = 0; j < row[i].length; j++) {
				sum += row[i][j];
				if ((sum == colLength - (row[i].length - 1)) && row[i].length != 1) {
					int index = i;
					for (int k = 0; k < cell.length; k++)
						cell[k][index] = 1;

					for (int l = 0; l < row[i].length - 1; l++) {
						if (l == 0)
							tot += row[i][l];
						else
							tot += row[i][l] + 1;
						cell[tot][index] = 0;
					}
					tot = 0;
				}
			}
			sum = 0;
		}
	}

	public void solveRow3(Nonogram nonogram) {

		int[][] cell = nonogram.getCell();
		int[][] row = nonogram.getRow();
		int colLength = nonogram.getColLength();

		for (int i = 0; i < row.length; i++) {
			for (int j = 0; j < row[i].length; j++) {
				if (row[i][j] >= (colLength / 2) && row[i].length == 1 && row[i][j] != colLength) {
					int index = i;
					int sub = colLength - row[i][j];
					for (int k = sub; k < (cell.length - sub); k++) {
						cell[k][index] = 1;
					}
				}
			}
		}
	}

	public void solveRow4(Nonogram nonogram) {
		int[][] cell = nonogram.getCell();
		int[][] row = nonogram.getRow();
		int[] subRowTotalValues = new int[row.length];
		int colLength = nonogram.getColLength();
		int sum = 0;

		for (int i = 0; i < row.length; i++) {
			for (int j = 0; j < row[i].length; j++) {
				sum += row[i][j];
			}
			subRowTotalValues[i] = sum;
			sum = 0;
		}

		for (int i = 0; i < row.length; i++) {
			int blank = colLength - (subRowTotalValues[i] + row[i].length - 1);
			HashMap<Integer, Stack<Integer>> hmap = createMap(row, blank);
			for (int j = 0; j < row[i].length; j++) {
				if ((row[i][j] > blank) && row[i].length != 1 && blank != 0) {
					Stack<Integer> s = hmap.get(i);
					int stackSum = sumStackElements(s);
					int stackSiz = s.size() - 1;
					int forInit = stackSum + stackSiz - 1;
					int forTerm = s.lastElement() - blank;
					for (int k = forInit; k > (forInit - forTerm); k--) {
						cell[k][i] = 1;
					}
					s.pop();
				}
			}
		}

	}

}