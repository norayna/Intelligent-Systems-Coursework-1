import java.util.ArrayList;

public class Grid {
	//char[][] grid;
	// Each one of the important tiles is an array of its coordinates x and y.
	private static final int x = 0;
	private static final int y = 1;
	int[] agent;
	int[] a;
	int[] b;
	int[] c;
	ArrayList<int[]> abc; // this is to be used later to loop through a,b,c
	
	
	public Grid(int size) {
		//The agent is the tile in the bottom right corner.
		agent = new int[2];
		agent[x] = size;
		agent[y] = size;
		
		// a, b, c are the first three tiles of the last row.
		a = new int[2];
		a[x] = size;
		a[y] = 1;
		
		b = new int[2];
		b[x] = size;
		b[y] = 2;
		
		c = new int[2];
		c[x] = size;
		c[y] = 3;
		
		//abc is the ArrayList of a, b, c.
		abc = new ArrayList<int[]>();
		abc.add(a);
		abc.add(b);
		abc.add(c);
		
	}
/*
	public void move(char direction) {

	}
*/
	public void moveLeft() {
		//if(!(grid[1][1]=='x'))
		
		agent[1]--;
		for(int[] l : abc) {
			if(l[x] == agent[x] && l[y] == agent[y]) {
				l[x]++;
			}
		}
	}

	public void moveRight() {

	}

	public void moveUp() {

	}

	public void moveDown() {

	}

	public void isSolved() {

	}
}
