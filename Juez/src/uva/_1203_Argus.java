package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class _1203_Argus {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		PriorityQueue<Register> pq = new PriorityQueue<Register>();
		while ((line = in.readLine()) != null && !line.equals("#")) {
			String[] info = line.split(" ");
			pq.add(new Register(Integer.parseInt(info[2]), Integer
					.parseInt(info[1])));
		}
		int k = Integer.parseInt(in.readLine().trim());
		Register e;
		while (k > 0) {
			e = pq.poll();
			out.append(e.name + "\n");
			pq.add(new Register(e.per, e.name, e.time + e.per));
			k--;
		}
		System.out.print(out);
	}

	private static class Register implements Comparable<Register> {
		int per, name, time;

		public Register(int per, int name, int time) {
			this.per = per;
			this.name = name;
			this.time = time;
		}

		public Register(int per, int name) {
			this(per, name, per);
		}

		@Override
		public int compareTo(Register arg0) {
			if (this.time == arg0.time)
				return this.name - arg0.name;
			return this.time - arg0.time;
		}

	}

}
