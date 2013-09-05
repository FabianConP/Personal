package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class _10171_Meeting_Prof_Miguel {

	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		d: do {
			line = in.readLine();
			if (line == null || line.length() == 0 || line.trim().equals("0"))
				break d;
			int streets = Integer.parseInt(line.trim());
			Floyd y = new Floyd();
			Floyd2 o = new Floyd2();
			String[][] d = new String[streets][5];
			HashMap<String, Integer> difCities = new HashMap<String, Integer>(streets);
			HashMap<Integer, String> difCities2 = new HashMap<Integer, String>(streets);
			int countDifCities = 0;
			for (int i = 0; i < streets; i++) {
				d[i] = in.readLine().trim().split(" ");
				if (!difCities.containsKey(d[i][2])) {
					difCities.put(d[i][2], countDifCities);
					difCities2.put(countDifCities, d[i][2]);
					countDifCities++;
				}
				if (!difCities.containsKey(d[i][3])) {
					difCities.put(d[i][3], countDifCities);
					difCities2.put(countDifCities, d[i][3]);
					countDifCities++;
				}
			}
			y.inint(countDifCities);
			o.inint(countDifCities);
			for (int i = 0; i < d.length; i++){
				int start = difCities.get(d[i][2]);
				int end = difCities.get(d[i][3]);
				if (d[i][0].trim().equals("M")) {
					o.d[start][end] = Math.min(o.d[start][end], Integer.parseInt(d[i][4]));
					if (d[i][1].trim().equals("B")){
						o.d[end][start] = Math.min(o.d[end][start], Integer.parseInt(d[i][4]));;
					}
				} else {
					y.d[start][end] = Math.min(y.d[start][end], Integer.parseInt(d[i][4]));
					if (d[i][1].trim().equals("B"))
						y.d[end][start] = Math.min(y.d[end][start], Integer.parseInt(d[i][4]));;
						
				}
			}
			y.floyd(countDifCities);
			o.floyd(countDifCities);
			String[] im = in.readLine().trim().split(" ");
			int minPath = Integer.MAX_VALUE / 2, indexCity = -1;
			if((!difCities.containsKey(im[0].trim()) || !difCities.containsKey(im[1].trim())) && !im[0].trim().equals(im[1].trim()))
				out.append("You will never meet.").append("\n");
			else if(difCities.containsKey(im[0].trim()) && difCities.containsKey(im[1].trim())){
			int indexI = difCities.get(im[0].trim());
			int indexM = difCities.get(im[1].trim());
			ArrayList<String> difEndCity = new ArrayList<String>();
			for (int i = 0; i < countDifCities; i++)
				if(minPath == y.d[indexI][i] + o.d[indexM][i]){
					difEndCity.add(difCities2.get(i));
				}else if (minPath > y.d[indexI][i] + o.d[indexM][i]) {
					indexCity = i;
					difEndCity.clear();
					difEndCity.add(difCities2.get(i));
					minPath = y.d[indexI][i] + o.d[indexM][i];
				}
			if (indexCity == -1){
				if(im[0].trim().equals(im[1].trim()))
					out.append("0 "+im[0]).append("\n");
				else
					out.append("You will never meet.\n").append("\n");
			}else{
				out.append(minPath+" ");
				Collections.sort(difEndCity);
				for (int i = 0; i < difEndCity.size(); i++){ 
					if(i!=0)
						out.append(" ");
					out.append(difEndCity.get(i));
				}
				out.append("\n");
				}

			}	else{ if(im[0].trim().equals(im[1].trim()))
				out.append("0 "+im[0]).append("\n");
			else
			out.append("You will never meet.").append("\n");
			}
		} while (line != null && line.length() != 0);
		System.out.print(out);

	}

}
class Floyd {
	public static int d[][];
	public static final int INF = (Integer.MAX_VALUE - 1) / 2;

	public void inint(int n) {
		d = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int k = 0; k < n; k++)
				if (i != k)
					d[i][k] = INF;
	}

	void floyd(int rows) {
		for (int k = 0; k < rows; k++)
			for (int i = 0; i < rows; i++)
				for (int j = 0; j < rows; j++)
					if (d[i][j] > d[i][k] + d[k][j]) {
						d[i][j] = d[i][k] + d[k][j];
					}
	}
	
	public void print(){
		System.out.println();
		for (int i = 0; i < d.length; i++) {
			for (int k = 0; k < d.length; k++) {
				if(d[i][k]>100000)
					System.out.print("* ");
				else
					System.out.print(d[i][k]+" ");
			}
			System.out.println();
		}
	}

}


class Floyd2 {
	public static int d[][];
	public static final int INF = (Integer.MAX_VALUE - 1) / 2;

	public void inint(int n) {
		d = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int k = 0; k < n; k++)
				if (i != k)
					d[i][k] = INF;
	}

	void floyd(int rows) {
		for (int k = 0; k < rows; k++)
			for (int i = 0; i < rows; i++)
				for (int j = 0; j < rows; j++)
					if (d[i][j] > d[i][k] + d[k][j]) {
						d[i][j] = d[i][k] + d[k][j];
					}
	}
	
	public void print(){
		System.out.println();
		for (int i = 0; i < d.length; i++) {
			for (int k = 0; k < d.length; k++) {
				if(d[i][k]>100000)
					System.out.print("* ");
				else
					System.out.print(d[i][k]+" ");
			}
			System.out.println();
		}
	}

}