package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class _105_The_Skyline_Problem {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int[] h = new int[10001];
		while ((line = in.readLine()) != null && line.length() != 0){
			int[] info = readInts(line);
			for (int i = info[0]; i < info[2]; i++) 
				h[i] = Math.max(h[i], info[1]);
		}
		int curH = 0;
		boolean first = true;
		for (int i = 0; i < h.length; i++) 
			if(h[i]!=curH){
				if(!first)
					out.append(" ");
				first = false;
				out.append(i+" "+h[i]);
				curH = h[i];
			}
		System.out.println(out);
	}

	public static int[] readInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i].trim());
		return a;
	}
}
