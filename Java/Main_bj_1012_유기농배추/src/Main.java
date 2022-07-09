import java.io.*;
import java.util.*;

public class Main {

	static int[] di = new int[] { 0, 1, 0, -1 };
	static int[] dj = new int[] { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_1012.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int cnt = 0;
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			boolean[][] chk = new boolean[M][N];

			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(bf.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				chk[b][a] = true;
			}

			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (chk[i][j]) {
						cnt++;
						dfs(chk, i, j);
					}
				}
			}
			System.out.println(cnt);
		}
	}

	static void dfs(boolean[][] chk, int i, int j) {
		chk[i][j] = false;
		for (int d = 0; d < 4; d++) {
			if (0 <= i + di[d] && 0 <= j + dj[d] && i + di[d] < chk.length && j + dj[d] < chk[i].length
					&& chk[i + di[d]][j + dj[d]]) {
				dfs(chk, i + di[d], j + dj[d]);
			}
		}
	}
}
