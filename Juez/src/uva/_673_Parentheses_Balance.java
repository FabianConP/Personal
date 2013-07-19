package uva;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class  _673_Parentheses_Balance {

	public static boolean check(String line) {
		Stack<String> pila = new Stack<String>();
		for (int j = 0; j < line.length(); j++) {
			if (line.charAt(j) == '(' || line.charAt(j) == '[')
				pila.push(line.charAt(j) + "");
			else if (pila.isEmpty()
					|| (line.charAt(j) == ')' && !pila.peek().equals("("))
					|| (line.charAt(j) == ']' && !pila.peek().equals("[")))
				return false;
			else
				pila.pop();
		}
		return true && pila.size()==0;
	}

	public static void main(String[] args) throws IOException {
	//	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedReader br = new BufferedReader(new FileReader("input.in"));
		StringBuilder sb = new StringBuilder();
		String line = "";
		d: do {
			line = br.readLine();
			if (line == null)
				break;
			int n = Integer.parseInt(line);
			for (int i = 0; i < n; i++) {
				line = br.readLine();
				if (line.length() > 0)
					sb.append((check(line) ? "Yes" : "No")).append("\n");
				else
					sb.append("No\n");
			}
		} while (line != null);
		System.out.print(sb);
	}

}
