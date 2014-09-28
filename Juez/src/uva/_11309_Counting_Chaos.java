package uva;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11309_Counting_Chaos {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		do {
			line = in.readLine();
			if (line == null || line.length() == 0)
				break;
			int times = Integer.parseInt(line);
			for (int i = 0; i < times; i++) {
				String[] w = in.readLine().trim().split(":");
				int[] hm = new int[2];
				hm[0] = Integer.parseInt(w[0]);
				hm[1] = Integer.parseInt(w[1]);
				do {
					if (hm[1] + 1 == 60) {
						hm[1] = 0;
						if (hm[0] + 1 == 24)
							hm[0] = 0;
						else
							hm[0]++;
					} else
						hm[1]++;
				} while (!date(hm[0], hm[1]));
				out.append(format(hm[0]) + ":" + format(hm[1]) + "\n");
			}
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}

	public static boolean date(int h, int m) {
		String hh, mm;
		if (h == 0) {
			if (m < 10)
				return true;
			else {
				mm = m + "";
				return mm.charAt(0) == mm.charAt(1);
			}
		} else if (h < 10) {
			hh = h + "";
			mm = format(m);
			return hh.charAt(0) == mm.charAt(1);
		}
		hh = format(h);
		mm = format(m);
		return hh.equals(((new StringBuilder(mm)).reverse()).toString());
	}

	public static String format(int n) {
		if (n < 10)
			return "0" + n;
		return n + "";
	}
}
