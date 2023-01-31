import java.util.*;
import java.io.*;


시간초과

class num implements Comparable<num> {
	int index;
	int val;
	
	num(int index,int val)
	{
		this.index = index;
		this.val = val;
	}
	
	@Override
	public int compareTo(num o) {
		return val - o.val;
	}
}

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_18870.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		for (int tc = 1; tc <= T; tc++) {

			int n = Integer.parseInt(bf.readLine());
			PriorityQueue<num> pq = new PriorityQueue<num>();

			StringTokenizer st = new StringTokenizer(bf.readLine());

			for (int i = 0; i < n; i++) {
				pq.offer(new num(i,Integer.parseInt(st.nextToken())));
			}
			int[] res = new int[n];
			int cnt = 0;
			int bef = 0;
			for (int i = 0; i < n; i++) {
				num tmp = pq.poll();
				if(i==0) bef = tmp.val;
				if (i>0 && tmp.val != bef)
				{
					bef = tmp.val;
					cnt++;
				}
				res[tmp.index] = cnt;
			}
			for (int i = 0; i < n; i++) {
				System.out.print(res[i] + " ");
			}
			System.out.println();
		}
	}

}
