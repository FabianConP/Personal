package structures;

/**
 * Segment Tree
 * 
 * Get O(log n).
 * 
 * Set O(long n).
 * 
 */
public class SegmentTree {

	/**
	 * Applications:
	 * 
	 * Get min, max or sum values in a range from a to b, where 0 <= a,b < n in
	 * O(log n)
	 */

	int minVal;
	int maxVal;
	int sumVal;
	int start;
	int end;
	SegmentTree left;
	SegmentTree right;

	public SegmentTree(int end) {
		this(0, end);
	}

	public SegmentTree(int start, int end) {
		this.start = start;
		this.end = end;
		if (start == end) {
			left = null;
			right = null;
		} else {
			int mid = (start + end) / 2;
			left = new SegmentTree(start, mid);
			right = new SegmentTree(mid + 1, end);
		}
	}

	public void set(int pos, int val) {
		if (start == end && start == pos) {
			minVal = maxVal = val;
			sumVal = val;
		} else {
			int mid = (start + end) / 2;
			if (pos <= mid)
				left.set(pos, val);
			else
				right.set(pos, val);
			minVal = Math.min(left.minVal, right.minVal);
			maxVal = Math.max(left.maxVal, right.maxVal);
			sumVal = left.sumVal + right.sumVal;
		}
	}

	public int getMin(int start, int end) {
		if (this.start == start && this.end == end)
			return minVal;
		int mid = (this.start + this.end) / 2;
		if (mid >= start && mid >= end)
			return left.getMin(start, end);
		if (mid < start && mid < end)
			return right.getMin(start, end);
		return Math.min(left.getMin(start, mid), right.getMin(mid + 1, end));
	}

	public int getMax(int start, int end) {
		if (this.start == start && this.end == end)
			return maxVal;
		int mid = (this.start + this.end) / 2;
		if (start <= mid && end <= mid)
			return left.getMax(start, end);
		if (start > mid && end > mid)
			return right.getMax(start, end);
		return Math.max(left.getMax(start, mid), right.getMax(mid + 1, end));
	}

	public int getSum(int start, int end) {
		if (this.start == start && this.end == end)
			return sumVal;
		int mid = (this.start + this.end) / 2;
		if (start <= mid && end <= mid)
			return left.getSum(start, end);
		if (start > mid && end > mid)
			return right.getSum(start, end);
		return left.getSum(start, mid) + right.getSum(mid + 1, end);
	}

	public static void main(String[] args) {
		int[] values = { 3, 6, 2, 7, 8, 12 };
		SegmentTree s = new SegmentTree(values.length);
		for (int i = 0; i < values.length; i++)
			s.set(i, values[i]);
		System.out.println(s.getSum(0, 3));
		System.out.println(s.getMax(4, 5));
		System.out.println(s.getMin(1, 1));
	}
}
