//import java.util.HashSet;
//import java.util.ArrayList;
//import java.util.Collections;
import java.util.HashMap;
//import java.util.Queue;
import java.util.Stack;
//import java.util.List;
//import java.util.concurrent.ConcurrentLinkedQueue;

public class IDS {
	//Stack<Pair<char[][], Integer>> stack;
	
	int nodesExpanded;
	HashMap<char[][], Pair<char[][], Character>> relations; //maps state to parent and action to reach child
	char[][] start;
	char[][] goal;

	public IDS(char[][] start, char[][] goal) {
		
		//stack = new Stack<Pair<char[][], Integer>>();
		relations = new HashMap<char[][], Pair<char[][], Character>>();
		this.nodesExpanded = 0;
		
		this.start = start;
		this.goal = goal;
		//stack.push(new Pair(start, 0));
		relations.put(start, new Pair<char[][], Character>(null, null));	//put the parent & preceding move of the start state as null
	}
	public String solve() {
		
		int limit = 1;
		while(true) {
			System.out.println("Limit: " + limit);
			
			String solution = DLS(limit);
			if(solution != null) {
				//System.out.println(solution);
				return solution;
			} else
			//System.out.println(DLS(limit));
			limit++;
			
			
			
			//limit++;
			//System.out.println("Limit: " + limit);
			if(nodesExpanded % 1000000 == 0) System.out.println("Nodes expanded: " + nodesExpanded);
			
			
			try{
				Thread.sleep(1000);
			} catch(InterruptedException e) {};
			
		}
		//return null;		//to suppress error
	}

	/*
	public String DLS(char[][] node, int depth) {
		if(depth == 0 && Problem.areSame(node, goal)) {
			return constructPath(node);
		} else {
			for(char direction : Problem.getPossibleMoves('x', node)) {
				char[][] child = Problem.move('x', direction, node);
				relations.put(child, new Pair(node, direction));
				stack.push(child);
				DLS(Problem.move('x', direction, node), depth-1);
			}
		}
		return null;
	}
	*/
	
	public String DLS(int limit) {
		Stack<Pair<char[][], Integer>> stack = new Stack<Pair<char[][], Integer>>();
		stack.push(new Pair(start, 0));
		
		while(!stack.isEmpty()) {
			
			//System.out.println("stack is not empty");
			
			Pair<char[][], Integer> statePair = stack.pop();
			
			//System.out.println("popped a state off the stack");
			
			char[][] state = statePair.first;
			//Problem.visualise(state);
			int depth = statePair.second;
			
			if(Problem.areSame(state, goal)) {
				System.out.println(nodesExpanded);
				return constructPath(state);
			} else if (depth < limit) {
				
				
				//System.out.println("depth " + depth + " is under limit " + limit);
				
				for (char direction : Problem.getPossibleMoves('x', state)) {
					
					//System.out.println("pushed a child onto the stack");
					
					char[][] child = Problem.move('x', direction, state);
					stack.push(new Pair(child, depth + 1));
					
					
					
					
					relations.put(child, new Pair(state, direction));
				}
				nodesExpanded++;
				
			} else {
				
				//System.out.println("depth " + depth + " is >= limit " + limit + ", putting the state back oops");
				
				//stack.push(statePair);	//push the state back because it's too deep
				//break;
			}
		}
		return null;	//if the while loop yields no solution, return nulls
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

