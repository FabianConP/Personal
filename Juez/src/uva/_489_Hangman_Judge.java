package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _489_Hangman_Judge {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			int round = Integer.parseInt(line);
			if (round == -1)
				break;
			char[] word = br.readLine().toCharArray();
			char[] guess = br.readLine().toCharArray();
			int error = 0, total = word.length;
			boolean flag = false;
			for (int i = 0; i < guess.length; i++) {
				flag = false;
				for (int j = 0; j < word.length; j++) {
					if (word[j] == guess[i]) {
						word[j] = '*';
						flag = true;
						total--;
					}
				}
				if (!flag && total!=0)
					error++;
			}
			out.append("Round " + round).append("\n");
			if (error < 7 && total == 0)
				out.append("You win.").append("\n");
			else if (total > 0 && error < 7)
				out.append("You chickened out.").append("\n");
			else
				out.append("You lose.").append("\n");
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}
}
