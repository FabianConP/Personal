package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class _12532_Interval_Product {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		do {
			line = in.readLine();
			if (line == null || line.length() == 0)
				break;
			int[] nq = retInts(line);
			String[] data = in.readLine().trim().split(" ");
			SegTree st = new SegTree(1, nq[0]);
			for (int i = 0; i < nq[0]; i++){ 
				int val = Integer.parseInt(data[i].trim());
				st.set(i+1, (val==0)?0:val<0?-1:1);
			}for (int i = 0; i < nq[1]; i++) {
				data = in.readLine().trim().split(" ");
				if(data[0].equals("C")){
					int pos =Integer.parseInt(data[1].trim());
					int val = Integer.parseInt(data[2].trim());
					st.set(pos, (val==0)?0:val<0?-1:1);
				}else{
					int start =Integer.parseInt(data[1].trim());
					int end = Integer.parseInt(data[2].trim());
					int ans = st.getMul(start, end);
					out.append(ans==0?"0":ans<0?"-":"+");					
				}
			}
			out.append("\n");
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}
	
	public static int[] retInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i].trim());
		return a;
	}
	
	public static class SegTree {
		int mulVal;
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

		public void set(int pos, int val) {
			if (start == end && start == pos) {
				mulVal = val;
			} else {
				int mid = (start + end) / 2;
				if (pos <= mid)
					left.set(pos, val);
				else
					right.set(pos, val);
				mulVal = left.mulVal*right.mulVal;
			}
		}

		public int getMul(int start, int end) {
			if (this.start == start && this.end == end)
				return mulVal;
			int mid = (this.start + this.end) / 2;
			if (mid >= start && mid >= end)
				return left.getMul(start, end);
			if (mid < start && mid < end)
				return right.getMul(start, end);
			return left.getMul(start, mid)* right.getMul(mid + 1, end);
		}
	}
}
