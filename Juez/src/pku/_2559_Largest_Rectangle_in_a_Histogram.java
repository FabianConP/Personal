package pku;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _2559_Largest_Rectangle_in_a_Histogram {

	public static long[] v;
	
	public static SegTree st;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		do {
			line = in.readLine();
			if (line == null || line.length() == 0)
				break;
			String[] d = line.trim().split(" ");
			if(d[0].trim().equals("0"))
				break;
			v = new long[d.length];
			v[0] = Long.MAX_VALUE;
			st = new SegTree(1, d.length-1);
			for (int i = 1; i < d.length; i++) {
				v[i] = Long.parseLong(d[i].trim());
				st.set(i, v[i]);
			}
			out.append(st.getMaxArea(1, d.length-1)+"\n");
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}

	public static class SegTree {
		int minIndexVal;
		int start;
		int end;
		SegTree left;
		SegTree right;

		public SegTree(int start, int end) {
			this.start = start;
			this.end = end;
			if (start == end) {
				left = null;
				right = null;
			} else {
				int mid = (start + end) / 2;
				left = new SegTree(start, mid);
				right = new SegTree(mid + 1, end);
			}
		}

		public void set(int pos, long val) {
			if (start == end && start == pos) {
				minIndexVal = pos;
			} else {
				int mid = (start + end) / 2;
				if (pos <= mid)
					left.set(pos, val);
				else
					right.set(pos, val);
				minIndexVal = minIndexVal(left.minIndexVal, right.minIndexVal);
			}
		}

		private int minIndexVal(int minIndexLeft, int minIndexRight){
			if (v[minIndexLeft] <=v[minIndexRight])
				return minIndexLeft;
			return minIndexRight;
		}

		public int getIndexMin(int start, int end) {
			if (this.start == start && this.end == end)
				return minIndexVal;
			int mid = (this.start + this.end) / 2;
			if (mid >= start && mid >= end)
				return left.getIndexMin(start, end);
			if (mid < start && mid < end)
				return right.getIndexMin(start, end);
			return minIndexVal(left.getIndexMin(start, mid), right.getIndexMin(mid + 1, end));
		}
		
		public long getMaxArea(int start, int end) {
			if(end<start)
				return Long.MIN_VALUE;
			else if (start == end)
				return v[start];
			int minIndexVal = getIndexMin(start, end);
			long maxAreaLeft = getMaxArea(start, minIndexVal-1);
			long maxAreaRight = getMaxArea(minIndexVal+1, end);
			long maxAreaSeg = v[minIndexVal]*(end-start+1);
			return Math.max(maxAreaLeft, Math.max(maxAreaRight, maxAreaSeg));
		}
	}
}
