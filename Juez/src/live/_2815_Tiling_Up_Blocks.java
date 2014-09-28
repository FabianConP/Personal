package live;
import java.awt.Point;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class _2815_Tiling_Up_Blocks {

	public static Point[] blocks;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		StringBuilder out = new StringBuilder();
		while (in.hasNext()) {
			int size = in.nextInt();
			if (size == 0)
				break;
			blocks = new Point[size];
			for (int i = 0; i < size; i++)
				blocks[i] = new Point(in.nextInt(), in.nextInt());
			Arrays.sort(blocks, new OrderBlocks());
			out.append(findLIS(size) + "\n");
		}
		System.out.print(out + "*\n");
	}

	public static boolean match(int i, int j) {
		return blocks[i].x <= blocks[j].x && blocks[i].y <= blocks[j].y;
	}

	public static int findLIS(int size) {
		int[] dp = new int[size];
		Arrays.fill(dp, 1);
		int ans = 1;
		for (int i = 0; i < dp.length; i++) 
			for (int j = 0; j < i; j++) 
				if (match(j, i) && dp[i] + 1 > dp[j])
					dp[i] = Math.max(dp[i], dp[j] + 1);
		for (int i = 0; i < dp.length; i++) 
			ans = Math.max(ans, dp[i]);
		return ans;
	}

	private static class OrderBlocks implements Comparator<Point> {

		@Override
		public int compare(Point arg0, Point arg1) {
			if (arg0.x == arg1.x)
				return arg0.y - arg1.y;
			return arg0.x - arg1.x;
		}

	}
}
