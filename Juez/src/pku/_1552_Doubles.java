package pku;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class _1552_Doubles {
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		w: while (scan.hasNext()) {
			int n = -1, con = 0;
			boolean[] a = new boolean[101];
			Arrays.fill(a, false);
			ArrayList<Integer> ll = new ArrayList<Integer>();
			HashSet<Integer> set = new HashSet<Integer>();
			while ((n = scan.nextInt()) != 0) {
				if (n == -1)
					break w;
				ll.add(n);
				set.add(n);
			}
			Collections.sort(ll);
			for (int i = ll.size() - 1; i >= 0; i--) {
				n = ll.get(i);
				if (n % 2 == 0 && set.contains(n / 2))
					con++;
			}
			System.out.println(con);
		}
	}

}
