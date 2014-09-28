package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11356_Dates {

	public static final int[] Y1 = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	public static final int[] Y2 = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	public static String[] mon = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

	public static int[] trans(String line) {
		String[] data = line.split("-");
		int[] a = new int[3];
		a[0] = Integer.parseInt(data[0]);
		for (int i = 0; i < mon.length; i++)
			if(data[1].equals(mon[i])){
				a[1] = i+1;
				break;
			}
		a[2] = Integer.parseInt(data[2]);
		return a;
	}

	public static boolean bis(int y) {
		return (y % 4 == 0 && (y % 100 != 0 || y % 400 == 0));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line = "";
		int[] date;
		int c=1;
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break;
			int times = Integer.parseInt(line);
			for (int i = 0; i < times; i++) {
				date = trans(br.readLine());
				int n = Integer.parseInt(br.readLine());
				while (n != 0) {
					if (!bis(date[0])) {
						if (date[2] + 1 > Y1[date[1] - 1]){
							date[2] = 1;
							date[1]++;
						}else
							date[2]++;
					} else {
						if (date[2] + 1 > Y2[date[1] - 1]){
							date[2] = 1;
							date[1]++;
						}else
							date[2]++;
					}
					if (date[1] > 12){
						date[1]=1;
						date[0]++;
					}
					n--;
				}
				String ans = "Case "+c+++": "+date[0]+"-"+mon[date[1]-1]+"-";
				ans+=((date[2]<10)?"0":"")+date[2];
				sb.append(ans).append("\n");
			}
		} while (line != null && line.length() != 0);
		System.out.print(sb);
	}
}
