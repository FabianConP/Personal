package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class _10305_Ordering_Tasks {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		do {
			line = in.readLine();
			if (line == null || line.length() == 0 || line.equals("0 0"))
				break;
			int[] nm = retInts(line);
			Task[] t = new Task[nm[0]];
			for (int i = 0; i < t.length; i++) 
				t[i] = new Task(i+1);
			int[] v;
			for (int i = 0; i < nm[1]; i++) {
				v = retInts(in.readLine());
				t[v[1]-1].num++;
			}
			Arrays.sort(t);
			for (int i = 0; i < t.length; i++) {
				if(i!=0)
					out.append(" ");
				out.append(t[i].id);
			}
			out.append("\n");
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}
	
	public static int[] retInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i].trim());
		return a;
	}
}

class Task implements Comparable<Task>{
	int id, num;

	public Task(int id) {
		this.id =id;
		num = 0;
	}

	@Override
	public int compareTo(Task arg0) {
		return this.num-arg0.num;
	}
	
	
	
}
