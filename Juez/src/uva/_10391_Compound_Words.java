package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class _10391_Compound_Words {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		HashSet<String> dictionary = new HashSet<String>();
		ArrayList<String> list = new ArrayList<String>(120000);
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			dictionary.add(line);
			list.add(line);
		} while (line != null && line.length() != 0);
		for (int i = 0; i < list.size(); i++) {
			String word = list.get(i);
			for (int j = 1; j < word.length(); j++) {
				if (dictionary.contains(word.substring(0, j))
						&& dictionary.contains(word.substring(j))) {
					out.append(word).append("\n");
					break;
				}
			}
		}
		System.out.print(out);
	}
}
