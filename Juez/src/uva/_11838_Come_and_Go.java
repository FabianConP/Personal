package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class _11838_Come_and_Go {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int[] nm = readInts(line);
			int n = nm[0], m = nm[1];
			if (n == 0 && m == 0)
				break;
			ComponentesConexos G = new ComponentesConexos();
			G.init(n);
			for (int i = 0; i < m; i++) {
				int[] vwp = readInts(in.readLine());
				int v = vwp[0], w = vwp[1];
				G.add(v - 1, w - 1, 1);
				if (vwp[2] == 2)
					G.add(w - 1, v - 1, 1);
			}
			ArrayList<Integer>[] graph = G.Tarjan();
			boolean connect = false;
			for (int i = 0; i < graph.length && !connect; i++)
				if (graph[i].size() == n)
					connect = true;
			out.append((connect ? "1" : "0") + "\n");
		}
		System.out.print(out);
	}

	public static int[] readInts(String line) {
		String[] w = line.trim().split(" ");
		int[] a = new int[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Integer.parseInt(w[i].trim());
		return a;
	}

	private static class ComponentesConexos {

		public static nodo grafo[];// arreglo de listas<arista> para
		public static boolean visitado[];// lleva los visitados de cada nodo
		public static int cont = 0;// contador para llevar los index y lowlink
		public static Stack<Integer> pila;// pila para sacar soluciones
		public static ArrayList<Integer> res[];//
		public static int sig = 0;

		public static class arista {
			int vecino;// etiqueta del nodo
			int peso;// etiqueta del peso

			public arista(int vecino, int peso) {// constructor
				this.vecino = vecino;
				this.peso = peso;

			}

			public String toString() {
				return "vecino : " + vecino + " peso : " + peso;
			}
		}

		public static class nodo {
			String nombre;
			int id;
			int lowLink;
			int index;
			ArrayList<arista> listaVecinos;

			public nodo(String nombre, int id, int lowlink, int index) {
				this.nombre = nombre;
				this.id = id;
				this.lowLink = lowlink;
				this.index = index;
				listaVecinos = new ArrayList<arista>();
			}

			public String toString() {
				return "id: " + id + " lowlink: " + lowLink + " index: "
						+ index;
			}
		}

		public void add(int a, int b, int peso) {
			grafo[a].listaVecinos.add(new arista(b, peso));
		}

		public void init(int v) {
			grafo = new nodo[v];
			visitado = new boolean[v];
			res = new ArrayList[v];
			cont = 1;
			sig = 0;
			for (int i = 0; i < grafo.length; i++) {
				res[i] = new ArrayList<Integer>();
				grafo[i] = new nodo(i + "", i, 0, 0);
			}
		}

		public ArrayList<Integer>[] Tarjan() {
			pila = new Stack<Integer>();
			for (int i = 0; i < grafo.length; i++) {
				if (!visitado[i]) {
					strongComponent(i);
				}
			}
			return res;
		}

		public static void strongComponent(int v) {
			visitado[v] = true;
			grafo[v].lowLink = cont;
			grafo[v].index = cont;
			cont++;
			pila.add(v);
			for (int i = 0; i < grafo[v].listaVecinos.size(); i++) {
				int vecino = grafo[v].listaVecinos.get(i).vecino;
				if (!visitado[vecino]) {
					strongComponent(vecino);
					grafo[v].lowLink = Math.min(grafo[v].lowLink,
							grafo[vecino].lowLink);
				} else if (pila.contains(vecino)) {
					grafo[v].lowLink = Math.min(grafo[v].lowLink,
							grafo[vecino].index);
				}
			}

			if (grafo[v].lowLink == grafo[v].index) {
				int w;
				do {
					w = pila.pop();
					res[sig].add(w);
				} while (w != v);
				sig++;
			} else {
			}
		}
	}

}
