package uva;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class _11110_Equidivisions {
	
	static int[][] dir = {{1,0}, {0,1},{-1,0},{0,-1}};

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line;
		while ((line = in.readLine()) != null) {
			int n = Integer.parseInt(line);
			if(n == 0) break;
			int[][] m = new int[n][n];
			for(int i = 0; i < n - 1; i++) {
				String[] l = in.readLine().trim().split(" ");
				for(int j = 0; j < l.length; j += 2) {
					int x = Integer.parseInt(l[j]) - 1, y = Integer.parseInt(l[j + 1]) - 1;
					m[x][y] = i + 1;
				}
			}
			HashSet<Integer> used = new HashSet<>(n + 1);
			boolean correct = true;
			for(int i = 0; i < n; i++) 
				for(int j = 0; j < n; j++) {
					if(m[i][j] < 0) continue;
					if(used.contains(m[i][j])) correct = false;
					used.add(m[i][j]);
					Queue<Point> q = new LinkedList<>();
					q.offer(new Point(i, j));
					m[i][j] -= n;
					while(!q.isEmpty()) {
						Point cur = q.poll();
						for(int[] d: dir) {
							int r = cur.x + d[0], c = cur.y + d[1];
							if(r >= 0  && r < n && c >= 0 && c < n && m[i][j] + n == m[r][c]) {
								m[r][c] -= n;
								q.offer(new Point(r, c));
							}
						}
					}
				}
			for(int i = 0; i < n; i++) 
				for(int j = 0; j < n; j++) if(m[i][j] >= 0) correct = false;
			out.append(correct ? "good\n" : "wrong\n");
		}
		System.out.print(out);
	}

}
