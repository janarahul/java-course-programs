import java.util.*;
import java.io.*;

//Throws compile time Exception

class Exc {

	public static void main(String args[]) throws IOException {
		FileReader fr = new FileReader("file.txt");
		BufferedReader buf = new BufferedReader(fr);
		buf.readLine();

		buf.close();
		fr.close();




	}


}
