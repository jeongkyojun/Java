import java.util.*;
import java.io.*;

public class Main {

	static int[] di = new int[] { -1, 1, 0, 0 };
	static int[] dj = new int[] { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int test = Integer.parseInt(bf.readLine());
		for (int tc = 1; tc <= test; tc++) {
			System.out.print("#" + tc + " ");

			StringTokenizer st = new StringTokenizer(bf.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int[][] mat = new int[n][m];
			int[][] chk = new int[n][m];
			int t = Integer.parseInt(st.nextToken());

			int res = 100001;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(bf.readLine());
				for (int j = 0; j < m; j++) {
					mat[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			Queue<int[]> q = new LinkedList<int[]>();
			q.offer(new int[] { 0, 0, 0 });
			chk[0][0] = 1;
			while (!q.isEmpty()) {
				int[] tmp = q.poll();
				for (int d = 0; d < 4; d++) {
					if (0 <= tmp[0] + di[d] && tmp[0] + di[d] < n && 0 <= tmp[1] + dj[d] && tmp[1] + dj[d] < m) {
						if (mat[tmp[0] + di[d]][tmp[1] + dj[d]] != 1 && (chk[tmp[0] + di[d]][tmp[1] + dj[d]] == 0
								|| chk[tmp[0] + di[d]][tmp[1] + dj[d]] > tmp[2] + 1)) {
							chk[tmp[0] + di[d]][tmp[1] + dj[d]] = tmp[2] + 1;
							if (tmp[0] + di[d] == n-1 && tmp[1] + dj[d] == m-1) {
								System.out.println("!!!");
								if (res > tmp[2] + 1) {
									res = tmp[2] + 1;
								}
							}
							if (mat[tmp[0] + di[d]][tmp[1] + dj[d]] == 2) {
								int len = Math.abs(tmp[0] + di[d] - (n - 1)) + Math.abs(tmp[1] + dj[d] - (m - 1));
								if (res > tmp[2] + 1 + len) {
									res = tmp[2] + 1 + len;
								}
							}
							q.offer(new int[] { tmp[0] + di[d], tmp[1] + dj[d], tmp[2] + 1 });
						}
					}
				}
			}
			for (int i = 0; i < n; i++) {
				System.out.println(Arrays.toString(chk[i]));
			}
			if (res <= t)
				System.out.println(res);
			else
				System.out.println("Fail");
		}
	}

}
