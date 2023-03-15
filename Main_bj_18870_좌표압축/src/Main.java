import java.util.*;
import java.io.*;

시간초과
더 빨리 정렬할 수 있는 방법을 찾아야 한다.

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
			int cnt = -1;
			int[] res = new int[n];
			int bef = -1000000001;
			while(!pq.isEmpty()) {
				num tmp = pq.poll();
				if(bef<tmp.val)
					cnt++;
				res[tmp.index] = cnt;
				bef = tmp.val;
			}
			for(int i=0;i<n;i++)
				System.out.print(res[i]+" ");
			System.out.println();
		}
	}

}
