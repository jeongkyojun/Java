import java.util.*;
import java.io.*;


public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_15591.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {

			StringTokenizer st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());

			int[][] mat = new int[N][N];

			for (int i = 0; i < N - 1; i++) {
				st = new StringTokenizer(bf.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				if (mat[u-1][v-1] == 0 || mat[u-1][v-1] > w) {
					mat[u-1][v-1] = w;
					mat[v-1][u-1] = w;
				}
			}
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(bf.readLine());
				int k = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				Queue<Integer> q = new LinkedList<Integer>();
				q.offer(v-1);
				int cnt = 0;
				boolean[] chk = new boolean[N];
				chk[v-1] = true;
				while(!q.isEmpty())
				{
					int n = q.poll();
					for(int x=0;x<N;x++)
					{
						// n->x를 확인, v-1 -> x의 경우의 수 충족
						if(mat[n][x]<k || chk[x]) continue;
						// 두 값 중 작은 쪽 선택
						mat[v-1][x] = mat[v-1][n]>mat[n][x]? mat[v-1][n]:mat[n][x];
						cnt++;
						chk[x] = true;
						q.offer(x);
					}
				}
				System.out.println(cnt);
			}
		}
	}

}
