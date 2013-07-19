package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10066_The_Twin_Towers {

	public static int[][] dp;//Matrix X x Y
	
	//Size of LCS	
	public static void lcs(String[] x, String[] y) {
		int n = x.length;
		int m = y.length;
		for (int i = 0; i < n + 1; i++)
			for (int j = 0; j < m + 1; j++)
				if (i == 0 || j == 0)
					dp[i][j] = 0;
				else if (x[i - 1].equals(y[j - 1]))
					dp[i][j] = dp[i - 1][j - 1] + 1;
				else
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder sb = new StringBuilder();
		int t = 1;
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			String[] nums = line.split(" ");
			int n1 = Integer.parseInt(nums[0]);
			int n2 = Integer.parseInt(nums[1]);
			if(n1==0 && n2==0)
				break d;
			String[] a1 = br.readLine().split(" ");
			String[] a2 = br.readLine().split(" ");
			dp = new int[a1.length+ 1][a2.length + 1];//Size of matrix
			lcs(a1, a2);
			sb.append("Twin Towers #").append(t++).append("\n");
			sb.append("Number of Tiles : ").append(dp[a1.length][a2.length]).append("\n\n");
		} while (line != null && line.length() != 0);
		System.out.print(sb);
	}
}

