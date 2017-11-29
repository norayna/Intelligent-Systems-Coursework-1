//import java.util.HashSet;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
//import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BFS {
	Queue<char[][]> queue;
	//HashSet<char[][]> visited;
	int nodesExpanded;
	HashMap<char[][], Pair<char[][], Character>> relations; //maps state to parent and action to reach child
	char[][] start;
	char[][] goal;
	//Problem problem;

	public BFS(char[][] start, char[][] goal) {
		//this.problem = problem;
		queue = new ConcurrentLinkedQueue<char[][]>();
		relations = new HashMap<char[][], Pair<char[][], Character>>();
		this.nodesExpanded = 0;
		//visited = new HashSet<char[][]>();			//TODO: use the size of this set somewhere
		this.start = start;
		this.goal = goal;
		queue.add(start);
		relations.put(start, new Pair<char[][], Character>(null, null));	//put the parent & preceding move of the start state as null
	}
	public String solve() {

		//while(!queue.isEmpty()) {
		while(true) {
			char[][] parent = queue.poll();		//poll retrieves the head of the queue and removes it
			//System.out.println("The following state was polled from the queue:");
			
			//GridController.visualise(parent);
			
			if(GridController.areSame(parent, goal)) {
				
				System.out.println("Current state:");
				GridController.visualise(parent);
				
				System.out.println("Goal state:");
				GridController.visualise(goal);
				
				System.out.println(nodesExpanded);
				return constructPath(parent); //constructPath should use "relations"
			} else
			
			for(char direction : GridController.getPossibleMoves('x', parent)) {
				//if(!Problem.areSame(relations.get(parent).first, Problem.move('x', direction, parent))) {
					//System.out.println("this is not where we came from!");
					char[][] child = GridController.move('x', direction, parent);		//makes the move, thus creating the child
					
					//System.out.println("The following child state was added to the queue:");
					//GridController.visualise(child);
					
					relations.put(child, new Pair<char[][], Character>(parent, direction));	//TODO: relations 
					queue.add(child);
				//} else System.err.println("this is where we came from");
			}
			nodesExpanded++;
			if(nodesExpanded % 1000000 == 0)System.out.println("Nodes expanded: " + nodesExpanded);
			//visited.add(parent);
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
	return solutionPath;
	}
}

