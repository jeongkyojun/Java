package main.bj_19236;

import java.util.*;
import java.io.*;

public class Main {

	static int[] di = new int[] { -1, -1, 0, 1, 1, 1, 0, -1 }; // 상, 좌상, 좌, 좌하, 하, 우하, 우, 우상
	static int[] dj = new int[] { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int res;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_19236.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			System.out.print("#" + test_case + " ");

			int[][] sea = new int[4][4];
			int[][] dir = new int[4][4];
			int[] shark = new int[] { 0, 0 };
			int[][] point = new int[17][];

			point[0] = new int[] { 0, 0 };
			for (int i = 0; i < 4; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for (int j = 0; j < 4; j++) {
					sea[i][j] = Integer.parseInt(st.nextToken());
					dir[i][j] = Integer.parseInt(st.nextToken()) - 1;
					point[sea[i][j]] = new int[] { i, j }; // 상어 위치에 따른 값 저장
				}
			}

			res = -1;

			int[][] tmp = new int[4][4];
			int[][] tmp_d = new int[4][4];
			int[][] tmp_p = new int[17][2];

			for (int a = 0; a < 4; a++) {
				for (int b = 0; b < 4; b++) {
					tmp[a][b] = sea[a][b];
					tmp_d[a][b] = dir[a][b];
				}
			}
			for (int a = 0; a < 17; a++) {
				for (int b = 0; b < 2; b++) {
					tmp_p[a][b] = point[a][b];
				}
			}
			int eat = 0;
			Eat(tmp, tmp_d, tmp_p, new int[] { 0, 0 }, eat, tmp_d[0][0]);

			System.out.println(res);
		}
	}

	static void Eat(int[][] sea, int[][] dir, int[][] point, int[] shark, int eat, int go) {
		int[][] tmp = new int[4][4];
		int[][] tmp_d = new int[4][4];
		int[][] tmp_p = new int[17][2];

		// System.out.println("cnt : "+c);
		// 0은 빈공간, -1 은 상어

		// 상어가 잡아먹고, 위치를 정의한다.
		if (dir[shark[0]][shark[1]] == -1) // 비어있는곳인 경우
			dir[shark[0]][shark[1]] = go; // 상어의 방향을 가져온다.
		eat += sea[shark[0]][shark[1]]; // 초기값 설정됨
		point[sea[shark[0]][shark[1]]][0] = -1; // 물고기 위치를 삭제
		sea[shark[0]][shark[1]] = -1; // 상어가 있는곳은 -1

		if (res < eat)
			res = eat;
		/*
		 * System.out.println("start"); for(int a=0;a<4;a++) {
		 * System.out.println(Arrays.toString(sea[a])+"\t"+Arrays.toString(dir[a])); }
		 * System.out.println("dir : "+go);
		 */
		int fish_num = 0;
		for (int i = 1; i < point.length; i++) {
			if (point[i][0] == -1)
				continue;
			fish_num++;
			int d = dir[point[i][0]][point[i][1]];
			int n_i = point[i][0];
			int n_j = point[i][1];
			int cnt = 0;
			while (n_i + di[d] < 0 || n_i + di[d] >= 4 || n_j + dj[d] < 0 || n_j + dj[d] >= 4
					|| sea[n_i + di[d]][n_j + dj[d]] == -1) {
				// 1. 칸이 존재하고
				// 2. 해당 칸에 상어가 없는경우
				d++;
				d = d % 8;
				cnt++;
				if (cnt == 9) {
					System.out.println("ERROR");
					System.exit(1);
					;
				}
			}

			int next_i = n_i + di[d];
			int next_j = n_j + dj[d];

			// 물고기 위치 교환
			int t = sea[n_i][n_j];
			sea[n_i][n_j] = sea[next_i][next_j];
			sea[next_i][next_j] = t;

			// 위치 정보 갱신
			if (sea[n_i][n_j] > 0) {
				point[sea[n_i][n_j]] = new int[] { n_i, n_j };
			}
			point[i][0] = next_i;
			point[i][1] = next_j;

			// 방위 교환
			dir[n_i][n_j] = dir[next_i][next_j];
			dir[next_i][next_j] = d;
		}

		if (fish_num == 0) // 물고기를 모두 잡아먹었으면 return
			return;

		// 변화 확인
		/*
		 * System.out.println(); for(int a=0;a<4;a++) {
		 * System.out.println(Arrays.toString(sea[a])); } System.out.println();
		 */

		// 상어 이동
		int d = dir[shark[0]][shark[1]];
		int n_i = shark[0];
		int n_j = shark[1];
		sea[n_i][n_j] = 0;// 현재 위치는 빈공간으로 변경
		dir[n_i][n_j] = -1;
		int cnt = 0;
		while (0 <= n_i + di[d] && n_i + di[d] < 4 && 0 <= n_j + dj[d] && n_j + dj[d] < 4) // 칸이 존재하면 반복
		{
			if(sea[n_i+di[d]][n_j+dj[d]]==0)
			{
				n_i += di[d];
				n_j += dj[d];
				continue;
			}
			// System.out.println("?>");
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					tmp[i][j] = sea[i][j];
					tmp_d[i][j] = dir[i][j];
				}
			}

			for (int i = 0; i < 17; i++) {
				for (int j = 0; j < 2; j++) {
					tmp_p[i][j] = point[i][j];
				}
			}

			Eat(tmp, tmp_d, tmp_p, new int[] { n_i + di[d], n_j + dj[d] }, eat, d);

			n_i += di[d];
			n_j += dj[d];
		}
		// 호출이 끝났다 = 이제 집에 갔다로 생각하여 종료, 이전까지 먹은 생선의 크기는 이미 정의 했으니 끝내기만 하면 된다.
	}
}
