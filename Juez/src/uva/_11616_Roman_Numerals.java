package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class _11616_Roman_Numerals {

	public static void buildConversion() {
		difNums.put(0, "");
		difNums.put(1, "I");
		difNums.put(2, "II");
		difNums.put(3, "III");
		difNums.put(4, "IV");
		difNums.put(5, "V");
		difNums.put(6, "VI");
		difNums.put(7, "VII");
		difNums.put(8, "VIII");
		difNums.put(9, "IX");
		difNums.put(10, "X");
		difNums.put(20, "XX");
		difNums.put(30, "XXX");
		difNums.put(40, "XL");
		difNums.put(50, "L");
		difNums.put(60, "LX");
		difNums.put(70, "LXX");
		difNums.put(80, "LXXX");
		difNums.put(90, "XC");
		difNums.put(100, "C");
		difNums.put(200, "CC");
		difNums.put(300, "CCC");
		difNums.put(400, "CD");
		difNums.put(500, "D");
		difNums.put(600, "DC");
		difNums.put(700, "DCC");
		difNums.put(800, "DCCC");
		difNums.put(900, "CM");
		difNums.put(1000, "M");
		difNums.put(2000, "MM");
		difNums.put(3000, "MMM");
		String conv = "";
		int div[] = { 1000, 100, 10, 1 }, n = 0, rem = 0;
		for (int i = 1; i < 4000; i++, conv = "") {
			n = i;
			for (int j = 0; j < div.length; j++) {
				rem = n / div[j];
				conv += difNums.get(rem * div[j]);
				n -= rem * div[j];
			}
			araToRom.put(i, conv);
			romToAra.put(conv, i);
		}

	}

	public static HashMap<Integer, String> difNums = new HashMap<Integer, String>(
			50);
	public static HashMap<Integer, String> araToRom = new HashMap<Integer, String>(
			5000);
	public static HashMap<String, Integer> romToAra = new HashMap<String, Integer>(
			5000);

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		buildConversion();
		while ((line = in.readLine()) != null && line.length() != 0) {
			line = line.trim();
			if (Character.isDigit(line.charAt(0)))
				out.append(araToRom.get(Integer.parseInt(line)) + "\n");
			else
				out.append(romToAra.get(line) + "\n");

		}
		System.out.print(out);
	}
}
