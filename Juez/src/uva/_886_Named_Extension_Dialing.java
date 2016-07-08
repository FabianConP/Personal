package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class _886_Named_Extension_Dialing {
	static int[] b, ids;
	static char[] t;

	static void preprocess(char[] p) {
		int i = 0, j = -1, m = p.length;
		b = new int[p.length + 1];
		b[0] = -1;
		while (i < m) {
			while (j >= 0 && p[i] != p[j])
				j = b[j];
			i++;
			j++;
			b[i] = j;
		}
	}

	static ArrayList<Integer> KMP(char[] p) {
		ArrayList<Integer> r = new ArrayList<>();
		int i = 1, j = 0, n = t.length, m = p.length;
		while (i < n) {
			while (j >= 0 && t[i] != p[j])
				j = b[j];
			i++;
			j++;
			if (j == m) {
				if (ids[i - m - 1] != ids[i - m] && ids[i - m] == ids[i - 1])
					r.add(ids[i - 1]);
				j = b[j];
			}
		}
		return r;
	}

	static int getNumber(char l) {
		l = Character.toUpperCase(l);
		if(l<='C')
			return 2;
		else if(l<='F')
			return 3;
		else if(l<='I')
			return 4;
		else if(l<='L')
			return 5;
		else if(l<='O')
			return 6;
		else if(l<='S')
			return 7;
		else if(l<='V')
			return 8;
		return 9;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		ArrayList<Integer> people = new ArrayList<>();
		ArrayList<String> ext = new ArrayList<>();
		HashSet<String> set = new HashSet<>();
		StringBuilder all = new StringBuilder();
		String[] info = null;
		all.append('#');
		while ((info = in.readLine().trim().split("\\s+")).length == 3) {
			people.add(all.length());
			all.append(getNumber(info[0].charAt(0)));
			for (int i = 0; i < info[1].length(); i++)
				all.append(getNumber(info[1].charAt(i)));
			set.add(info[2]);
			ext.add(info[2]);
		}
		all.append('#');
		ids = new int[all.length()];
		t = new char[all.length()];
		ids[0] = ids[ids.length - 1] = -1;
		t[0] = t[t.length - 1] = '#';
		int start = 1, to;
		for (int j = 0; j < people.size(); j++) {
			to = (j + 1 < people.size() ? people.get(j + 1) : all.length()- 1);
			for (; start < to; start++) {
				ids[start] = j;
				t[start] = all.charAt(start);
			}
		}
		String line = info[0];
		do {
			if (set.contains(line)) {
				out.append(line + "\n");
				continue;
			}
			char[] t = line.toCharArray();
			preprocess(t);
			ArrayList<Integer> matches = KMP(t);
			if (matches.isEmpty())
				out.append("0\n");
			else {
				for (int i = 0; i < matches.size(); i++) {
					if (i != 0)
						out.append(' ');
					out.append(ext.get(matches.get(i)));
				}
				out.append('\n');
			}
		} while ((line = in.readLine()) != null);
		System.out.print(out);
	}

}
