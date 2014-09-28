package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class _156_Ananagrams {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		ArrayList<String> list = new ArrayList<String>();
		HashMap<String, String> map = new HashMap<String, String>();
		HashMap<String, String> map2 = new HashMap<String, String>();
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			if (line.equals("#"))
				break;
			String[] w = line.split(" ");
			String order = "";
			for (int i = 0; i < w.length; i++)
				if (w[i].length() > 0) {
					order = orderWord(w[i]);
					if (!map.containsKey(order)) {
						map.put(order, w[i]);
						map2.put(order, w[i]);
					} else {
						map2.remove(order);
					}
				}
		} while (line != null && line.length() != 0);
		Iterator it = map2.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			list.add(Objects.toString(e.getValue()));
		}
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++)
			out.append(list.get(i)).append("\n");
		System.out.print(out);
	}

	public static String orderWord(String word) {
		String ans = "";
		char[] arrayLet = word.toLowerCase().trim().toCharArray();
		Arrays.sort(arrayLet);
		for (int i = 0; i < arrayLet.length; i++)
			ans += (arrayLet[i] + "");
		return ans;
	}
}
