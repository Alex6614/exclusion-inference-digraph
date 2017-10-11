import java.util.HashSet;


public class IOParser {
	
	public ParaphraseDigraph digraph;
	int i = 1;
	
	HashSet<ExclusionPair> exclusionPairs;

	public IOParser() {
		digraph = new ParaphraseDigraph();
		exclusionPairs = new HashSet<ExclusionPair>();
	}

	public void add(String line) {
		if(line != null){
	    	String[] splitted = line.split("\\|");
	    	// Pattern matching entailment
	    	switch (splitted[3].trim()){
	    		case "ForwardEntailment":
//	    			System.out.println("This is a Forward Entailment!");
	    			digraph.addLink(splitted[1].trim(), splitted[2].trim());
	    			break;
	    		case "ReverseEntailment":
//	    			System.out.println("This is a Reverse Entailment!");
	    			digraph.addLink(splitted[2].trim(), splitted[1].trim());
	    			break;
	    		case "Equivalence":
//	    			System.out.println("This is an Equivalence!");
	    			digraph.addLink(splitted[2].trim(), splitted[1].trim());
	    			digraph.addLink(splitted[1].trim(), splitted[2].trim());
	    			break;
	    			
	    		//New Code Below
	    		case "Exclusion":
//	    			System.out.println("This is an Exclusion Relation!");
	    			digraph.addVertex(splitted[1].trim());
	    			digraph.addVertex(splitted[2].trim());
	    			exclusionPairs.add(new ExclusionPair(splitted[1].trim(), splitted[2].trim()));
//	    			System.out.println("Added New Exclusion Pair!: "+ splitted[1].trim() + "and" + splitted[2].trim());
	    			break;
	    		//New Code Above
	    	}
	    	System.out.println("Processing entailment number: " + i);
	    	i++;
		}
	}
	
	public class ExclusionPair {
		
		String StringOne = "";
		String StringTwo = "";
		
		public ExclusionPair(String a, String b){
			StringOne = a;
			StringTwo = b;
		}
	}
	
	public void runExclusionDetector(){
		for(ExclusionPair e: exclusionPairs){
			@SuppressWarnings("unused")
			ExclusionImpl exclusion = new ExclusionImpl(digraph, e.StringOne, e.StringTwo);
		}
	}
}
