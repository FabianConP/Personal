package uva;
import java.util.Scanner;


public class _483_Word_Scramble {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		d: do {
			String line = scan.nextLine(), ans = "";
			String[] w = line.split(" ");
			for (int i = 0; i < w.length; i++) {
				ans += new StringBuilder(w[i]).reverse().toString() + " ";
			}
			System.out.println(ans.trim());
		} while (scan.hasNext());

	}

}
