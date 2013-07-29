package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class _725_Division {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		boolean start = true;
		do {
			line = in.readLine();
			if (line == null || line.length() == 0)
				break;
			int n = Integer.parseInt(line);
			if (n == 0)
				break;
			if (!start)
				out.append("\n");
			start = false;
			int current = 1234, numer;
			ArrayList<Pair> list = new ArrayList<Pair>(10000);
			while (n*current<= 98765) {
				numer = isDifferent(current, n);
				if (numer != -1)
					list.add(new Pair(f(numer), f(current)));
				current++;
			}
			Collections.sort(list);
			if(list.isEmpty())
				out.append("There are no solutions for "+n+".\n");
			else
				for (Pair pair : list) 
					out.append(pair+(n+"\n"));
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}

	public static int isDifferent(int cur, int n) {
		int binary = 2 << 10;
		char[] nn;
		nn = (f(cur)).toCharArray();
		for (int i = 0; i < nn.length; i++)
			if ((binary & (2 << Character.digit(nn[i], 10))) == 0)
				binary |= 2 << Character.digit(nn[i], 10);
			else
				return -1;
		if(n*cur>98765)
			return -1;
		nn = (f(n * cur)).toCharArray();
		for (int i = 0; i < nn.length; i++)
			if ((binary & (2 << Character.digit(nn[i], 10))) == 0)
				binary |= 2 << Character.digit(nn[i], 10);
			else
				return -1;
		return n * cur;
	}

	public static String f(int n) {
		return (n < 10000 ? "0" : "") + n;
	}
}

class Pair implements Comparable<Pair> {
	String a;
	String b;

	public Pair(String a, String b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public int compareTo(Pair o) {
		return this.a.compareTo(o.a);
	}

	@Override
	public String toString() {
		return a + " / " + b + " = ";
	}

}
