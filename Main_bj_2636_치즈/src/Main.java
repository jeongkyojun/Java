
import java.util.*;
import java.io.*;

public class Main {

	static int[] di = new int[] { -1, 1, 0, 0 };// 상 하 좌 우
	static int[] dj = new int[] { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2636.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());

			int[][] mat = new int[R][C];
			boolean[][] chk = new boolean[R][C];

			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(bf.readLine());
				for (int j = 0; j < C; j++) {
					mat[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			filling_bfs(chk, mat, 0, 0);
			int before = 0;
			int now;
			loop: for (int t = 0; t < 10000; t++) {

				System.out.println();
				for (int x = 0; x < mat.length; x++) {
					System.out.println(Arrays.toString(mat[x]));
				}
				System.out.println();

				boolean[][] chk_tmp = new boolean[R][C];
				now = 0;
				for (int i = 0; i < mat.length; i++) {
					for (int j = 0; j < mat[i].length; j++) {

						if (mat[i][j] == 1) {
							now++;
							for (int d = 0; d < 4; d++) {
								// 공간이 존재하고, 공기와 노출된 곳이 있는경우
								if (0 <= i + di[d] && i + di[d] < chk.length && 0 <= j + dj[d]
										&& j + dj[d] < chk[0].length && chk[i + di[d]][j + dj[d]]) {
									mat[i][j] = 0; // 치즈는 녹고
									filling_bfs(chk_tmp, mat, i, j); // 공기 또한 통한다.
								}
							}
						} else {
							mat[i][j] = 0;
						}
					}
				}

				if (now == 0) {
					System.out.println(t);
					System.out.println(before);
					break loop;
				}
				chk = chk_tmp;
				before = now;
			}
		}
	}

	static void filling(boolean[][] chk, int[][] mat, int i, int j) {
		chk[i][j] = true;
		for (int d = 0; d < 4; d++) {
			if (0 <= i + di[d] && i + di[d] < chk.length && 0 <= j + dj[d] && j + dj[d] < chk[0].length
					&& mat[i + di[d]][j + dj[d]] == 0 && !chk[i + di[d]][j + dj[d]]) {
				filling(chk, mat, i + di[d], j + dj[d]);
			}
		}
	}

	static void filling_bfs(boolean[][] chk, int[][] mat, int i, int j) {
		chk[i][j] = true;
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { i, j });
		while (!q.isEmpty()) {
			int[] tmp = q.poll();
			for (int d = 0; d < 4; d++) {
				if (0 <= tmp[0] + di[d] && tmp[0] + di[d] < chk.length && 0 <= tmp[1] + dj[d] && tmp[1] + dj[d] < chk[0].length
						&& mat[tmp[0] + di[d]][tmp[1] + dj[d]] == 0 && !chk[tmp[0] + di[d]][tmp[1] + dj[d]]) {
					chk[tmp[0]+di[d]][tmp[1]+dj[d]] = true;
					q.offer(new int[] {tmp[0]+di[d],tmp[1]+dj[d]});
				}
			}
		}
	}
}
