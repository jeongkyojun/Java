import java.util.*;
import java.io.*;

public class Main {

	static int[] di = new int[] { 1, 0, 0, 0, 0, -1 };
	static int[] dj = new int[] { 0, 1, 0, 0, -1, 0 };
	static int[] dk = new int[] { 0, 0, 1, -1, 0, 0 };
	static int nums = 0;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_7569.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		for (int tc = 1; tc <= T; tc++) {
			System.out.print("#" + tc + " ");

			StringTokenizer st = new StringTokenizer(bf.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken());
			int[][][] box = new int[N][M][H];
			Queue<int[]> q = new LinkedList<int[]>();
			nums = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < N; j++) {
					st = new StringTokenizer(bf.readLine());
					for (int k = 0; k < M; k++) {
						box[j][k][i] = Integer.parseInt(st.nextToken());
						if (box[j][k][i] == 0) {
							nums++; // 안익은 토마토 개수 출력
						}
						if (box[j][k][i] == 1) {
							q.offer(new int[] { j, k, i });
						}
					}
				}
			}

			int day = 0;
			int bef = nums;
			while (nums > 0) {
				bef = nums;
				day++;
				int size = q.size();
				for (int i = 0; i < size; i++) {
					int[] tmp = q.poll();
					for (int d = 0; d < 6; d++) {
						if (0 <= tmp[0] + di[d] && tmp[0] + di[d] < box.length && 0 <= tmp[1] + dj[d]
								&& tmp[1] + dj[d] < box[0].length && 0 <= tmp[2] + dk[d]
								&& tmp[2] + dk[d] < box[0][0].length) {
							if (box[tmp[0] + di[d]][tmp[1] + dj[d]][tmp[2] + dk[d]] == 0) {
								nums--;
								box[tmp[0] + di[d]][tmp[1] + dj[d]][tmp[2] + dk[d]] = 1;
								q.offer(new int[] { tmp[0] + di[d], tmp[1] + dj[d], tmp[2] + dk[d] });
							}
						}
					}
				}
				if (bef == nums) {
					day = -1;
					break;
				}
			}
			System.out.println(day);
		}
	}
}
