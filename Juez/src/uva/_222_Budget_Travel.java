package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class _222_Budget_Travel {

	public static double totalDis, galCapacity, milesPerGal, initialCost, min;
	public static Station[] stations;

	public static void back(double curMoney, int last) {
		if ((totalDis - stations[last].dis) <= (galCapacity * milesPerGal)
				&& curMoney != 0) {
			if (curMoney < min)
				min = curMoney;
		} else {
			for (int i = last + 1; i < stations.length; i++) {
				double dis = stations[i].dis - stations[last].dis;
				double fuelDis = galCapacity - (dis / milesPerGal);
				if ((noNext(i, last, galCapacity) || stop(galCapacity, dis))
						&& fuelDis >= 0)
					back(curMoney + 200 + (galCapacity - fuelDis)
							* stations[i].gallon, i);
			}
		}
	}

	public static boolean stop(double gallons, double dis) {
		return milesPerGal * gallons / 2 <= dis;
	}

	public static boolean noNext(int i, int last, double gallons) {
		double dis = 0;
		if (i + 1 < stations.length)
			dis = stations[i + 1].dis - stations[last].dis;
		return milesPerGal * gallons < dis || i + 1 >= stations.length;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		int times = 1;
		while ((line = in.readLine()) != null && line.length() != 0) {
			if (line.trim().equals("-1"))
				break;
			totalDis = Double.parseDouble(line.trim());
			double[] d = retDoubles(in.readLine());
			galCapacity = d[0];
			milesPerGal = d[1];
			initialCost = d[2] * 100;
			int amountStations = (int) d[3];
			stations = new Station[amountStations + 1];
			stations[0] = new Station(0, initialCost);
			for (int i = 1; i <= amountStations; i++)
				stations[i] = new Station(in.readLine().trim());
			min = Double.MAX_VALUE;
			back(initialCost, 0);
			System.out.println("Data Set #" + times++);
			System.out.printf(Locale.US, "minimum cost = $%.2f\n", min / 100.0);
		}
	}

	public static double[] retDoubles(String line) {
		String[] w = line.trim().split(" ");
		double[] a = new double[w.length];
		for (int i = 0; i < w.length; i++)
			a[i] = Double.parseDouble(w[i].trim());
		return a;
	}

	static class Station {
		double dis, gallon;

		public Station(String line) {
			double[] d = retDoubles(line);
			this.dis = d[0];
			this.gallon = d[1];
		}

		public Station(double dis, double gallon) {
			this.dis = dis;
			this.gallon = gallon;
		}
	}
}
