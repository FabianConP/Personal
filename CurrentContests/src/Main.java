import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

public class Main {
	public static void main(String[] args) throws IOException, ParseException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar[] cal = new Calendar[2];
		cal[0] = Calendar.getInstance();
		cal[1] = Calendar.getInstance();
		do {
			line = in.readLine();
			if (line == null || line.length() == 0)
				break;
			line = line.replaceAll(":", "-");
			cal[0].setTime(sdf.parse(line));
			line  = in.readLine().replaceAll(":", "-");
			cal[1].setTime(sdf.parse(line));
			Arrays.sort(cal);
			long con = 0;
			while(cal[0].compareTo(cal[1])<0){
				cal[0].add(Calendar.DATE, 1);
				con++;
			}
			out.append(con).append("\n");
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}
}
