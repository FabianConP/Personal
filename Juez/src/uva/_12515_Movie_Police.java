package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class _12515_Movie_Police {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int[] MQ = readInts(line);
			int m = MQ[0], q = MQ[1];
			ArrayList<char[]> lm = new ArrayList<>(m + 1);
			ArrayList<char[]> lq = new ArrayList<>(q + 1);
			for (int i = 0; i < m; i++)
				lm.add(in.readLine().trim().toCharArray());
			for (int i = 0; i < q; i++)
				lq.add(in.readLine().trim().toCharArray());
			char[] cm, cq;
			int minDif = 1 << 25, curDif = 0, dif = 0, index = 0;
			for (int i = 0; i < lq.size(); i++) {
				cq = lq.get(i);
				index = minDif = curDif = 1 << 25;
				for (int j = 0; j < lm.size(); j++) {
					cm = lm.get(j);
					for (int k = 0; k <= cm.length - cq.length; k++) {
						dif = 0;
						for (int h = 0; h < cq.length; h++)
							if (cq[h] != cm[h + k])
								dif++;
						curDif = Math.min(curDif, dif);
					}
					if (curDif < minDif) {
						minDif = curDif;
						index = j + 1;
					}
				}
				out.append(index + "\n");
			}
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
