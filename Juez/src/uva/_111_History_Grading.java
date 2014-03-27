package uva;

import java.io.IOException;
import java.util.Scanner;

public class _111_History_Grading {

	public static Scanner in;

	public static void main(String[] args) throws IOException {
		in = new Scanner(System.in);
		StringBuilder out = new StringBuilder();
		int students = in.nextInt();
		int[] his = new int[students], aux = readInts(students), stud = new int[students];
		for (int i = 0; i < his.length; i++)
			his[aux[i] - 1] = i + 1;
		while (in.hasNextInt()) {
			aux = readInts(students);
			int equal = 0, lcs[][] = new int[students + 1][students + 1];
			for (int i = 0; i < stud.length; i++)
				stud[aux[i] - 1] = i + 1;
			for (int i = 1; i < lcs.length; i++) {
				if (stud[i - 1] == his[i - 1])
					equal++;
				for (int j = 1; j < lcs[i].length; j++)
					if (stud[i - 1] == his[j - 1])
						lcs[i][j] = lcs[i - 1][j - 1] + 1;
					else
						lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
			}
			out.append(Math.max(equal, lcs[his.length][his.length]) + "\n");
		}
		System.out.print(out);
	}

	public static int[] readInts(int size) {
		int[] a = new int[size];
		for (int i = 0; i < a.length; i++)
			a[i] = in.nextInt();
		return a;
	}
}
