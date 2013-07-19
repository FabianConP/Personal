package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10921_Find_the_Telephone {
	
	public static String convery(String n){
		n = n.replaceAll("A", "2");
		n = n.replaceAll("B", "2");
		n = n.replaceAll("C", "2");
		n = n.replaceAll("D", "3");
		n = n.replaceAll("E", "3");
		n = n.replaceAll("F", "3");
		n = n.replaceAll("G", "4");
		n = n.replaceAll("H", "4");
		n = n.replaceAll("I", "4");
		n = n.replaceAll("J", "5");
		n = n.replaceAll("K", "5");
		n = n.replaceAll("L", "5");
		n = n.replaceAll("M", "6");
		n = n.replaceAll("N", "6");
		n = n.replaceAll("O", "6");
		n = n.replaceAll("P", "7");
		n = n.replaceAll("Q", "7");
		n = n.replaceAll("R", "7");
		n = n.replaceAll("S", "7");
		n = n.replaceAll("T", "8");
		n = n.replaceAll("U", "8");
		n = n.replaceAll("V", "8");
		n = n.replaceAll("W", "9");
		n = n.replaceAll("X", "9");
		n = n.replaceAll("Y", "9");
		n = n.replaceAll("Z", "9");
		return n;
	}
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader buf = new BufferedReader(
				new InputStreamReader(System.in));
		String line = "";
		d: do {
			line = buf.readLine();
			if (line == null || line.length() == 0)
				break d;
			sb.append(convery(line)).append("\n");
		} while (line != null && line.length() != 0);
		System.out.print(sb);
	}
}
