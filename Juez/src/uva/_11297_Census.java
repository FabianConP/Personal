package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class _11297_Census {
	
	public static class SegTree{
		int minVal;
		int maxVal;
		int start;
		int end;
		SegTree left;
		SegTree right;
		
		public SegTree(int start, int end){
            this.start = start;
            this.end = end;
			if(start == end){
				left = null;
				right = null;
			}else{
				int mid = (start+end)/2;
				left = new SegTree(start, mid);
				right = new SegTree(mid+1, end);
			}
		}
		
		public void set(int pos, int val){
			if(start == end && start == pos){
				minVal = maxVal = val;
			}else{
				int mid = (start+end)/2;
				if(pos<=mid)
					left.set(pos, val);
				else
					right.set(pos, val);
				minVal = Math.min(left.minVal, right.minVal);
				maxVal = Math.max(left.maxVal, right.maxVal);
			}
		}
		
		public int getMin(int start, int end){
			if(this.start == start && this.end== end) return minVal;
			int mid = (this.start+this.end)/2;
			if(mid>=start && mid>=end) return left.getMin(start, end);
			if(mid<start && mid<end) return right.getMin(start, end);
			return Math.min(left.getMin(start, mid), right.getMin(mid+1, end));
		}
		
		public int getMax(int start,int end){
            if(this.start == start && this.end == end) return maxVal;
            int mid = (this.start + this.end)/2;
            if(start <= mid && end <= mid) return left.getMax(start,end);
            if(start > mid && end > mid) return right.getMax(start,end);
            return Math.max(left.getMax(start,mid),right.getMax(mid+1,end));
        }
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		do {
			line = in.readLine();
			if (line == null || line.length() == 0)
				break;
			int[] nn = retInts(line);
			SegTree[] st = new SegTree[nn[0]];
			String[] d;
			for (int i = 0; i < nn[0]; i++) {
				d = in.readLine().trim().split(" ");
				st[i] = new SegTree(1, nn[1]);
				for (int j = 0; j < d.length; j++) 
					st[i].set(j+1, Integer.parseInt(d[j]));
			}
			int q = Integer.parseInt(in.readLine());
			for (int i = 0; i < q; i++) {
				d = in.readLine().trim().split(" ");
				if(d[0].equals("q")){
					int x0 = Integer.parseInt(d[1]);
					int y0 = Integer.parseInt(d[2]);
					int x1 = Integer.parseInt(d[3]);
					int y1 = Integer.parseInt(d[4]);
					long max = Long.MIN_VALUE;
					long min = Long.MAX_VALUE;
					for (int j = x0; j <= x1; j++) {
						max = Math.max(max, st[j-1].getMax(y0, y1));
						min = Math.min(min, st[j-1].getMin(y0, y1));
					}
					out.append(max+" "+min+"\n");
				}else{
					int x = Integer.parseInt(d[1]);
					int y = Integer.parseInt(d[2]);
					int v = Integer.parseInt(d[3]);
					st[x-1].set(y, v);
				}
			}
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
}
