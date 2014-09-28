package uva;
import java.util.HashSet;
import java.util.Scanner;

public class _11063_B2_Sequence {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int times = 1;
		HashSet<Integer> set = new HashSet<Integer>(101);
		while (scan.hasNext()) {
			set.clear();
			int size = scan.nextInt();
			boolean b2 = true;
			int a[] = new int[size];
			for (int i = 0; i < size; i++) {
				a[i] = scan.nextInt();
				b2 &= a[i] >= 1;
			}
			for (int i = 0; i < a.length && b2; i++) {
				if (set.contains(2 * a[i]))
					b2 = false;
				else
					set.add(2 * a[i]);
				for (int j = i + 1; j < a.length && b2; j++)
					if ((b2 &= a[j] > a[i]) && set.contains(a[i] + a[j]))
						b2 = false;
					else
						set.add(a[i] + a[j]);
			}
			System.out.print("Case #" + times++ + ": It is ");
			if (!b2)
				System.out.print("not ");
			System.out.println("a B2-Sequence.\n");
		}
	}
}
