//import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.List;
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
	public List<Character> solve() {

		while(!queue.isEmpty()) {

			char[][] parent = queue.poll();		//poll retrieves the head of the queue and removes it
			if(Problem.areSame(parent,goal)) {
				return constructPath(parent); //constructPath should use "relations"
			} else
			
			for(char move : Problem.getPossibleMoves('x', parent)) {
				if(!Problem.areSame(relations.get(parent).first, Problem.move('x', move, parent))) {
					System.out.println("this is not where we came from!");
					char[][] child = Problem.move('x', move, parent);		//makes the move, thus creating the child
				
					relations.put(child, new Pair<char[][], Character>(parent, move));	//TODO: relations 
					queue.add(child);
				} else System.err.println("this is where we came from");
			}
			nodesExpanded++;
			System.out.println("Nodes expanded: " + nodesExpanded);
			//visited.add(parent);
		}
		return null;		//to suppress error
	}

	public List<Character> constructPath(char[][] state) {
		List<Character> solutionPath = new ArrayList<Character>();
		char[][]currentState = state;
		while(true) {
			if(relations.get(currentState).first != null) {				//if the parent is not null
				solutionPath.add(0, relations.get(currentState).second); //add at index 0
				currentState = relations.get(currentState).first;
			} else break;
		}
	return solutionPath;
	}
}

