package uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class _11995_I_Can_Guess_the_Data_Structure {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		d: do {
			line = br.readLine();
			if (line == null || line.length() == 0)
				break d;
			int times = Integer.parseInt(line);
			Stack<Integer> stack = new Stack<Integer>();
			Queue<Integer> queue = new LinkedList<Integer>();
			PriorityQueue<Integer> priority = new PriorityQueue<Integer>(1001,
					Collections.reverseOrder());
			boolean[] data = new boolean[3];
			Arrays.fill(data, true);
			for (int i = 0; i < times; i++) {
				int[] op = returnInts(br.readLine());
				if (op[0] == 1) {
					stack.push(op[1]);
					queue.add(op[1]);
					priority.offer(op[1]);
				} else {
					if ((!stack.isEmpty() && stack.peek() != op[1])
							|| stack.isEmpty()) {
						data[0] = false;
						if(!stack.isEmpty()) stack.pop();
					}else if(!stack.isEmpty())
						stack.pop();
					if ((!queue.isEmpty() && queue.peek() != op[1])
							|| queue.isEmpty()) {
						data[1] = false;
						if(!queue.isEmpty()) queue.poll();
					}else if(!queue.isEmpty())
						queue.poll();
					if ((!priority.isEmpty() && priority.peek() != op[1])
							|| priority.isEmpty()) {
						data[2] = false;
						if(!priority.isEmpty()) priority.poll();
					} else if(!priority.isEmpty())
						priority.poll();
				}
			}
			int con = 0;
			for (int i = 0; i < data.length; i++)
				if (data[i])
					con++;
			if (con == 0) {
				out.append("impossible").append("\n");
			} else if (con == 1) {
				out.append(
						data[0] ? "stack" : (data[1]) ? "queue"
								: "priority queue").append("\n");
			} else {
				out.append("not sure").append("\n");
			}
		} while (line != null && line.length() != 0);
		System.out.print(out);
	}

	public static int[] returnInts(String line) {
		String[] a = line.split(" ");
		int[] nums = new int[a.length];
		for (int i = 0; i < nums.length; i++)
			nums[i] = Integer.parseInt(a[i]);
		return nums;
	}
}
