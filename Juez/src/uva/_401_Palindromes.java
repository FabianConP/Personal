package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class _401_Palindromes {

	public static HashMap<Character, Character> map;

	public static void fillMap() {
		map = new HashMap<>(30);
		map.put('A', 'A');
		map.put('H', 'H');
		map.put('I', 'I');
		map.put('M', 'M');
		map.put('O', 'O');
		map.put('T', 'T');
		map.put('U', 'U');
		map.put('V', 'V');
		map.put('W', 'W');
		map.put('X', 'X');
		map.put('Y', 'Y');
		map.put('1', '1');
		map.put('8', '8');
		map.put('E', '3');
		map.put('3', 'E');
		map.put('J', 'L');
		map.put('L', 'J');
		map.put('S', '2');
		map.put('2', 'S');
		map.put('Z', '5');
		map.put('5', 'Z');
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		fillMap();
		while ((line = in.readLine()) != null && line.length() != 0) {
			boolean rev = isReverse(line);
			boolean mir = isMirror(line);
			if (rev)
				if (mir)
					out.append(line + " -- is a mirrored palindrome.\n\n");
				else
					out.append(line + " -- is a regular palindrome.\n\n");
			else if (mir)
				out.append(line + " -- is a mirrored string.\n\n");
			else
				out.append(line + " -- is not a palindrome.\n\n");
		}
		System.out.print(out);
	}

	public static String reverse(String w) {
		char[] a = w.toCharArray();
		String r = "";
		for (int i = a.length - 1; i >= 0; i--)
			r += a[i];
		return r;
	}

	public static boolean isReverse(String w) {
		return w.equals(reverse(w));
	}

	public static String mirror(String w) {
		char a[] = w.toCharArray();
		String r = "";
		for (int i = a.length - 1; i >= 0; i--)
			if (!map.containsKey(a[i]))
				return "-";
			else
				r += map.get(a[i]);
		return r;
	}

	public static boolean isMirror(String w) {
		return w.equals(mirror(w));
	}

}
