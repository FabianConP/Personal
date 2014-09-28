package spoj;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class _440_The_Turtle_s_Shortest_Path {
	
	public static ArrayList<Integer>[] m;
	public static boolean[] vis;
	public static boolean cycle;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		do {
			line = in.readLine();
			if (line == null || line.length() == 0)
				break;
			int[] nm = retInts(line);
			int[] v;
			m = new ArrayList[nm[0]];
			for (int i = 0; i < m.length; i++) 
				m[i] = new ArrayList<Integer>();
			vis = new boolean[nm[0]];
			cycle = false;
			for (int i = 0; i < nm[1]; i++) {
				v = retInts(in.readLine());
				m[v[0]-1].add(v[1]-1);
			}
			for (int i = 0; i < m.length; i++) 
				if(m[i].size()>0 && !vis[i])
					DFS(i);
			out.append((!cycle?"YES":"NO")+"\n");
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}
	
	public static void DFS(int node){
		if(vis[node]){
			cycle = true;
			return;
		}
		if(cycle)
			return;
		vis[node] = true;
		for (int i = 0; i < m[node].size(); i++) {
			DFS(m[node].get(i));
		}
	}
	
	public static int[] retInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i].trim());
		return a;
	}
}