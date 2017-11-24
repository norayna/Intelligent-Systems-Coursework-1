
public class Grid2 {

	// [row][column]
	private char[][] grid;
	private int size;
	public static final int ROW = 0;
	public static final int COLUMN = 1;

	public Grid2(int size) {
		this.size = size;
		grid = new char[size][size];
		System.out.println("New " + size + "x" + size + " grid created.");
	}

	public int check(char x, int coordinate) { // coordinate can be either ROW
												// or COLUMN

		for (int i = 0; i < size; i++) { // for each row

			for (int j = 0; j < size; j++) { // for each column (so for each
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

	public void insertBlock(char x, int row, int column) {
		if (grid[row][column] == '\u0000') { // if that grid cell isn't empty...
												// ('\u0000' is the empty char)
			grid[row][column] = x; // put x in that cell
			System.out.println("Block " + x + " inserted in cell (" + row + "," + column + ").");
		} else {
			System.err.println("Cannot place " + x + " in cell (" + row + "," + column + ") - cell is taken by " + grid[row][column]);
		}
		visualise();
	}

	public void move(char x, char direction) {
		int currentRow = check(x, ROW);
		int currentColumn = check(x, COLUMN);
		int newRow = currentRow;				// new row & column defaulted to current ones - this will be changed depending on direction
		int newColumn = currentColumn;
		
		switch (direction) {
			case 'L': newColumn = currentColumn - 1;
			break;
			
			case 'R': newColumn = currentColumn + 1;
			break;
			
			case 'U': newRow = currentRow - 1;
			break;
			
			case 'D': newRow = currentRow + 1;
			break;
			
			default: System.err.println("Cannot move " + x + " in the direction " + direction + " - invalid direction");
		}
		
		if(newColumn < 0) {
			System.err.println("Cannot move " + x + " in the direction " + direction + " - " + x + " is already in the leftmost column.");
		}
		else if(newColumn > size-1) {		//size-1 is the last column - rows and columns are index-0-based
			System.err.println("Cannot move " + x + " in the direction " + direction + " - " + x + " is already in the rightmost column.");
		}
		else if(newRow < 0) {
			System.err.println("Cannot move " + x + " in the direction " + direction + " - " + x + " is already in the top row.");
		}
		else if(newRow > size-1) {
			System.err.println("Cannot move " + x + " in the direction " + direction + " - " + x + " is already in the bottom row.");
		}
		else {
			grid[currentRow][currentColumn] = grid[newRow][newColumn];	//the value from the cell that x is moving to gets put in the cell where x currently is
			
			//WHAT IF IT's EMPTY?? WILL IT MOVE '\u0000' AROUND OR NOT??
			
			grid[newRow][newColumn] = x;		// x is put into the new cell
			System.out.println(x + " moved from (" + currentRow + "," + currentColumn + ") to (" + newRow + "," + newColumn + ").");
		}
		visualise();
		
	}
	
	public void visualise() {
		for(int i = 0; i < size; i++) {		// for each row
			System.out.println("┌───┐ ┌───┐ ┌───┐ ┌───┐");
			for (int j = 0; j < size; j++) {
				System.out.print("│ ");
				System.out.print(grid[i][j] == '\u0000' ? " " : grid[i][j]); // null used to be '\u0000'
				System.out.print(" │ ");
			}
			System.out.print("\n");
			System.out.println("└───┘ └───┘ └───┘ └───┘");
		}
		System.out.println();
		System.out.println("________________________________________________________");
	}
	
	public static void main(String[] args) {
		int size = 4;
		
		Grid2 myGrid = new Grid2(size);
		
		char agent = 'X';
		
		myGrid.insertBlock(agent, size-1, size-1);
		
		myGrid.insertBlock('A', size-1, 0);
		myGrid.insertBlock('B', size-1, 1);
		myGrid.insertBlock('C', size-1, 2);
		
		//myGrid.visualise();
		
		myGrid.move(agent, 'L');
		myGrid.move(agent, 'L');
		myGrid.move(agent, 'L');
		myGrid.move(agent, 'L');
		
	}
	
			
	
}
