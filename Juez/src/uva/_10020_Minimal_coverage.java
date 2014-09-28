package uva;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class _10020_Minimal_coverage {

	public static int m;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		StringBuilder out = new StringBuilder();
		int times = in.nextInt();
		for (int i = 0; i < times; i++) {
			if (i != 0)
				out.append("\n");
			m = in.nextInt();
			PriorityQueue<SegmentB> segs = new PriorityQueue<SegmentB>();
			while (true) {
				int[] temp = new int[2];
				temp[0] = in.nextInt();
				temp[1] = in.nextInt();
				if (temp[0] == 0 && temp[1] == 0)
					break;
				segs.add(new SegmentB(temp[0], temp[1]));
			}
			if (segs.isEmpty())
				out.append("0\n");
			else {
				ArrayList<SegmentB> used = new ArrayList<SegmentB>(segs.size());
				SegmentB best, cur;
				int start = 0;
				while (start < m) {
					best = null;
					while (!segs.isEmpty() && (segs.peek()).s <= start) {
						cur = segs.remove();
						if (best == null || cur.e > best.e)
							best = cur;
					}
					if (best == null)
						break;
					else {
						start = best.e;
						used.add(best);
					}
				}
				if (start < m)
					out.append("0\n");
				else {
					out.append(used.size() + "\n");
					for (int j = 0; j < used.size(); j++)
						out.append(used.get(j) + "\n");
				}
			}

		}
		System.out.print(out);
	}
}
// Thanks to f0rth3r3c0rd

class SegmentB implements Comparable<SegmentB> {
	int s, e;

	public SegmentB(int s, int e) {
		this.s = s;
		this.e = e;
	}

	@Override
	public String toString() {
		return "" + s + " " + e + "";
	}

	@Override
	public int compareTo(SegmentB o) {
		if (this.s == o.s)
			return -this.e + o.e;
		return this.s - o.s;
	}
}