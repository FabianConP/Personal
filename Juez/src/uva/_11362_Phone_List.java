package uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class _11362_Phone_List {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int nCases = Integer.parseInt(in.readLine().trim());
		for (int nCase = 0; nCase < nCases; nCase++) {
			int n = Integer.parseInt(in.readLine().trim());
			ArrayList<String> nums = new ArrayList<>();
			String number;
			for (int i = 0; i < n; i++) {
				number = in.readLine().trim();
				nums.add(number);
			}
			Collections.sort(nums, new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					int i = 0, j = 0, n = o1.length(), m = o2.length();
					char a, b;
					while (true) {
						if (i == n && j == m)
							return 0;
						else if (i == n)
							return 1;
						else if (j == m)
							return -1;
						a = o1.charAt(i);
						b = o2.charAt(j);
						if (a != b)
							return a - b;
						i++;
						j++;
					}
				}
			});
			boolean consistent = true;
			for (int i = 1; i < n && consistent; i++) 
				if (nums.get(i - 1).startsWith(nums.get(i)))
					consistent = false;
			out.append(consistent ? "YES\n" : "NO\n");
		}
		System.out.print(out);
	}

}
