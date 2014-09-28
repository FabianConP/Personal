package uva;

import java.util.Scanner;

public class _729_The_Hamming_Distance {

	public static boolean next_permutation(int[] vec) {
		int tmp;
		for (int i = vec.length - 2; i >= 0; i--)
			if (vec[i + 1] > vec[i])
				for (int j = vec.length - 1; j >= 0; j--)
					if (vec[j] > vec[i]) {
						tmp = vec[i];
						vec[i] = vec[j];
						vec[j] = tmp;
						for (int k = i + 1, l = vec.length - 1; k < l; k++, l--) {
							tmp = vec[k];
							vec[k] = vec[l];
							vec[l] = tmp;
						}
						return true;
					}
		return false;
	}

	public static StringBuilder printArray(int[] a) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < a.length; i++)
			sb.append(a[i]);
		return sb;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int times = scan.nextInt();
		f: for (int i = 0; i < times; i++) {
			int l = scan.nextInt();
			int k = scan.nextInt();
			int[] array = new int[l];
			for (int j = l - k; j < array.length; j++)
				array[j] = 1;
			if (i != 0)
				sb.append("\n");
			sb.append(printArray(array)).append("\n");
			while (next_permutation(array))
				sb.append(printArray(array)).append("\n");
		}
		System.out.print(sb);
	}
}
