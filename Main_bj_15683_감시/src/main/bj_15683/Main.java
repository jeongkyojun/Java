package main.bj_15683;

import java.util.*;
import java.io.*;

public class Main {

	static int[] di = new int[] { -1, 0, 1, 0 }; // 상 좌 하 우
	static int[] dj = new int[] { 0, -1, 0, 1 };
	static int min;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_15683.txt"));

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			System.out.print("#" + test_case + " ");

			min = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());

			int[][] mat = new int[R][C];
			int[][] camera = new int[8][3]; // i,j, 종류
			int cnt = 0;
			int res = 0;
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(bf.readLine(), " ");
				for (int j = 0; j < C; j++) {
					mat[i][j] = Integer.parseInt(st.nextToken());
					if (mat[i][j] == 6) { // 벽은 -1로 바꾼다.
						mat[i][j] = -1;
					} else if (mat[i][j] != 0) {
						// 감시 카메라의 지점을 저장
						camera[cnt++] = new int[] { i, j, mat[i][j] };
						mat[i][j] = cnt;
					} else {
						res++;
					}
				}
			}
			for (int c = 0; c < cnt; c++) {
				// 5번인경우, 4번인경우는 십자로 칠해놓는다
				if (camera[c][2] == 5) {
					for (int d = 0; d < 4; d++) {
						int c_i = camera[c][0] + di[d];
						int c_j = camera[c][1] + dj[d];
						while (0 <= c_i && c_i < R && 0 <= c_j && c_j < C) {
							if (mat[c_i][c_j] == -1) // 벽에 막히면 종료한다.
								break;
							if (mat[c_i][c_j] == 0) {
								mat[c_i][c_j] = c+1;
								res--;
							}
							c_i += di[d];
							c_j += dj[d];
						}
					}
				}
			}
			
			int[] v = new int[cnt];
			Search(mat, camera, v, res, 0, cnt);
			System.out.println(min);
		}
	}

	static void Search(int[][] mat, int[][] camera, int[] cnt, int res, int cam, int max_cam) {
		if (cam == max_cam) {
			int[][] tmp = new int[mat.length][mat[0].length];
			for (int i = 0; i < mat.length; i++) {
				for (int j = 0; j < mat[0].length; j++) {
					tmp[i][j] = mat[i][j];
				}
			}
			
			for (int i = 0; i < max_cam; i++) {
				int dir = cnt[i]; // 방향 정하기
				int next_i = camera[i][0] + di[dir];
				int next_j = camera[i][1] + dj[dir];
				switch (camera[i][2])// 카메라 종류
				{
				// 한쪽을 감시한다
				case 1:
					while (0 <= next_i && next_i < mat.length && 0 <= next_j && next_j < mat[0].length) {
						if (tmp[next_i][next_j] == -1)
							break;
						if (tmp[next_i][next_j] == 0) {
							tmp[next_i][next_j] = i + 1;
							res--;
						}
						next_i += di[dir];
						next_j += dj[dir];
					}
					break;
				case 2:
					while (0 <= next_i && next_i < mat.length && 0 <= next_j && next_j < mat[0].length) {
						if (tmp[next_i][next_j] == -1)
							break;
						if (tmp[next_i][next_j] == 0) {
							tmp[next_i][next_j] = i + 1;
							res--;
						}
						next_i += di[dir];
						next_j += dj[dir];
					}
					next_i = camera[i][0] + di[dir + 2];
					next_j = camera[i][1] + dj[dir + 2];
					while (0 <= next_i && next_i < mat.length && 0 <= next_j && next_j < mat[0].length) {
						// 양측 확인
						if (tmp[next_i][next_j] == -1)
							break;
						if (tmp[next_i][next_j] == 0) {
							tmp[next_i][next_j] = i + 1;
							res--;
						}
						next_i += di[dir + 2];
						next_j += dj[dir + 2];
					}
					break;
				case 3:
					while (0 <= next_i && next_i < mat.length && 0 <= next_j && next_j < mat[0].length) {
						if (tmp[next_i][next_j] == -1)
							break;
						if (tmp[next_i][next_j] == 0) {
							tmp[next_i][next_j] = i + 1;
							res--;
						}
						next_i += di[dir];
						next_j += dj[dir];
					}
					next_i = camera[i][0] + di[(dir + 1) % 4];
					next_j = camera[i][1] + dj[(dir + 1) % 4];
					while (0 <= next_i && next_i < mat.length && 0 <= next_j && next_j < mat[0].length) {
						// 직각
						if (tmp[next_i][next_j] == -1)
							break;
						if (tmp[next_i][next_j] == 0) {
							tmp[next_i][next_j] = i + 1;
							res--;
						}
						next_i += di[(dir + 1) % 4];
						next_j += dj[(dir + 1) % 4];
					}
					break;
				case 4:
					// 감시하는곳을 지우기
					while (0 <= next_i && next_i < mat.length && 0 <= next_j && next_j < mat[0].length) {
						if (tmp[next_i][next_j] == -1)
							break;
						if (tmp[next_i][next_j] == 0) {
							tmp[next_i][next_j] = i + 1;
							res--;
						}
						next_i += di[dir];
						next_j += dj[dir];
					}
					next_i = camera[i][0] + di[(dir + 1) % 4];
					next_j = camera[i][1] + dj[(dir + 1) % 4];
					while (0 <= next_i && next_i < mat.length && 0 <= next_j && next_j < mat[0].length) {
						// 직각
						if (tmp[next_i][next_j] == -1)
							break;
						if (tmp[next_i][next_j] == 0) {
							tmp[next_i][next_j] = i + 1;
							res--;
						}
						next_i += di[(dir + 1) % 4];
						next_j += dj[(dir + 1) % 4];
					}
					next_i = camera[i][0] + di[(dir + 2) % 4];
					next_j = camera[i][1] + dj[(dir + 2) % 4];
					while (0 <= next_i && next_i < mat.length && 0 <= next_j && next_j < mat[0].length) {
						// 직각
						if (tmp[next_i][next_j] == -1)
							break;
						if (tmp[next_i][next_j] == 0) {
							tmp[next_i][next_j] = i + 1;
							res--;
						}
						next_i += di[(dir + 2) % 4];
						next_j += dj[(dir + 2) % 4];
					}
					break;

				}
			}
			/*System.out.println("res : "+res);
			for(int i=0;i<mat.length;i++)
			{
				System.out.println(Arrays.toString(tmp[i]));
			}
			System.out.println();*/
			
			if (min > res) {				
				min = res;
			}
			return;
		} else {
			// 카메라 종류에 따라 칠한다.
			switch (camera[cam][2]) {
			case 1: // 카메라 번호가 1번인경우
				// 1,2,3,4
				for (int i = 0; i < 4; i++) {
					cnt[cam] = i;
					Search(mat, camera, cnt, res, cam + 1, max_cam);
				}
				break;
			case 2:
				// 1,2
				for (int i = 0; i < 2; i++) {
					cnt[cam] = i;
					Search(mat, camera, cnt, res, cam + 1, max_cam);
				}
				break;
			case 3:
				// 1,2,3,4
				for (int i = 0; i < 4; i++) {
					cnt[cam] = i;
					Search(mat, camera, cnt, res, cam + 1, max_cam);
				}
				break;
			case 4:
				// 1,2,3,4
				for (int i = 0; i < 4; i++) {
					cnt[cam] = i;
					Search(mat, camera, cnt, res, cam + 1, max_cam);
				}
				break;
			case 5:
				// 1
				cnt[cam] = 1;
				Search(mat, camera, cnt, res, cam + 1, max_cam);
				break;
			}
		}
	}
}
