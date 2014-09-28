package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class _644_Immediate_Decodability {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		ArrayList<String> list = new ArrayList<String>();
		int times = 1;
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			if(line.equals("9")){
				boolean flag = true;
				String s1;
				f1:for (int i = 0; i < list.size(); i++) {
					s1 = list.get(i);
					for (int j = 0; j < list.size(); j++) 
						if(i!=j && s1.startsWith(list.get(j))){
							flag = false;
							break f1;
						}
				}
				out.append("Set "+times+++" ");
				if(flag)
					out.append("is immediately decodable").append("\n");	
				else
					out.append("is not immediately decodable").append("\n");
				list = new ArrayList<String>();
			}else
				list.add(line);
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}
}
