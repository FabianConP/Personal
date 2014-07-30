package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _459_Graph_Connectivity {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int nSet = Integer.parseInt(line.trim());
			for (int i = 0; i < nSet; i++) {
				if (i == 0)
					in.readLine();
				int nNodes = in.readLine().trim().charAt(0) - 'A' + 1;
				WQU_PC G = new WQU_PC(nNodes);
				while ((line = in.readLine()) != null && line.length() != 0)
					G.union(line.charAt(0) - 'A', line.charAt(1) - 'A');
				if (i != 0)
					out.append("\n");
				out.append(G.getNumberConnectedComponents(nNodes) + "\n");
			}
		}
		System.out.print(out);
	}

	private static class WQU_PC {

		static int padre[];
		static int rango[];

		public WQU_PC(int n) {
			padre = new int[n];
			rango = new int[n];
			for (int i = 0; i < n; ++i) {
				padre[i] = i;
				rango[i] = 1;
			}
		}

		int Find(int x) {
			while (x != padre[x])
				x = padre[x];
			return x;
		}

		void union(int x, int y) {
			int xRoot = Find(x);
			int yRoot = Find(y);
			if (rango[xRoot] > rango[yRoot]) {
				padre[yRoot] = xRoot;
				rango[xRoot] += rango[yRoot];
			} else {
				padre[xRoot] = yRoot;
				rango[yRoot] += rango[xRoot];
			}
		}

		// conexas luego de aplicar el metodo
		static int numComponentes; // variable para el numero total de
									// componentes
									// conexas

		// Metodo para obtener el numero de componentes conexas luego de
		// realizar
		// las conexiones respectivas
		int getNumberConnectedComponents(int n) {
			numComponentes = 0;
			for (int i = 0; i < n; ++i)
				if (padre[i] == i)
					numComponentes++;
			return numComponentes;
		}
	}

}
