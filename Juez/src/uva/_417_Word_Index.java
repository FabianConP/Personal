package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class _417_Word_Index {

	public static HashMap<String, Integer> map = new HashMap<>();

	static void solve() {
		Queue<String> q = new LinkedList<String>();
		int cont = 1;
		for (int i = 'a'; i <= 'z'; i++) 
			q.add((char) i+"");
		while (!q.isEmpty()) {
			String c = q.poll();
			map.put(c, cont++);
			if (c.length() < 5) {
				int start = (int) c.charAt(c.length() - 1) + 1;
				for (int i = start; i <= 'z'; i++)
					q.add(c + (char) i);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		solve();
		while ((line = in.readLine()) != null && line.length() != 0) {
			char[] w = line.trim().toCharArray();
			boolean valid = true;
			for (int i = 1; i < w.length; i++) {
				if (w[i] - 'a' <= w[i - 1] - 'a') {
					valid = false;
					break;
				}
			}
			int ans = valid ? map.get(line) : 0;
			out.append(ans + "\n");
		}
		System.out.print(out);
	}
}
