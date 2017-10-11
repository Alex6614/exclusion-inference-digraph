import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class ParaphraseDigraph {
	
	HashMap<String, HashSet<ParaphraseVertex>> outlinks;
	HashMap<String, HashSet<ParaphraseVertex>> inlinks;
	HashMap<String, HashSet<ParaphraseVertex>> neighbours;
	HashMap<String, ParaphraseVertex> stringToVertex;
	HashSet<ParaphraseVertex> vertices;

	// Constructor for the Digraph
	
	public ParaphraseDigraph() {
		outlinks = new HashMap<String, HashSet<ParaphraseVertex>>();
		inlinks = new HashMap<String, HashSet<ParaphraseVertex>>();
		vertices = new HashSet<ParaphraseVertex>();
		neighbours = new HashMap<String, HashSet<ParaphraseVertex>>();
		stringToVertex = new HashMap<String, ParaphraseVertex>();
	}
	
	// Adds a vertex to the digraph, if it isn't already in it
	// Updates hashset vertices, hashmap outlinks, inlinks, and neighbours
	
	public void addVertex(String ID) {
		if (!vertices.contains(stringToVertex.get(ID))) {
			ParaphraseVertex vertex = new ParaphraseVertex(ID);
			vertices.add(vertex);
			stringToVertex.put(ID, vertex);
			outlinks.put(ID, new HashSet<ParaphraseVertex>());
			inlinks.put(ID, new HashSet<ParaphraseVertex>());
			neighbours.put(ID, new HashSet<ParaphraseVertex>());
		}
	}
	
	// Checks if an ID is already associated with a vertex
	
	public boolean containsVertex(String ID){
		ParaphraseVertex vertex = getVertex(ID);
		if(vertices.contains(vertex)){
			return true;
		} else {
			return false;
		}
	}
	
	public ParaphraseVertex getVertex(String ID){
		return stringToVertex.get(ID);
	}
	
	public String getID(ParaphraseVertex vertex){
		return vertex.ID;
	}
	
	public void addLink(String from, String to){
		if(!vertices.contains(stringToVertex.get(from))){
			addVertex(from);
		}
		
		if(!vertices.contains(stringToVertex.get(to))){
			addVertex(to);
		}
		
		ParaphraseVertex sourceVertex = stringToVertex.get(from);
		ParaphraseVertex toVertex = stringToVertex.get(to);
		
		// Update outlinks
		if(!outlinks.get(from).contains(toVertex)){
			outlinks.get(from).add(toVertex);
		}
		
		// Update inlinks
		if (!inlinks.get(to).contains(sourceVertex)) {
			inlinks.get(to).add(sourceVertex);
		}

		// Update source's neighbours
		if (!neighbours.get(from).contains(toVertex)) {
			neighbours.get(from).add(toVertex);
		}

		// Update destination's neighbours
		if (!neighbours.get(to).contains(sourceVertex)) {
			neighbours.get(to).add(sourceVertex);
		}
	}
	
	public Set<ParaphraseVertex> neighbours(ParaphraseVertex vertex){
		if(vertex == null || !vertices.contains(vertex)){
			throw new IllegalArgumentException();
		}
		String ID = vertex.ID;
		return outlinks.get(ID);
	}
	
	public Set<ParaphraseVertex> vertexSet(){
		return vertices;
	}
	
	public Set<ParaphraseVertex> inNeighbours(ParaphraseVertex vertex){
		if(vertex == null || !vertices.contains(vertex)){
			throw new IllegalArgumentException();
		}
		String ID = vertex.ID;
		return inlinks.get(ID);
	}
	
	public Set<ParaphraseVertex> outNeighbours(ParaphraseVertex vertex){
		if(vertex == null || !vertices.contains(vertex)){
			throw new IllegalArgumentException();
		}
		String ID = vertex.ID;
		return outlinks.get(ID);
	}
	

}
