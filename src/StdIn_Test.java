

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class StdIn_Test {

	public static void main(String args[]) throws FileNotFoundException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		IOParser parser = new IOParser();
		try {
			String line;
			while((line = reader.readLine()) != null){
				parser.add(line);
			}
			parser.runExclusionDetector();
		} catch (IOException e) {
		   e.printStackTrace();
		} 
	}

}
