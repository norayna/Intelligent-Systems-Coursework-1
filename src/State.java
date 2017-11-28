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
			manhattan += Math.abs(Problem.check(tile, Problem.ROW, grid) - Problem.check(tile, Problem.ROW, goal)) + Math.abs(Problem.check(tile, Problem.COLUMN, grid) - Problem.check(tile, Problem.COLUMN, goal));
		}
		return manhattan;
	}

	@Override
	public int compareTo(Object state) {
		// TODO Auto-generated method stub
		return this.getEstCost() - ((State) state).getEstCost();
	}
	
}