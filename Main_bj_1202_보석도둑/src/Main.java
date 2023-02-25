import java.util.*;
import java.io.*;

class jewel implements Comparable<jewel> {
	long m;
	long v;
	jewel(long m, long v) {
		this.m = m;
		this.v = v;
	}
	@Override
	public String toString() {
		return "["+m+","+v+"]";
	}
	@Override
	public int compareTo(jewel o) {
		if(m-o.m<0)
			return -1;
		else if(m-o.m==0)
			return 0;
		else return 1;
	}
}

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(bf.readLine());
		for (int tc = 1; tc <= t; tc++) {
			System.out.println("#" + tc + " ");

			StringTokenizer st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			PriorityQueue<Long> pq = new PriorityQueue<Long>(Collections.reverseOrder());
			PriorityQueue<jewel> jewely = new PriorityQueue<jewel>();
			PriorityQueue<Long> bag = new PriorityQueue<Long>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine());
				long m = Long.parseLong(st.nextToken());
				long v = Long.parseLong(st.nextToken());
				jewely.offer(new jewel(m, v));
			}
			for (int j = 0; j < K; j++) {
				bag.offer(Long.parseLong(bf.readLine()));
			}
			long res = 0;
			System.out.println(jewely.toString());
			System.out.println(bag.toString());
			System.out.println("=====");
			while (!bag.isEmpty()) {
				long b = bag.poll();
				System.out.println(b+" ::: ");
				while(!jewely.isEmpty()) {
					jewel tmp = jewely.poll();
					if(tmp.m <= b) { // 무게가 가방과 같거나 작은경우
						System.out.print(tmp.m);
						pq.offer(tmp.v);
					}
					else
					{
						jewely.offer(tmp);
						if(tmp.m>b)
							break;
					}
				}
				System.out.println(pq.toString());
				if(!pq.isEmpty())
				{
					res+=(long)pq.poll();
				}
			}
			System.out.println(res);

		}
	}
}
