package uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class _12207_That_is_Your_Queue {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line;
		int ncase = 1;
		while ((line = in.readLine()) != null) {
			Integer[] pn = readInts(line);
			int p = pn[0], n = pn[1];
			if(p == 0 && n == 0) break;
			out.append("Case ").append(ncase++).append(":\n");
			TreeMap<Long, Long> q = new TreeMap<>();
			HashMap<Long, Long> ids = new HashMap<>();
			HashSet<Long> set = new HashSet<>();
			for(int i = 0; i < Math.min(n, p); i++) set.add(i + 1L);
			String[][] ins = new String[n][];
			for(int i = 0; i < n; i++) {
				ins[i] = in.readLine().trim().split(" ");
				if(ins[i].length > 1) set.add(Long.parseLong(ins[i][1]));
			}
			for(long v : set) {
				q.put(v, v);
				ids.put(v, v);
			}
			long minKey = 0, value, lastKey, key;
			for(int i = 0; i < n; i++) {
				minKey = q.firstKey();
				lastKey = q.lastKey();
				if(ins[i].length == 1) {
					value = q.get(minKey);
					q.remove(minKey);
					q.put(lastKey + 1, value);
					ids.put(value, lastKey + 1);
					out.append(value).append('\n');
				}else {
					value = Integer.parseInt(ins[i][1]);
					key = ids.get(value);
					q.remove(key);
					q.put(minKey - 1, value);
					ids.put(value, minKey - 1);
				}
			}
		}
		System.out.print(out);
	}

	public static Integer[] readInts(String line) {
		StringTokenizer st = new StringTokenizer(line.trim());
		Integer a[] = new Integer[st.countTokens()], index = 0;
		while (st.hasMoreTokens())
			a[index++] = Integer.parseInt(st.nextToken());
		return a;
	}

}
