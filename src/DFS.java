import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

public class DFS {
	Stack<char[][]> stack;
	int nodesExpanded;
	HashMap<char[][], Pair<char[][], Character>> relations; //maps state to parent and action to reach child
	char[][] start;
	char[][] goal;


	public DFS(char[][] start, char[][] goal) {
		
		stack = new Stack<char[][]>();
		relations = new HashMap<char[][], Pair<char[][], Character>>();
		this.nodesExpanded = 0;
		
		this.start = start;
		this.goal = goal;
		stack.push(start);
		relations.put(start, new Pair<char[][], Character>(null, null));	//put the parent & preceding move of the start state as null
	}

	public String solve() {

		//while(!stack.isEmpty()) {
		while(true) {
			char[][] parent = stack.pop();		//poll retrieves the head of the queue and removes it
			//System.out.println("The following state was popped off the stack:");
			
			//GridController.visualise(parent);
			
			if(GridController.areSame(parent, goal)) {
				
				System.out.println("Current state:");
				GridController.visualise(parent);
				
				System.out.println("Goal state:");
				GridController.visualise(goal);
				
				System.out.println(nodesExpanded);
				return constructPath(parent); //constructPath should use "relations"
			} else {
			
				ArrayList<Character> children = GridController.getPossibleMoves('x', parent);
				Collections.shuffle(children);
				char direction = children.get(0);
				
					char[][] child = GridController.move('x', direction, parent);		//makes the move, thus creating the child
					
					//System.out.println("The following child state was pushed on the stack:");
					//GridController.visualise(child);
					
					relations.put(child, new Pair<char[][], Character>(parent, direction)); 
					stack.push(child);
				
				nodesExpanded++;
				if(nodesExpanded % 1000000 == 0)System.out.println("Nodes expanded: " + nodesExpanded);
				
				}
		}
		//return null;		//to suppress error
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

