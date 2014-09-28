package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class _755_487_3279 {
	public static int[] conversion;
	public static HashMap<String, Integer> map;
	public static ArrayList<String> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		conversion = new int[100];
		conversion[(int) 'A'] = conversion[(int) 'B'] = conversion[(int) 'C'] = 2;
		conversion[(int) 'D'] = conversion[(int) 'E'] = conversion[(int) 'F'] = 3;
		conversion[(int) 'G'] = conversion[(int) 'H'] = conversion[(int) 'I'] = 4;
		conversion[(int) 'J'] = conversion[(int) 'K'] = conversion[(int) 'L'] = 5;
		conversion[(int) 'M'] = conversion[(int) 'N'] = conversion[(int) 'O'] = 6;
		conversion[(int) 'P'] = conversion[(int) 'R'] = conversion[(int) 'S'] = 7;
		conversion[(int) 'T'] = conversion[(int) 'U'] = conversion[(int) 'V'] = 8;
		conversion[(int) 'W'] = conversion[(int) 'X'] = conversion[(int) 'Y'] = 9;
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			int ndatasets = Integer.parseInt(line);
			for (int i = 0; i < ndatasets; i++) {
				br.readLine();
				if (i != 0)
					out.append("\n");
				map = new HashMap<String, Integer>();
				list = new ArrayList<String>();
				int nlines = Integer.parseInt(br.readLine());
				String result = "";
				for (int j = 0; j < nlines; j++) {
					result = proccess(br.readLine());
					if (!map.containsKey(result)) {
						list.add(result);
						map.put(result, 1);
					} else
						map.put(result, map.get(result) + 1);
				}
				if (list.isEmpty())
					out.append("No duplicates.").append("\n");
				else {
					Collections.sort(list);
					for (int j = 0; j < list.size(); j++) {
						int times = map.get(list.get(j));
						if(times>1)
							out.append(list.get(j) + " "+times).append("\n");
					}
				}
			}
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}

	public static String proccess(String line) {
		String line2 = line.replaceAll("-", "");
		while (!line2.equals(line)) {
			line = line2;
			line2 = line2.replaceAll("-", "");
		}
		String result = "";
		for (int i = 0; i < line.length(); i++) {
			if (Character.isDigit(line.charAt(i)))
				result += (line.charAt(i) + "");
			else
				result += (conversion[(int) line.charAt(i)] + "");
		}
		return result.substring(0, 3) + "-" + result.substring(3);
	}
}
