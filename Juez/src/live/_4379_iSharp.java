package live;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class _4379_iSharp {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		boolean s = false;
		do {
			line = in.readLine();
			if (line == null || line.length() == 0)
				break;
			if (s)
				out.append("\n");
			s = true;
			String prev = line;
			while(!prev.replaceAll("  ", " ").equals(line))
				prev = line = prev.replaceAll("  ", " ");
			String[] sp1 = line.replaceAll(";", "").split(" ");
			String type = sp1[0].replaceAll("[\\[\\]*& ]", ""), name;
			String left = left(sp1[0]), r = "";
			Var[] sen = new Var[sp1.length-1];
			for (int l = 1; l < sp1.length; l++) {
				name = sp1[l].replaceAll("[\\[\\]*&,; ]", "");
				r = right(sp1[l]);
				sen[l-1] = new Var(type+left+r, name);
			}
			for (Var var : sen) 
				out.append(var+"\n");
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}
	
	public static String right(String line){
		String res = line.replaceAll("[^\\[\\]*&]", "");
		return (new StringBuilder(res)).reverse().toString().replaceAll("\\]\\[", "[]");
	}
	
	public static String left(String line){
		return line.replaceAll("[^\\[\\]*&]", "");
	}
}
class Var implements Comparable<Var> {
	String type, name;

	public Var(String type, String name) {
		this.type = type;
		this.name = name;
	}




	@Override
	public String toString() {
		String var = "";
		var += type;
			var += " "+name;
		return var+";";
	}




	@Override
	public int compareTo(Var o) {
		// TODO Auto-generated method stub
		return 0;
	}

}