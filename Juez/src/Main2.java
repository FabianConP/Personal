import java.util.Scanner;


public class Main2 {


	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		d: do {
			int a = scan.nextInt(), b = scan.nextInt(), c = scan.nextInt();
			long d = 0;
			if(b<a){
				int aux = b;
				b = a;
				a = aux;
			}
			long f = 0;
			for (int i = 0, j = a; i < b; i++){
				d+=j+i;
				if(i+1==b)
					f = i+j;
			}
			d *=2;
			if(c>b && c-b-1>=0)
				d = d+(c-b-1)*f;
			System.out.println(d);
		} while (scan.hasNext());
	}
}
