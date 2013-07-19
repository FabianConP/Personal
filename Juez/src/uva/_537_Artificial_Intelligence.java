package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class _537_Artificial_Intelligence {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		int times = 1;
		StringBuilder out = new StringBuilder();
		DecimalFormat df = new DecimalFormat("####.##");
		df.setMinimumFractionDigits(2);
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			int nlines = Integer.parseInt(line);
			String sentence = "";
			int indexU, indexI, indexP;
			double U, I, P;
			U = I = P = 0;
			for (int i = 0; i < nlines; i++) {
				sentence = br.readLine();
				indexU = sentence.indexOf("U=");
				indexI = sentence.indexOf("I=");
				indexP = sentence.indexOf("P=");
				if (indexU != -1)
					U = capture(sentence, indexU);
				if (indexI != -1)
					I = capture(sentence, indexI);
				if (indexP != -1)
					P = capture(sentence, indexP);
				out.append("Problem #"+times++).append("\n");
				if(indexU == -1)
					out.append("U="+df.format((P/I)).replace(",", ".")+"V");
				else if(indexI==-1)
					out.append("I="+df.format((P/U)).replace(",", ".")+"A");
				else if(indexP==-1)
					out.append("P="+df.format(U*I).replace(",", ".")+"W");
				out.append("\n\n");
			}
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}

	public static double capture(String sentence, int pos) {
		double ans = 0;
		String current = "";
		char sufijo = ' ';
		for (int i = pos+2; i < sentence.length(); i++) 
			if(sentence.charAt(i)=='.' || Character.isDigit(sentence.charAt(i)))
				current += (sentence.charAt(i)+"");
			else{
				sufijo = sentence.charAt(i);
				break;
			}
		ans = Double.parseDouble(current);
		switch (sufijo) {
		case 'm':
			ans /=1000;
			break;
		case 'k':
			ans *=1000;
			break;
		case 'M':
			ans *=1000000;
			break;
		}
		return ans;
	}
}
