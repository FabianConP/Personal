package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _347_Run {

	public static int[] all;

	public static void cs() {
		int tam = 0;
		all = new int[448];
		for (int i = 13; i <= 9682415; i++)
			if (run(i))
				all[tam++] = i;
	}

	public static int upper_bound(int key) {
        int lower = 0, upper = all.length - 1, ans = -1;
        while (lower <= upper) {
            int mid = (lower + upper) / 2;
            if (key > all[mid])
                lower = mid + 1;
            else {
                upper = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int time = 1;
		cs();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int n = Integer.parseInt(line.trim());
			if (n == 0)
				break;
			int ans = upper_bound(n);
			out.append("Case " + time++ + ": " + all[ans] + "\n");
		}
		System.out.print(out);
	}

	public static boolean run(int num) {
		char[] n = (num + "").toCharArray();
		int mask = 0, used = 0, pos = 0;
		for (int i = 0; i < n.length; i++) {
			if (n[i] == '0' || (mask & (1 << (n[i] - '0'))) != 0)
				return false;
			mask |= (1 << (n[i] - '0'));
		}
		mask = 0;
		do {
			if ((mask & (1 << pos)) != 0)
				return false;
			mask |= (1 << pos);
			pos = (pos + n[pos] - '0') % n.length;
			used++;
		} while (pos != 0);
		return used == n.length;
	}
}
