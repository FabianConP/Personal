package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class _10009_All_Roads_Lead_Where {
	public static HashMap<Character, Integer> map; //Mapa para reconocer 1er letra en el arreglo m
	public static char[] m; //Letras usadas
	public static int minL; //Mínima longitud de la rta
	public static String minPath; //Mínimo camino
	public static boolean[][] used; //Matriz que guarda si se uso ese camino

	public static ArrayList<Integer>[] ma; //Matriz de adyacencia

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			int times = Integer.parseInt(line);
			for (int i = 0; i < times; i++) {
				br.readLine();
				if(i!=0)out.append("\n");
				int[] data = returnInts(br.readLine());
				ma = new ArrayList[data[0]*2];
				used = new boolean[data[0]*2][data[0]*2];
				map = new HashMap<Character, Integer>();
				m = new char[26];
				int con = 0;
				minL = 50; //Longitud que superaria una rta
				String[] names;
				for (int j = 0; j < data[0]*2; j++) 
					ma[j] = new ArrayList<Integer>();
				for (int j = 0; j < data[0]; j++) {
					names = br.readLine().split(" ");
					//Mapea si es una nueva letra y guarda al arreglo m
					if (!map.containsKey(names[0].charAt(0))) {
						m[con] = names[0].charAt(0);
						map.put(m[con], con++);
					}
					if (!map.containsKey(names[1].charAt(0))) {
						m[con] = names[1].charAt(0);
						map.put(m[con], con++);
					}
					//Agrega la relación en la matriz de adyacencia
					ma[map.get(names[0].charAt(0))].add(map.get(names[1]
							.charAt(0)));
					ma[map.get(names[1].charAt(0))].add(map.get(names[0].charAt(0)));
				}
				for (int j = 0; j < data[1]; j++) { //Queries
					names = br.readLine().split(" ");
					minL = 50;
					minPath = "";
					DFS(map.get(names[0].charAt(0)), 0, names[1].charAt(0), "");
					out.append(minPath).append("\n");
				}
			}
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}

	public static void DFS(int index, int step, char end, String path) {
		if (step < minL) {
			if(m[index]==end){ //Llegó al final
				minL = step+1;
				minPath = path+m[index];  
			}
			for (int i = 0; i < ma[index].size(); i++){
				//Marcar camino usado e ir a la siguiente ciudad
				if(!used[index][ma[index].get(i)] && !used[ma[index].get(i)][index]){
					used[index][ma[index].get(i)] = used[ma[index].get(i)][index] = true;
					DFS(ma[index].get(i), step+1, end, path+(m[index]+""));
					used[index][ma[index].get(i)] = used[ma[index].get(i)][index] = false;
				}
			}
		}
	}

	public static int[] returnInts(String line) {
		String[] a = line.split(" ");
		int[] nums = new int[a.length];
		for (int i = 0; i < nums.length; i++)
			nums[i] = Integer.parseInt(a[i]);
		return nums;
	}
}
