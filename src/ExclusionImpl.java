import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;


public class ExclusionImpl {
	
	ParaphraseDigraph digraph;
	HashSet<String> stringOneChildren;
	
	// HashMap for the purpose of keeping track of parent, for printing purposes
	// Key is the child, Value is the parent, followed up to stringOne
	
	HashMap<String, String> parent_stringOne;
	
	// HashMap for the purpose of keeping track of parent, for printing purposes
	// Key is the child, Value is the parent, followed up to stringTwo
	
	HashMap<String, String> parent_stringTwo;
	
	String stringOne;
	String stringTwo;
	
	private Stack<String> stack;
	private HashMap<String, Boolean> marked;

	public ExclusionImpl(ParaphraseDigraph digraph, String stringOne,
			String stringTwo) {
		// Initialize the hashmaps
		parent_stringOne= new HashMap<String, String>();
		
		parent_stringTwo= new HashMap<String, String>();
		
		// Strings for printing purposes
		this.stringOne = stringOne;
		this.stringTwo = stringTwo;
		
		// Initialize digraph
		this.digraph = digraph;
		
		// Initialize HashSet for stringOne's children
		stringOneChildren = new HashSet<String>();
		
		// Initialize data structures for first DFS
		stack = new Stack<String>();
		marked = new HashMap<String, Boolean>();
		
		// Perform the DFS on the first children for the HashSet
		DFS(stringOne, 0);
		
		// Initialize data structures for second DFS
		stack = new Stack<String>();
		marked = new HashMap<String, Boolean>();
		
		// Perform the DFS on the second children and 
		// while searching through the HashSet
		DFS(stringTwo, 1);
	}

	
	// Performs DFS. If the second parameter is 0, write into HashSet, 
	// if 1, search through HashSet
	private void DFS(String startingString, int i) {
		for(ParaphraseVertex v: digraph.vertices){
			marked.put(digraph.getID(v), false);
		}
		stack.push(startingString);
		while(!stack.isEmpty()){
			String next = stack.pop();
			if(!marked.get(next)){
				marked.put(next, true);
//				System.out.println(startingString + "'s child: " + next);
				// If i = 0, add things to stringOne's children.
				// If i = 1, search through those children to see if there's a hit
				if(i == 0){
					stringOneChildren.add(next);
				} else {
					if(stringOneChildren.contains(next)){
						String up_the_tree = next;
						System.out.println(stringOne + " != " + startingString);
						System.out.println(next);
						// Printing how target relates to string_one
						System.out.print(up_the_tree);
						while(!up_the_tree.trim().equals(stringOne)){
							up_the_tree = parent_stringOne.get(up_the_tree);
							System.out.print(" > " + up_the_tree);
//							System.out.println(up_the_tree + " | " + stringOne);
						}
						System.out.println();
						up_the_tree = next;
						System.out.print(up_the_tree);
						while(!up_the_tree.trim().equals(stringTwo.trim())){
							up_the_tree = parent_stringTwo.get(up_the_tree);
							System.out.print(" > " + up_the_tree);
						}
						System.out.println();
					}
				}
				for(ParaphraseVertex x: digraph.inNeighbours(digraph.getVertex(next))){
					stack.push(digraph.getID(x));
					if(i == 0){
						if(!parent_stringOne.containsKey(digraph.getID(x))){
							parent_stringOne.put(digraph.getID(x), next);
						}
					} else {
						if(!parent_stringTwo.containsKey(digraph.getID(x))){
							parent_stringTwo.put(digraph.getID(x), next);
						}
					}
//					System.out.println("Looking at" + next + "'s child: " + digraph.getID(x));
				}
			}
		}
	}

}
