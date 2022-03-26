import java.io.*;
import java.util.*;

class pair implements Comparable<pair> {
	int v;
	long w;

	pair() {
		this(0, 0);
	}

	pair(int v, long w) {
		this.v = v;
		this.w = w;
	}

	@Override
	public int compareTo(pair o) {
		// TODO Auto-generated method stub
		return Long.compare(w,o.w);
	}
}

class pair2 implements Comparable<pair2> {
	int v;
	long w;

	pair2() {
		this(0, 0);
	}

	pair2(int v, long w) {
		this.v = v;
		this.w = w;
	}

	@Override
	public int compareTo(pair2 o) {
		// TODO Auto-generated method stub
		return Long.compare(o.w,w);
	}
}

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2307.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			System.out.print("##" + test_case + " ");

			StringTokenizer st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[][] mat = new int[N][N];
			// System.out.println("N : " + N + " , M : " + M);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					mat[i][j] = -1;
				}
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(bf.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				mat[u - 1][v - 1] = w;
				mat[v - 1][u - 1] = w;
			}

			long max = 0;
			long min = -1;
			PriorityQueue<pair> pq = new PriorityQueue<pair>();
			long[] vnear = new long[N];
			int[] path = new int[N];
			boolean[] check = new boolean[N];
			int cnt = 0; // 종료조건

			vnear = new long[N];
			Arrays.fill(vnear, -1);
			check = new boolean[N];
			cnt = 0;
			vnear[0] = 0;
			pq.clear();
			pq.offer(new pair(0, 0));
			while (!pq.isEmpty()) {
				pair node = pq.poll(); // 0->pair.v 로가는 지점
				if (check[node.v])
					continue;
				check[node.v] = true;
				cnt++;
				if (cnt == N)
					break;
				for (int e = 0; e < N; e++) // 0->pair->e로 가는 경우의 수를 계산
				{
					if (mat[node.v][e] == -1)
						continue;
					if (!check[e] && (vnear[e] == -1 || vnear[e] > vnear[node.v] + mat[node.v][e])) {
						vnear[e] = vnear[node.v] + mat[node.v][e];
						path[e] = node.v;
						pq.offer(new pair(e, vnear[e]));
					}
				}
			}
			System.out.println("now : "+Arrays.toString(vnear));
			System.out.println("path: "+Arrays.toString(path));
			min = vnear[N-1];
			int[] route = new int[N];
			int x = N-1;
			for(int i=0;i<N;i++)
			{
				route[i] = path[x];
				x = path[x];
				if(x==0)
					break;
			}
			System.out.println("route : "+Arrays.toString(route));
			x = N-1;
			for(int i=0;i<N;i++)
			{
				if(x==0 && route[i]==0) break;
				System.out.println(x+" , "+route[i]);
				vnear = new long[N];
				Arrays.fill(vnear, -1);
				check = new boolean[N];
				cnt = 0;
				vnear[0] = 0;
				pq.clear();
				pq.offer(new pair(0, 0));
				while (!pq.isEmpty()) {
					pair node = pq.poll(); // 0->pair.v 로가는 지점
					if (check[node.v])
						continue;
					check[node.v] = true;
					cnt++;
					if (cnt == N)
						break;
					for (int e = 0; e < N; e++) // 0->pair->e로 가는 경우의 수를 계산
					{
						if (mat[node.v][e] == -1)
							continue;
						if(e==x && node.v==route[i]) continue;
						if(x==node.v && route[i]==e) continue;
						if (!check[e] && (vnear[e] == -1 || vnear[e] > vnear[node.v] + mat[node.v][e])) {
							vnear[e] = vnear[node.v] + mat[node.v][e];
							pq.offer(new pair(e, vnear[e]));
						}
					}
				}
				System.out.println(Arrays.toString(vnear));
				if(vnear[N-1]==-1)
				{
					max = -1;
					//break;
				}
				if(max<vnear[N-1])
					max = vnear[N-1];
				x=route[i];
			}
			if(max==-1)
				System.out.println(-1);
			else
				System.out.println(max-min);
		}
	}

}
