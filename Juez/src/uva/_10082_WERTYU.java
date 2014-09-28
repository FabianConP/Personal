package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class _10082_WERTYU {

	public static HashMap<Character, Character> map;

	public static void fillMap(){
		map = new HashMap<Character, Character>(50);
		map.put('1','`');
		map.put('2','1');
		map.put('3','2');
		map.put('4','3');
		map.put('5','4');
		map.put('6','5');
		map.put('7','6');
		map.put('8','7');
		map.put('9','8');
		map.put('0','9');
		map.put('-','0');
		map.put('=','-');
		map.put('W','Q');
		map.put('E','W');
		map.put('R','E');
		map.put('T','R');
		map.put('Y','T');
		map.put('U','Y');
		map.put('I','U');
		map.put('O','I');
		map.put('P','O');
		map.put('[','P');
		map.put(']','[');
		map.put('\\',']');
		map.put('S','A');
		map.put('D','S');
		map.put('F','D');
		map.put('G','F');
		map.put('H','G');
		map.put('J','H');
		map.put('K','J');
		map.put('L','K');
		map.put(';','L');
		map.put('\'',';');
		map.put('X','Z');
		map.put('C','X');
		map.put('V','C');
		map.put('B','V');
		map.put('N','B');
		map.put('M','N');
		map.put(',','M');
		map.put('.',',');
		map.put('/','.');
		map.put(' ', ' ');
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		fillMap();
		while ((line = in.readLine()) != null && line.length() != 0) {
			char[] sentence = line.toCharArray();
			for (int i = 0; i < sentence.length; i++) 
				out.append(map.get(sentence[i]));
			out.append("\n");
		}
		System.out.print(out);
	}
}
