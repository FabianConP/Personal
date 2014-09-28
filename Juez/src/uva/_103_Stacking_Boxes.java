package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class _103_Stacking_Boxes {

	public static int boxes, dim, aux[], prev[];
	public static Box[] b;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int[] nm = readInts(line);
			boxes = nm[0];
			dim = nm[1];
			b = new Box[boxes];
			aux = new int[dim];
			prev = new int[boxes];
			int lis[] = new int[boxes], max = 0, idMax = 0;
			for (int i = 0; i < boxes; i++) {
				aux = readInts(in.readLine());
				b[i] = new Box(i, aux);
				prev[i] = i;
				lis[i] = 1;
			}
			Arrays.sort(b);
			for (int i = 0; i < lis.length; i++)
				for (int j = 0; j < i; j++)
					if (b[i].in(b[j]) && lis[i] < lis[j] + 1) {
						lis[i] = lis[j] + 1;
						prev[b[i].id] = b[j].id;
					}
			idMax = max = 0;
			for (int i = 0; i < boxes; i++)
				if (max <= lis[i]) {
					max = lis[i];
					idMax = b[i].id;
				}
			ArrayList<Integer> listOfLIS = new ArrayList<Integer>(max);
			while (prev[idMax] != idMax) {
				listOfLIS.add(idMax + 1);
				idMax = prev[idMax];
			}
			listOfLIS.add(idMax + 1);
			out.append(max + "\n");
			for (int i = listOfLIS.size() - 1; i >= 0; i--) {
				out.append(listOfLIS.get(i));
				if (i != 0)
					out.append(" ");
			}
			out.append("\n");
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
}

class Box implements Comparable<Box> {
	int id, dim[], sum;

	public Box(int id, int[] dim) {
		this.id = id;
		Arrays.sort(dim);
		this.dim = new int[dim.length];
		sum = 0;
		for (int i = 0; i < dim.length; i++) {
			this.dim[i] = dim[i];
			sum += this.dim[i];
		}
	}

	public boolean in(Box o) {
		for (int i = 0; i < this.dim.length; i++)
			if (o.dim[i] >= this.dim[i])
				return false;
		return true;
	}

	@Override
	public int compareTo(Box o) {
		return sum-o.sum;
	}

	@Override
	public String toString() {
		return "id = " + id + " " + Arrays.toString(dim);
	}

}