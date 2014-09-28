package uva;
import java.io.IOException;
import java.util.Arrays;

public class _386_Perfect_Cubes {

	public static Cube[] l;
	public static final long LIMIT = 200 * 200 * 200;
	public static final double EPS = 1e-5;

	public static void main(String[] args) throws IOException {
		StringBuilder out = new StringBuilder();
		l = new Cube[223]; // Pre calculate
		int cont = 0;
		long a = 0;
		double a_3 = 0, aux;
		for (int i = 2; i <= 199; i++)
			for (int j = i; j <= 199; j++)
				if (p3(i) + p3(j) <= LIMIT)
					for (int k = j; k <= 199; k++) {
						a = p3(i) + p3(j) + p3(k);
						a_3 = (int) Math.round(Math.pow(a, 1 / 3.0));
						aux = Math.pow(a_3, 3);
						if (a <= LIMIT && Math.abs(aux - a) <= EPS)
							l[cont++] = new Cube((int) a_3, i, j, k);
					}
		Arrays.sort(l);
		for (int i = 0; i < cont; i++)
			out.append(l[i] + "\n");
		System.out.print(out);
	}

	public static long p3(long n) {
		return n * n * n;
	}

	private static class Cube implements Comparable<Cube> {
		int a, b, c, d;

		public Cube(int a, int b, int c, int d) {
			this.a = a;
			this.b = b;
			this.c = c;
			this.d = d;
		}

		@Override
		public int compareTo(Cube arg0) {
			if (this.a == arg0.a)
				return this.b - arg0.b;
			return this.a - arg0.a;
		}

		@Override
		public String toString() {
			return "Cube = " + a + ", Triple = (" + b + "," + c + "," + d + ")";
		}

	}
}
