import java.util.HashMap;
import java.util.PriorityQueue;

public class Astar {
	int nodesExpanded;
	HashMap<char[][], Pair<char[][], Character>> relations; // maps state to (parent and action to reach child)
	PriorityQueue<State> availableStates;
	char[][] start;
	char[][] goal;
	
	public Astar(char[][] start, char[][] goal) {
		this.start = start;
		this.goal = goal;
		this.nodesExpanded = 0;
		relations = new HashMap<char[][], Pair<char[][], Character>>();
		relations.put(start, new Pair(null, null));
		availableStates = new PriorityQueue<State>();
		availableStates.add(new State(start, 0, goal));	//state(grid, depth)
	}
	
	public String solve() {

		while(true) {
			State currentState = availableStates.poll();
			//System.out.println("The following state was polled from the priority queue:");
			//GridController.visualise(currentState.grid);
			
			if(GridController.areSame(currentState.grid, goal)) {
				System.out.println();
				System.out.println("A*");
				
				System.out.println(nodesExpanded);
				return constructPath(currentState.grid);
			}
			else {
				for(char direction : GridController.getPossibleMoves('x', currentState.grid)) {
					char[][] child = GridController.move('x', direction, currentState.grid);
					
					//System.out.println("The following child state was added to the priority queue:");
					//GridController.visualise(child);
					
					availableStates.add(new State(child, currentState.depth+1, goal));
					relations.put(child, new Pair(currentState.grid, direction));
					
				}
				nodesExpanded++;
				if(nodesExpanded % 500000 == 0) System.out.println(nodesExpanded);
			}
		}
	}
	
	public String constructPath(char[][] state) {
		String solutionPath = new String();
		char[][]currentState = state;
		while(true) {
			if(relations.get(currentState).first != null) {				//if the parent is not null
				solutionPath = relations.get(currentState).second + solutionPath; //add at index 0
				currentState = relations.get(currentState).first;
			} else break;
		}
		System.out.println("Constructing solution path...");
		System.out.println("Length of solution path: " + solutionPath.length());
	return solutionPath;
	}
	

}
