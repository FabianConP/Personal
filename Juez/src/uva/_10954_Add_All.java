package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class _10954_Add_All {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int size = Integer.parseInt(line.trim());
			if (size == 0)
				break;
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>(size);
			int[] v = readInts(in.readLine());
			for (int i = 0; i < v.length; i++)
				pq.add(v[i]);
			int ans = 0, a = 0, b = 0;
			while (pq.size() >= 2) {
				a = pq.poll();
				b = pq.poll();
				ans += a + b;
				pq.add(a + b);
			}
			out.append(ans + "\n");
		}
		System.out.print(out);
	}

	public static int[] readInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i].trim());
		return a;
	}
}
