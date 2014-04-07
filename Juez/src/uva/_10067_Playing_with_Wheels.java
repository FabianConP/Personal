package uva;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class _10067_Playing_with_Wheels {

	public static int[] ini, fin;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		StringBuilder out = new StringBuilder();
		int times = in.nextInt();
		for (int i = 0; i < times; i++) {
			ini = new int[4];
			fin = new int[4];
			for (int j = 0; j < 4; j++)
				ini[j] = in.nextInt();
			for (int j = 0; j < 4; j++)
				fin[j] = in.nextInt();
			int forbidden = in.nextInt();
			boolean[][][][] f = new boolean[10][10][10][10];
			for (int j = 0; j < forbidden; j++)
				f[in.nextInt()][in.nextInt()][in.nextInt()][in.nextInt()] = true;
			Queue<W> q = new LinkedList<>();
			W cur = new W(ini[0], ini[1], ini[2], ini[3],0);
			q.add(cur);
			int minStep = Integer.MAX_VALUE;
			while (!q.isEmpty()) {
				cur = q.poll();
				if (cur.equals(fin)) {
					minStep = cur.s;
					break;
				} else  {
					if (!f[a1(cur.w0)][cur.w1][cur.w2][cur.w3]){
						q.add(new W(a1(cur.w0), cur.w1, cur.w2, cur.w3,cur.s+1));
						f[a1(cur.w0)][cur.w1][cur.w2][cur.w3] = true;
					}
					if (!f[cur.w0][a1(cur.w1)][cur.w2][cur.w3]){
						q.add(new W(cur.w0, a1(cur.w1), cur.w2, cur.w3,cur.s+1));
						f[cur.w0][a1(cur.w1)][cur.w2][cur.w3] = true;
					}
					if (!f[cur.w0][cur.w1][a1(cur.w2)][cur.w3]){
						q.add(new W(cur.w0, cur.w1, a1(cur.w2), cur.w3,cur.s+1));
						f[cur.w0][cur.w1][a1(cur.w2)][cur.w3] = true;
					}
					if (!f[cur.w0][cur.w1][cur.w2][a1(cur.w3)]){
						q.add(new W(cur.w0, cur.w1, cur.w2, a1(cur.w3),cur.s+1));
						f[cur.w0][cur.w1][cur.w2][a1(cur.w3)] = true;
					}
					if (!f[s1(cur.w0)][cur.w1][cur.w2][cur.w3]){
						q.add(new W(s1(cur.w0), cur.w1, cur.w2, cur.w3,cur.s+1));
						f[s1(cur.w0)][cur.w1][cur.w2][cur.w3] = true;
					}
					if (!f[cur.w0][s1(cur.w1)][cur.w2][cur.w3]){
						q.add(new W(cur.w0, s1(cur.w1), cur.w2, cur.w3,cur.s+1));
						f[cur.w0][s1(cur.w1)][cur.w2][cur.w3] = true;
					}
					if (!f[cur.w0][cur.w1][s1(cur.w2)][cur.w3]){
						q.add(new W(cur.w0, cur.w1, s1(cur.w2), cur.w3,cur.s+1));
						f[cur.w0][cur.w1][s1(cur.w2)][cur.w3] = true;
					}
					if (!f[cur.w0][cur.w1][cur.w2][s1(cur.w3)]){
						q.add(new W(cur.w0, cur.w1, cur.w2, s1(cur.w3),cur.s+1));
						f[cur.w0][cur.w1][cur.w2][s1(cur.w3)] = true;
					}
				}
			}
			if(minStep!=Integer.MAX_VALUE)
				out.append(minStep+"\n");
			else
				out.append("-1\n");
		}

		System.out.print(out);
	}

	public static int a1(int w) {
		return (w + 1) % 10;
	}
	
	public static int s1(int w) {
		return (w-1<0)?10-w-1:w-1;
	}
}

class W {
	int w0, w1, w2, w3, s;

	public W(int w0, int w1, int w2, int w3, int s) {
		this.w0 = w0;
		this.w1 = w1;
		this.w2 = w2;
		this.w3 = w3;
		this.s = s;
	}

	@Override
	public String toString() {
		return "[" + w0 + "," + w1 + "," + w2 + "," + w3 + "]";
	}

	public boolean equals(int[] fin) {
		return this.w0 == fin[0] && this.w1 == fin[1] && this.w2 == fin[2]
				&& this.w3 == fin[3];
	}

}
