package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class _497_Strategic_Defense_Initiative {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int times = Integer.parseInt(in.readLine().trim()), max, idMax;
		for (int t = 0; t < times; t++) {
			if (t == 0)
				in.readLine();
			ArrayList<Integer> l = new ArrayList<Integer>(1000);
			while ((line = in.readLine()) != null && line.length() != 0)
				l.add(Integer.parseInt(line));
			if (t != 0)
				out.append("\n");
			int[] lis = new int[l.size()];
			int[] v = new int[l.size()];
			int[] prev = new int[l.size()];
			for (int j = 0; j < lis.length; j++) {
				lis[j] = 1;
				v[j] = l.get(j);
				prev[j] = j;
			}
			for (int i = 0; i < lis.length; i++)
				for (int j = 0; j < i; j++)
					if (v[i] > v[j] && lis[i] <= lis[j] + 1) {
						lis[i] = lis[j] + 1;
						prev[i] = j;
					}
			idMax = max = 0;
			for (int i = 0; i < lis.length; i++)
				if (max <= lis[i]) {
					max = lis[i];
					idMax = i;
				}
			ArrayList<Integer> listOfLIS = new ArrayList<Integer>(max);
			while (prev[idMax] != idMax) {
				listOfLIS.add(v[idMax]);
				idMax = prev[idMax];
			}
			listOfLIS.add(v[idMax]);
			out.append("Max hits: " + max + "\n");
			for (int i = listOfLIS.size() - 1; i >= 0; i--)
				out.append(listOfLIS.get(i) + "\n");
		}
		System.out.print(out);
	}
}
