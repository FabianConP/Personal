package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class _10131_Is_Bigger_Smarter {

	public static ArrayList<Elephant> elephants;
	public static String str;

	public static int LIS() {
		int lis[] = new int[elephants.size()], cur = 0;
		String s[] = new String[lis.length];
		Arrays.fill(lis, 1);
		Elephant curI, curJ;
		for (int i = 0; i < lis.length; i++, cur = 0, str = "") {
			curI = elephants.get(i);
			s[i] = curI.id + "\n";
			for (int j = 0; j < i; j++) {
				curJ = elephants.get(j);
				if (curI.check(curJ) && lis[j] > cur) {
					cur = lis[j];
					str = s[j];
				}
			}
			lis[i] += cur;
			s[i] = str + s[i];
		}
		int max = 0;
		for (int i = 0; i < lis.length; i++)
			if (max < lis[i]) {
				max = lis[i];
				str = s[i];
			}
		return max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		elephants = new ArrayList<Elephant>(1000);
		int v[], id = 1;
		while ((line = in.readLine()) != null && line.length() != 0) {
			v = readInts(line);
			elephants.add(new Elephant(id++, v[0], v[1]));
		}
		Collections.sort(elephants);
		out.append(LIS() + "\n" + str);
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

class Elephant implements Comparable<Elephant> {
	int id, w, iq;

	public Elephant(int id, int w, int iq) {
		this.id = id;
		this.w = w;
		this.iq = iq;
	}

	@Override
	public int compareTo(Elephant o) {
		if (this.w == o.w)
			return o.iq - this.iq;
		return this.w - o.w;
	}

	public boolean check(Elephant o) {
		return this.w > o.w && this.iq < o.iq;
	}

}
