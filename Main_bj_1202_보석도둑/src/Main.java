import java.util.*;
import java.io.*;

class jewel implements Comparable<jewel> {
	int m;
	int v;
	jewel(int m, int v) {
		this.m = m;
		this.v = v;
	}
	@Override
	public String toString() {
		return "["+m+","+v+"]";
	}
	@Override
	public int compareTo(jewel o) {
		return m-o.m;
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
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
			PriorityQueue<jewel> jewely = new PriorityQueue<jewel>();
			PriorityQueue<Integer> bag = new PriorityQueue<Integer>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine());
				int m = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				jewely.offer(new jewel(m, v));
			}
			for (int j = 0; j < K; j++) {
				bag.offer(Integer.parseInt(bf.readLine()));
			}
			long res = 0;
			System.out.println(jewely.toString());
			System.out.println(bag.toString());
			System.out.println("=====");
			while (!bag.isEmpty()) {
				int b = bag.poll();
				//System.out.println(b+" ::: ");
				while(!jewely.isEmpty()) {
					jewel tmp = jewely.poll();
					if(tmp.m <= b) { // 무게가 가방과 같거나 작은경우
						System.out.print(tmp.m);
						pq.offer(tmp.v);
					}
					else
					{
						jewely.offer(tmp);
						break;
					}
				}
				System.out.println(pq.toString());
				if(!pq.isEmpty())
				{
					res+=pq.poll();
				}
			}
			System.out.println(res);

		}
	}
}
