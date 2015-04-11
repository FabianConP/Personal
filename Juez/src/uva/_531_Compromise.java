package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class _531_Compromise {
	
	public static boolean[][] match;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			if (line.equals("#"))
				break;
			ArrayList<String> a = new ArrayList<String>(100);
			String[] w = line.trim().split(" ");
			for (int i = 0; i < w.length; i++)
				a.add(w[i]);
			while (!(line = in.readLine()).equals("#")) {
				w = line.trim().split(" ");
				for (int i = 0; i < w.length; i++)
					a.add(w[i]);
			}
			ArrayList<String> b = new ArrayList<String>(100);
			w = line.trim().split(" ");
			for (int i = 0; i < w.length; i++)
				b.add(w[i]);
			while (!(line = in.readLine()).equals("#")) {
				w = line.trim().split(" ");
				for (int i = 0; i < w.length; i++)
					b.add(w[i]);
			}
			int rows = a.size(), cols = b.size();
			match = new boolean[rows][cols];
			for (int i = 0; i < rows; i++) 
				for (int j = 0; j < cols; j++) 
					match[i][j] = a.get(i).equals(b.get(j));
			int[][] lcs = new int[rows+1][cols+1];
			for (int i = 1; i <= rows; i++) {
				for (int j = 1; j <= cols; j++) {
					if(match[i-1][j-1]){
						lcs[i][j] = lcs[i-1][j-1]+1;
					}else{
						lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
					}
				}
			}
			String[] ans = new String[lcs[rows][cols]];
			int r = rows, c = cols, count = lcs[rows][cols] -1;
			while(r>0 &&  c>0){
				if(match[r-1][c-1]){
					ans[count--] = a.get(r-1);
					r--;
					c--;
				}else if(lcs[r-1][c]>lcs[r][c-1])
					r--;
				else
					c--;
			}
			for (int i = 0; i < ans.length; i++) {
				if(i!=0)
					out.append(" ");
				out.append(ans[i]);
			}
			out.append("\n");
			
		}
		System.out.print(out);
	}
	
	static void recon(){
		
	}
}
