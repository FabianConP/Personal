package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

public class _410_Station_Balance {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int times = 1;
		while ((line = in.readLine()) != null && line.length() != 0) {
			if (times != 1)
				out.append("\n");
			int[] cs = retInts(line);
			int[] temp = retInts(in.readLine());
			Item[] s = new Item[cs[0] * 2];
			double imb = 0, prom = 0;
			for (int i = 0; i < temp.length; i++) {
				s[i] = new Item(i, temp[i]);
				prom += temp[i];
			}
			prom /= cs[0];
			for (int i = 0; i < cs[0] * 2 - cs[1]; i++)
				s[i+cs[1]] = new Item(Integer.MAX_VALUE, 0);
			Arrays.sort(s);
			ArrayList<Item>[] cam = new ArrayList[cs[0]];
			for (int i = 0; i < cs[0]; i++)
				cam[i] = new ArrayList<Item>(2);
			Item itemA, itemB;
			for (int i = 0, end = cs[0] * 2 - 1; i < cs[0]; i++, end--) {
				itemA = s[i];
				itemB = s[end];
				cam[i].add(itemA);
				cam[i].add(itemB);
				imb += Math.abs(itemA.w + itemB.w - prom);
			}
			for (int i = 0; i < cam.length; i++)
				Collections.sort(cam[i], Item.CompByIndex);
			out.append("Set #" + times++ + "\n");
			for (int i = 0; i < cam.length; i++) {
				out.append(" " + i + ":");
				itemA = cam[i].get(0);
				if (itemA.w != 0)
					out.append(" ");
				out.append(itemA);
				itemB = cam[i].get(1);
				if (itemB.w != 0)
					out.append(" ");
				out.append(itemB);
				out.append("\n");
			}
			out.append("IMBALANCE = " + String.format(Locale.US, "%.5f\n", imb));
		}
		System.out.println(out);
	}

	public static int[] retInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i].trim());
		return a;
	}
}

class Item implements Comparable<Item> {
	int i, w;

	public Item(int i, int m) {
		this.i = i;
		this.w = m;
	}

	@Override
	public int compareTo(Item arg0) {
		return this.w - arg0.w;
	}

	public static Comparator<Item> CompByIndex = new Comparator<Item>() {
		@Override
		public int compare(Item o1, Item o2) {
			return o1.i - o2.i;
		}
	};

	@Override
	public String toString() {
		return (w == 0) ? "" : w + "";
	}

}
