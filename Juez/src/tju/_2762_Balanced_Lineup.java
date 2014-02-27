package tju;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _2762_Balanced_Lineup {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int[] nq = readInts(line), ab;
			SegTree s = new SegTree(0, nq[0]+1);
			for (int i = 0; i < nq[0]; i++) 	
				s.set(i+1, Integer.parseInt(in.readLine()));
			for (int i = 0; i < nq[1]; i++) {
				ab = readInts(in.readLine());
				out.append((s.getMax(ab[0], ab[1])-s.getMin(ab[0], ab[1]))+"\n");
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

	public static class SegTree {
		long minVal;
		long maxVal;
		long sumVal;
		long start;
		long end;
		SegTree left;
		SegTree right;

		public SegTree(long start, long end) {
			this.start = start;
			this.end = end;
			if (start == end) {
				left = null;
				right = null;
			} else {
				long mid = (start + end) / 2;
				left = new SegTree(start, mid);
				right = new SegTree(mid + 1, end);
			}
		}

		public void set(long pos, long val) {
			if (start == end && start == pos) {
				minVal = maxVal = val;
				sumVal = val;
			} else {
				long mid = (start + end) / 2;
				if (pos <= mid)
					left.set(pos, val);
				else
					right.set(pos, val);
				minVal = Math.min(left.minVal, right.minVal);
				maxVal = Math.max(left.maxVal, right.maxVal);
				sumVal = left.sumVal + right.sumVal;
			}
		}

		public long getMin(long start, long end) {
			if (this.start == start && this.end == end)
				return minVal;
			long mid = (this.start + this.end) / 2;
			if (mid >= start && mid >= end)
				return left.getMin(start, end);
			if (mid < start && mid < end)
				return right.getMin(start, end);
			return Math
					.min(left.getMin(start, mid), right.getMin(mid + 1, end));
		}

		public long getMax(long start, long end) {
			if (this.start == start && this.end == end)
				return maxVal;
			long mid = (this.start + this.end) / 2;
			if (start <= mid && end <= mid)
				return left.getMax(start, end);
			if (start > mid && end > mid)
				return right.getMax(start, end);
			return Math
					.max(left.getMax(start, mid), right.getMax(mid + 1, end));
		}

		public long getSum(long start, long end) {
			if (this.start == start && this.end == end)
				return sumVal;
			long mid = (this.start + this.end) / 2;
			if (start <= mid && end <= mid)
				return left.getSum(start, end);
			if (start > mid && end > mid)
				return right.getSum(start, end);
			return left.getSum(start, mid) + right.getSum(mid + 1, end);
		}
	}
}
