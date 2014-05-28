package uva;
import java.util.Scanner;

public class _11078_Open_Credit_System {

	public static final long INF = Long.MAX_VALUE / 2;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuilder out = new StringBuilder();
		int times = in.nextInt();
		for (int i = 0; i < times; i++) {
			int nStudents = in.nextInt(), level = 0;
			long max = in.nextInt(), dif = Integer.MIN_VALUE;
			for (int j = 1; j < nStudents; j++) {
				level = in.nextInt();
				dif = Math.max(max - level, dif);
				max = Math.max(level, max);
			}
			out.append(dif + "\n");
		}
		System.out.print(out);
	}
}
