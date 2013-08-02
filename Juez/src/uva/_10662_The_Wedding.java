package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10662_The_Wedding {

	public static int[][] tr;
	public static int[][] rh;
	public static int[][] ht;
	public static long min;
	public static String sol;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		do {
			line = in.readLine();
			if (line == null || line.length() == 0)
				break;
			int[] trh = retInts(line);
			tr = new int[trh[0]][trh[1]];
			rh = new int[trh[1]][trh[2]];
			ht = new int[trh[2]][trh[0]];
			for (int i = 0; i < trh[0]; i++)
				tr[i] = retInts(in.readLine());
			for (int i = 0; i < trh[1]; i++)
				rh[i] = retInts(in.readLine());
			for (int i = 0; i < trh[2]; i++)
				ht[i] = retInts(in.readLine());
			min = Integer.MAX_VALUE;
			sol = "";
			searchTR();
			if(sol.length()==0)
			out.append("Don't get married!\n");
			else
			out.append(sol+"\n");
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}

	public static void searchTR() {
		long cur = 0;
		for (int i = 0; i < tr.length; i++) {
			cur = tr[i][0];
			for (int j = 1; j < tr[i].length; j++) 
				if(tr[i][j]==0)
					 searchRH(i, j-1, tr[i][0], i+"");
		}
	}

	public static void searchRH(int T, int R, long cur, String path) {
		cur+=rh[R][0];
		for (int i = 1; i < rh[R].length; i++) {
			if(rh[R][i]==0)
				searchHT(T, i-1, cur, path+" "+R);
		}
	}

	public static void searchHT(int T, int H, long cur, String path) {
		cur+=ht[H][0];
		if(ht[H][T+1]==0 && cur<min){
			min = cur;
			sol = path.trim()+" "+H+":"+min;
		}
	}

	public static int[] retInts(String line) {
		String[] w = line.split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i]);
		return a;
	}
}
