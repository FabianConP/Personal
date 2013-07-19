package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10405_Longest_Common_Subsequence {

	public static int[][] dp;//Matrix X x Y
	public static char[] ax, ay;//Strings x and y

	//Constructor of LCS
	public static void construc(String x, String y){
		dp = new int[x.length()+ 1][y.length() + 1];//Size of matrix
		ax = x.toCharArray();//Char array x String
		ay = y.toCharArray();//Char array y string
	}
	
	//Size of LCS	
	public static void lcs(String x, String y) {
		int n = x.length();
		int m = y.length();
		for (int i = 0; i < n + 1; i++)
			for (int j = 0; j < m + 1; j++)
				if (i == 0 || j == 0)
					dp[i][j] = 0;
				else if (x.charAt(i - 1) == y.charAt(j - 1))
					dp[i][j] = dp[i - 1][j - 1] + 1;
				else
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "", line2="";
		StringBuilder sb = new StringBuilder();
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			line2 = br.readLine();
			construc(line, line2);
			lcs(line, line2);
			sb.append(dp[line.length()][line2.length()]).append("\n");
		} while (line != null && line.length() != 0);
		System.out.print(sb);
	}
}

