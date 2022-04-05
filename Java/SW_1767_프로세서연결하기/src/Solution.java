import java.util.*;
import java.io.*;

public class Solution {

	static int min;
	static int max;
	static int[] di = new int[] { -1, 1, 0, 0 }; // 상, 하, 좌, 우
	static int[] dj = new int[] { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_sw_1767.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			System.out.print("#" + test_case + " ");
			int N = Integer.parseInt(bf.readLine());

			int[][] mat = new int[N][N];
			int[][] processor = new int[145][];
			int pnum = 0;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for (int j = 0; j < N; j++) {
					mat[i][j] = Integer.parseInt(st.nextToken());
					if (mat[i][j] == 1) {
						processor[pnum++] = new int[] { i, j }; // 프로세서의 개수를 받는다.
						mat[i][j] = pnum;
					}
				}
			}

			max = -1;
			min = Integer.MAX_VALUE;
			process_perm(mat, processor, pnum, 0, 0, 0);
			System.out.println(min);
		}

	}

	static void process_perm(int[][] mat, int[][] processor, int n, int cnt, int res, int core_num) {		
		if (cnt == n) {
			if (core_num > max) {
				max = core_num;
				min = res;
			} else if (core_num == max) {
				if (res < min) {
					min = res;
				}
			}
			return;
		}
		// 프로세서 위치 확인
		int pi = processor[cnt][0];
		int pj = processor[cnt][1];
		// 프로세서가 그대로인경우 건너 뛴다.
		if (pi == 0 || pj == 0 || pi == mat.length - 1 || pj == mat[pi].length - 1) {
			process_perm(mat, processor, n, cnt + 1, res, core_num + 1);
			return;
		}
		int[][] tmp = new int[mat.length][mat[pi].length];
		dir: for (int d = 0; d < 4; d++) {
			int k = 0;
			int ni = pi;
			int nj = pj;
			// tmp 초기화
			for (int i = 0; i < mat.length; i++) {
				for (int j = 0; j < mat[0].length; j++) {
					tmp[i][j] = mat[i][j];
				}
			}
			// 방향 지정
			while (0 <= ni + di[d] && ni + di[d] < mat.length && 0 <= nj + dj[d] && nj + dj[d] < mat[0].length) {
				if (mat[ni + di[d]][nj + dj[d]] != 0) {
					continue dir;
				}
				tmp[ni + di[d]][nj + dj[d]] = cnt + 1;
				ni += di[d];
				nj += dj[d];
				k++;
			}
			process_perm(tmp, processor, n, cnt + 1, res + k, core_num + 1);
		}
		process_perm(mat, processor, n, cnt + 1, res, core_num);
	}
}
