package uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class _12195_Jingle_Composing {
	public static void main(String[] args) throws IOException {
		File inputFile = new File("entrada");
		if (inputFile.exists())
			System.setIn(new FileInputStream(inputFile));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line;
		HashMap<Character, Integer> map = new HashMap<>();
		map.put('W', 64);
		map.put('H', 32);
		map.put('Q', 16);
		map.put('E', 8);
		map.put('S', 4);
		map.put('T', 2);
		map.put('X', 1);
		while ((line = in.readLine()) != null) {
			if (line.trim().equals("*"))
				break;
			String[] v = line.trim().split("/");
			int count = 0;
			for (int i = 0; i < v.length; i++) {
				if (v[i].length() > 0) {
					char[] l = v[i].toCharArray();
					int sum = 0;
					for (int j = 0; j < l.length; j++)
						sum += map.get(l[j]);
					if (sum == 64)
						count++;
				}
			}
			out.append(count + "\n");
		}
		System.out.print(out);
	}

}
