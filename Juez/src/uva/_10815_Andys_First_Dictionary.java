package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class _10815_Andys_First_Dictionary {
	public static HashMap<String, Integer> map;
	public static ArrayList<String> list;
	public static int pos;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		map = new HashMap<String, Integer>();
		list = new ArrayList<String>();
		pos = 0;
		d: do {
			line = br.readLine();
			if (line == null)
				break d;
			returnWord(line);
		} while (line != null);
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).length() > 0)
				out.append(list.get(i)).append("\n");
		System.out.print(out);
	}

	public static void returnWord(String word) {
		String current = "";
		for (int i = 0; i < word.length(); i++) {
			if (Character.isLetter(word.charAt(i)))
				current += (Character.toLowerCase(word.charAt(i)));
			else {
				if (current.length() > 0 && !map.containsKey(current)) {
					map.put(current, pos++);
					list.add(current);
				}
				current = "";
			}
		}
		if (current.length() > 0 && !map.containsKey(current)) {
			map.put(current, pos++);
			list.add(current);
		}
	}
}
