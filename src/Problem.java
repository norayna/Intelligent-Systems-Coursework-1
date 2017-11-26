import java.util.ArrayList;

public class Problem {

	// [row][column]
	// private char[][] start;
	// private char[][] goal;
	// private int size;
	public static final int ROW = 0;
	public static final int COLUMN = 1;

	// public Problem(char[][] start, char[][] goal) {
	// this.start = start;
	// this.goal = goal;
	// this.size = size;
	// grid = new char[size][size];
	// System.out.println("New " + size + "x" + size + " AbandonedGrid
	// created.");
	// }

	public static int check(char x, int coordinate, char[][] grid) { // coordinate
																		// can
																		// be
																		// either
																		// ROW
		// or COLUMN

		for (int i = 0; i < grid.length; i++) { // for each row

			for (int j = 0; j < grid.length; j++) { // for each column (so for
													// each
				// cell)

				if (x == grid[i][j]) { // if x is in the current cell

					int value = 0; // this is the value of the coordinate we're
									// looking for
									// (this is what we will be returning)

					switch (coordinate) {

					case ROW:
						value = i; // if we need the row, assign the current row
									// index to "variable"
						break;

					case COLUMN:
						value = j; // if we need the column, assign the current
									// column index to "variable"
						break;
					}

					return value;
				}
			}
		}
		return 666; // if x hasn't been found, return 666
	}

	public static char[][] insertBlock(char x, int row, int column, char[][] grid) {
		if (grid[row][column] == '\u0000') { // if that grid cell isn't empty...
												// ('\u0000' is the empty char)
			grid[row][column] = x; // put x in that cell
			System.out.println("Block " + x + " inserted in cell (" + row + "," + column + ").");
		} else {
			System.err.println("Cannot place " + x + " in cell (" + row + "," + column + ") - cell is taken by "
					+ grid[row][column]);
		}
		// visualise(grid);
		return grid;

	}

	public static char[][] move(char x, char direction, char[][] grid) {
		int currentRow = check(x, ROW, grid);
		int currentColumn = check(x, COLUMN, grid);
		int newRow = currentRow; // new row & column defaulted to current ones -
									// this will be changed depending on
									// direction
		int newColumn = currentColumn;
		// char[][] newGrid = new char[grid.length][grid.length];
		char[][] newGrid = copyGrid(grid);

		switch (direction) {
		case 'L':
			newColumn = currentColumn - 1;
			break;

		case 'R':
			newColumn = currentColumn + 1;
			break;

		case 'U':
			newRow = currentRow - 1;
			break;

		case 'D':
			newRow = currentRow + 1;
			break;

		default:
			System.err.println("Cannot move " + x + " in the direction " + direction + " - invalid direction");
		}

		if (newColumn < 0) {
			System.err.println("Cannot move " + x + " in the direction " + direction + " - " + x
					+ " is already in the leftmost column.");
		} else if (newColumn > grid.length - 1) { // size-1 is the last column -
													// rows and columns are
													// index-0-based
			System.err.println("Cannot move " + x + " in the direction " + direction + " - " + x
					+ " is already in the rightmost column.");
		} else if (newRow < 0) {
			System.err.println(
					"Cannot move " + x + " in the direction " + direction + " - " + x + " is already in the top row.");
		} else if (newRow > grid.length - 1) {
			System.err.println("Cannot move " + x + " in the direction " + direction + " - " + x
					+ " is already in the bottom row.");
		} else {

			// grid[currentRow][currentColumn] = grid[newRow][newColumn]; //the
			// value from the cell that x is moving to gets put in the cell
			// where x currently is
			newGrid[currentRow][currentColumn] = grid[newRow][newColumn];
			// WHAT IF IT's EMPTY?? WILL IT MOVE '\u0000' AROUND OR NOT??

			newGrid[newRow][newColumn] = x; // x is put into the new cell
			
			System.out.println(x + " moved from (" + currentRow + "," + currentColumn + ") to (" + newRow + ","
					+ newColumn + ").");
					
			 visualise(newGrid);
		}
		return newGrid;

	}

	public static void visualise(char[][] grid) {
		for (int i = 0; i < grid.length; i++) { // for each row
			System.out.println("┌───┐ ┌───┐ ┌───┐ ┌───┐");
			for (int j = 0; j < grid.length; j++) {
				System.out.print("│ ");
				System.out.print(grid[i][j] == '\u0000' ? " " : grid[i][j]); // null
																				// used
																				// to
																				// be
																				// '\u0000'
				System.out.print(" │ ");
			}
			System.out.print("\n");
			System.out.println("└───┘ └───┘ └───┘ └───┘");
		}
		System.out.println();
		System.out.println("________________________________________________________");

		// VISUALISING ANIMATION PAUSE:
		/*
		 * try { Thread.sleep(300); } catch(InterruptedException e){};
		 */
	}

	public static void main(String[] args) {
		int size = 4;

		// Problem problem = new Problem(size);
		char[][] start = new char[size][size];

		char agent = 'x';

		insertBlock(agent, size-1, size-1, start);

		insertBlock('A', size-1, 0, start);
		insertBlock('B', size-1, 1, start);
		insertBlock('C', size-1, 2, start);

		// problem.visualise();
		/*
		move(agent, 'L', start);
		visualise(start);

		move(agent, 'L', start);
		visualise(start);

		move(agent, 'L', start);
		visualise(start);
		
		
		char[][] newGrid = move(agent, 'L', start);
		visualise(newGrid);
		*/
		
		char[][] goal = new char[size][size];

		//insertBlock(agent, size-1, 0, goal); 		// goal shouldn't need the agent
		/*
		insertBlock('A', 1, 1, goal);
		insertBlock('B', 2, 1, goal);
		insertBlock('C', 3, 1, goal);
		*/

		insertBlock('A', size-1, 0, goal);
		insertBlock('B', size-1, 1, goal);
		insertBlock('C', size-1, 3, goal);
		
		BFS bfs = new BFS(start, goal);
		System.out.println(bfs.solve());
		
		//System.out.println(areSame(start, goal));

	}

	public static ArrayList<Character> getPossibleMoves(char x, char[][] grid) {
		ArrayList<Character> possibleMoves = new ArrayList<Character>();
		if (check(x, ROW, grid) != 0)
			possibleMoves.add('U');
		if (check(x, ROW, grid) != grid.length - 1)
			possibleMoves.add('D');
		if (check(x, COLUMN, grid) != 0)
			possibleMoves.add('L');
		if (check(x, COLUMN, grid) != grid.length - 1)
			possibleMoves.add('R');

		return possibleMoves;
	}

	public static Boolean areSame(char[][] grid, char[][] goal) {
		Boolean areSame = true;
		if(grid == null) {
			areSame = false;
		} else {
			for (int i = 0; i < goal.length; i++) {
				for (int j = 0; j < goal.length; j++) {
					if (grid[i][j] != goal[i][j] && grid[i][j] != 'x')		//TODO: rename 'x' to agent or something
						areSame = false;
				}
			}
		}
		System.out.println("Is this the goal: " + areSame);
		return areSame;
	};

	public static char[][] copyGrid(char[][] grid) {
		char[][] newGrid = new char[grid.length][grid.length];

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				newGrid[i][j] = grid[i][j];
			}
		}
		return newGrid;
	}
}

