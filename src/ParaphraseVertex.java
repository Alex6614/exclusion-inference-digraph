
public class ParaphraseVertex {
	
	// This represents the word that the vertex is associated with
	String ID;
	int index;
	int lowlink;

	public ParaphraseVertex(String id){
			if(id == null) {throw new NullPointerException();}
			ID = id;
			index = Integer.MAX_VALUE;
	}
	
	@Override
	public String toString(){
		return ID;
	}

}
