package uva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class _10776_Determine_The_Combination {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder sb = new StringBuilder();
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			String[] data = line.trim().split(" ");
			char[] w = data[0].toCharArray();
			int size = Integer.parseInt(data[1]);
			Queue<C> q = new LinkedList<C>();
			C aux = new C("", 0);
			Arrays.sort(w);
			HashSet<String> dif = new HashSet<String>(10000000);
			for (int i = 0; i < w.length; i++) {
				aux = new C(w[i] + "", i);
				q = new LinkedList<C>();
				q.add(aux);
				while (!q.isEmpty()) {
					aux = q.poll();
					for (int j = aux.index + 1; j < w.length; j++){
						if (aux.path.length() < size)
							q.add(new C(aux.path + w[j], j));
						while(j+1<w.length && w[j]==w[j+1]) j++;
					}
					if (aux.path.length() == size && !dif.contains(aux.path)) {
						sb.append(aux.path).append("\n");
						dif.add(aux.path);
					}
				}
			}
		} while (line != null && line.length() != 0);
		System.out.print(sb);
	}
}

class C {
	String path;
	int index;

	public C(String path, int index) {
		this.path = path;
		this.index = index;
	}

}