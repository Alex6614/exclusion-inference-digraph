import static org.junit.Assert.*;

import org.junit.Test;


public class ParaphraseDigraphTest {
	
	@Test
	public void addOneVertex() {
		ParaphraseDigraph test = new ParaphraseDigraph();
		test.addVertex("Hi!");
		assertTrue(test.containsVertex("Hi!"));
	}
	
	@Test
	public void addVerticesViaLink(){
		ParaphraseDigraph test = new ParaphraseDigraph();
		test.addLink("Hi!", "Bye!");
		assertTrue(test.containsVertex("Hi!") && test.containsVertex("Bye!"));
	}
	
	@Test
	public void testNeighbours(){
		ParaphraseDigraph test = new ParaphraseDigraph();
		test.addLink("Hi!", "Bye!");
		assertTrue(test.neighbours(test.getVertex("Hi!")).contains(test.getVertex("Bye!")));
	}
	
	@Test
	public void testVertexSet(){
		ParaphraseDigraph test = new ParaphraseDigraph();
		test.addLink("Hi!", "Bye!");
		assertTrue(test.vertexSet().contains(test.getVertex("Hi!")) && test.vertexSet().contains(test.getVertex("Bye!")));
	}

}
