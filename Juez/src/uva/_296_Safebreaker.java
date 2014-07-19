package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class _296_Safebreaker {

	public static int nums[][], v1, v2;
	public static final int LIM = 9999;

	public static void fillNums(int size) {
		nums = new int[size + 1][4];
		char[] charNum;
		for (int i = 0; i <= size; i++) {
			charNum = String.format("%04d", i).toCharArray();
			for (int j = 0; j < charNum.length; j++)
				nums[i][j] = charNum[j] - '0';
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		fillNums(LIM);
		while ((line = in.readLine()) != null && line.length() != 0) {
			int nSet = Integer.parseInt(line.trim());
			for (int i = 0; i < nSet; i++) {
				int nClues = Integer.parseInt(in.readLine().trim());
				Clue[] c = new Clue[nClues];
				for (int j = 0; j < c.length; j++)
					c[j] = new Clue(in.readLine());
				ArrayList<Integer> l = new ArrayList<Integer>();
				boolean correct = true;
				for (int j = 0; j <= LIM; j++) {
					correct = true;
					for (int k = 0; k < c.length && correct; k++)
						if (!check(c[k], j))
							correct = false;
					if (correct)
						l.add(j);
				}
				if (l.isEmpty())
					out.append("impossible\n");
				else if (l.size() > 1)
					out.append("indeterminate\n");
				else if (l.size() == 1)
					out.append(String.format("%04d\n", l.get(0)));

			}
		}
		System.out.print(out);
	}

	public static boolean check(Clue c, int n) {
		v1 = v2 = 0;
		int num[] = nums[n], equal = 0, semi = 0;
		for (int i = 0; i < num.length; i++)
			if (num[i] == c.num[i]) {
				v1 |= (1 << i);
				v2 |= (1 << i);
				equal++;
			}
		if (equal != c.equal)
			return false;
		for (int i = 0; i < c.num.length; i++) {
			if ((v1 & (1 << i)) == 0)
				for (int j = 0; j < num.length; j++) {
					if (i != j && c.num[i] == num[j] && (v2 & (1 << j)) == 0) {
						semi++;
						// isEq[c.num[i]] = isEq[num[j]] = true;
						v1 |= (1 << i);
						v2 |= (1 << j);
						break;
					}
				}
		}
		return semi == c.semi;
	}

	private static class Clue {
		int[] num;
		int equal, semi;

		public Clue(String info) {
			String[] w = info.trim().split("[\\s/]");
			this.num = nums[Integer.parseInt(w[0])];
			equal = Integer.parseInt(w[1]);
			semi = Integer.parseInt(w[2]);
		}

	}
}
