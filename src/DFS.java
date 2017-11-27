//import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
//import java.util.Queue;
import java.util.Stack;
//import java.util.List;
//import java.util.concurrent.ConcurrentLinkedQueue;

public class DFS {
	Stack<char[][]> stack;
	//HashSet<char[][]> visited;
	int nodesExpanded;
	HashMap<char[][], Pair<char[][], Character>> relations; //maps state to parent and action to reach child
	char[][] start;
	char[][] goal;
	//Problem problem;

	public DFS(char[][] start, char[][] goal) {
		//this.problem = problem;
		stack = new Stack<char[][]>();
		relations = new HashMap<char[][], Pair<char[][], Character>>();
		this.nodesExpanded = 0;
		//visited = new HashSet<char[][]>();			//TODO: use the size of this set somewhere
		this.start = start;
		this.goal = goal;
		stack.push(start);
		relations.put(start, new Pair<char[][], Character>(null, null));	//put the parent & preceding move of the start state as null
	}
	public String solve() {

		while(!stack.isEmpty()) {

			char[][] parent = stack.pop();		//poll retrieves the head of the queue and removes it
			//System.out.println("The following state was polled from the queue:");
			
			//Problem.visualise(parent);
			
			if(Problem.areSame(parent, goal)) {
				
				System.out.println("Current state:");
				Problem.visualise(parent);
				
				System.out.println("Goal state:");
				Problem.visualise(goal);
				
				System.out.println(nodesExpanded);
				return constructPath(parent); //constructPath should use "relations"
			} else {
			
				ArrayList<Character> children = Problem.getPossibleMoves('x', parent);
				Collections.shuffle(children);
				char direction = children.get(0);
				//for(char direction : children) {
					//if(!Problem.areSame(relations.get(parent).first, Problem.move('x', direction, parent))) {
						//System.out.println("this is not where we came from!");
					char[][] child = Problem.move('x', direction, parent);		//makes the move, thus creating the child
					
					//Problem.visualise(Problem.move('x', direction, parent));
					
					relations.put(child, new Pair<char[][], Character>(parent, direction));	//TODO: relations 
					stack.push(child);
				//} else System.err.println("this is where we came from");
				//}
				nodesExpanded++;
				if(nodesExpanded % 1000000 == 0)System.out.println("Nodes expanded: " + nodesExpanded);
				//visited.add(parent);
				}
		}
		return null;		//to suppress error
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

