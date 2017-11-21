import java.util.ArrayList;

public class Grid {
	// char[][] grid;
	// Each one of the important tiles is an array of its coordinates x and y.
	private static final int x = 0;
	private static final int y = 1;
	int[] agent;
	int[] a;
	int[] b;
	int[] c;
	ArrayList<int[]> abc; // this is to be used later to loop through a,b,c
	int size;

	public Grid(int size) {
		this.size = size;
		
		// The agent is the tile in the bottom right corner.
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

		// abc is the ArrayList of a, b, c.
		abc = new ArrayList<int[]>();
		abc.add(a);
		abc.add(b);
		abc.add(c);

	}

	/*
	 * public void move(char direction) {
	 * 
	 * }
	 */
	public void moveLeft() {
		
		if (agent[y] != 1) { 	//if the agent isn't in the leftmost column
			agent[y]--;			//move the agent to the left
			for (int[] l : abc) {								// for every tile a,b,c, 
				if (l[x] == agent[x] && l[y] == agent[y]) {		// if it's in the same cell as x,
					l[y]++;										// slide it to the right where x used to be
				}
			}
		}
	}

	public void moveRight() {
		if (agent[y] != size) { 	//if the agent isn't in the rightmost column
			agent[y]++;				//move the agent to the right
			for (int[] l : abc) {								// for every tile a,b,c, 
				if (l[x] == agent[x] && l[y] == agent[y]) {		// if it's in the same cell as x,
					l[x]--;										// slide it to the left where x used to be
				}
			}
		}
	}

	public void moveUp() {
		if (agent[x] != 1) { 	//if the agent isn't in the top row
			agent[x]--;			//move the agent up
			for (int[] l : abc) {								// for every tile a,b,c, 
				if (l[x] == agent[x] && l[y] == agent[y]) {		// if it's in the same cell as x,
					l[x]++;										// slide it down to where x used to be
				}
			}
		}
	}

	public void moveDown() {
		if (agent[x] != size) { 	//if the agent isn't in the bottom row
			agent[x]++;				//move the agent down
			for (int[] l : abc) {								// for every tile a,b,c, 
				if (l[x] == agent[x] && l[y] == agent[y]) {		// if it's in the same cell as x,
					l[x]--;										// slide it up to where x used to be
				}
			}
		}
	}

	public boolean isSolved() {
		if(a[x] == )
	}
}
