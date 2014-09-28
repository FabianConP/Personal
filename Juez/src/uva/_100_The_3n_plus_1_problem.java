package uva;
import java.util.Scanner;

public class _100_The_3n_plus_1_problem {
	
	public static int calcule(int c, int n) {
		if (n != 1) {
			if (n % 2 == 1) {
				return calcule(++c, (3 * n) + 1);
			} else {
				return calcule(++c, n/2);
			}
		}
		return c+1;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		d: do {
			int a = scan.nextInt(), b = scan.nextInt(), max = 0, aux;
			for (int i = Math.min(a, b); i <= Math.max(a, b); i++) {
				aux = calcule(0, i);
				max = Math.max(aux, max);
			}
			System.out.println(a + " " + b + " " + max);
		} while (scan.hasNext());

	}
}