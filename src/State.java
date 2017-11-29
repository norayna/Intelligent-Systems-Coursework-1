import java.util.ArrayList;

public class State implements Comparable{
	public char[][] grid;
	public char[][] goal;
	public int depth;
	//public int estDistance;
	
	public State(char[][] grid, int depth, char[][] goal) {
		this.grid = grid;
		this.depth = depth;
		this.goal = goal;
	}
	
	public int getEstCost() {
		int estCost = depth + getManhattan(goal);
		return estCost;
	}
	
	public int getManhattan(char[][] goal) {
		
		ArrayList<Character> tiles = new ArrayList<Character>();
		tiles.add('A');
		tiles.add('B');
		tiles.add('C');
		
		int manhattan = 0;
		
		for(char tile : tiles) {
			manhattan += Math.abs(GridController.check(tile, GridController.ROW, grid) - GridController.check(tile, GridController.ROW, goal)) + Math.abs(GridController.check(tile, GridController.COLUMN, grid) - GridController.check(tile, GridController.COLUMN, goal));
		}
		return manhattan;
	}

	@Override
	public int compareTo(Object state) {
		return this.getEstCost() - ((State) state).getEstCost();
	}
	
}