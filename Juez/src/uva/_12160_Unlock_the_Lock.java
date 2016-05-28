package uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _12160_Unlock_the_Lock {

	static class State {
		int v, s;

		public State(int v, int s) {
			this.v = v;
			this.s = s;
		}

	}

	public static void main(String[] args) throws IOException {
		File inputFile = new File("entrada");
		if (inputFile.exists())
			System.setIn(new FileInputStream(inputFile));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line;
		int MOD = 10000;
		int nCase = 1;
		while ((line = in.readLine()) != null) {
			int[] abc = readInts(line);
			int a = abc[0], b = abc[1], c = abc[2];
			if (a == 0 && b == 0 && c == 0)
				break;
			int[] but = readInts(in.readLine());
			boolean[] vis = new boolean[MOD];
			State cur = new State(a, 0);
			Queue<State> q = new LinkedList<>();
			q.add(cur);
			int ans = -1;
			while (!q.isEmpty()) {
				cur = q.poll();
				if (cur.v == b) {
					ans = cur.s;
					break;
				}
				for (int i = 0; i < but.length; i++) {
					if (!vis[(but[i] + cur.v) % MOD]) {
						vis[(but[i] + cur.v) % MOD] = true;
						q.add(new State((but[i] + cur.v) % MOD, cur.s + 1));
					}
				}
			}
			if (ans == -1)
				out.append("Case " + nCase++ + ": Permanently Locked\n");
			else
				out.append("Case " + nCase++ + ": " + ans + "\n");
		}
		System.out.print(out);
	}

	public static int[] readInts(String line) {
		StringTokenizer st = new StringTokenizer(line.trim());
		int a[] = new int[st.countTokens()], index = 0;
		while (st.hasMoreTokens())
			a[index++] = Integer.parseInt(st.nextToken());
		return a;
	}
}
