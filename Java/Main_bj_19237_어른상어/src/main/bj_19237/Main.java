package main.bj_19237;

import java.util.*;
import java.io.*;
import java.lang.reflect.Array;

public class Main {

	static int[] di = { 0, -1, 1, 0, 0 }; // 제자리, 위, 아래, 왼쪽, 오른쪽
	static int[] dj = { 0, 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_19237.txt"));

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			System.out.println("#" + test_case + " ");

			StringTokenizer st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken()); // 격자의 크기
			int M = Integer.parseInt(st.nextToken()); // 상어의 수
			int k = Integer.parseInt(st.nextToken()); // 냄새가 사라지는 시간

			int shark_num = M;
			int[][] sea = new int[N][N]; // 상어의 위치를 표시
			int[][][] smell = new int[N][N][2]; // [i][j][냄새를 표시 0 : 냄새의 주인, 1 : 냄새가 남는 시간]
			int[][] shark = new int[M + 1][2]; // 상어의 위치
			int[] dir = new int[M + 1]; // 상어의 보는방향
			int[][][] move = new int[M + 1][5][5]; // 바라보는 방향별 선호도 [상어번호][바라보는방향][선호도]

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine());
				for (int j = 0; j < N; j++) {
					sea[i][j] = Integer.parseInt(st.nextToken());
					if (sea[i][j] != 0) {
						// 상어의 위치 저장
						smell[i][j][0] = sea[i][j];
						smell[i][j][1] = k;
						shark[sea[i][j]][0] = i;
						shark[sea[i][j]][1] = j;
					}
				}
			}
			// 바라보는 방향 저장
			st = new StringTokenizer(bf.readLine());
			for (int j = 1; j <= M; j++) {
				dir[j] = Integer.parseInt(st.nextToken());
			}

			for (int i = 1; i <= M; i++) {
				for (int j = 1; j <= 4; j++) {
					st = new StringTokenizer(bf.readLine());
					for (int l = 1; l <= 4; l++) {
						move[i][j][l] = Integer.parseInt(st.nextToken());
					}
				}
			}
			int time = 0;

			/*for (int i = 1; i <= M; i++) {
				for (int j = 1; j <= 4; j++)
					System.out.print(Arrays.toString(move[i][j]) + " ");
				System.out.println();
			}*/

			// 한마리만 남을때까지 계속한다.
			while(true){
				/*System.out.println("time : " + time);
				System.out.println("dir : " + Arrays.toString(dir));
				for (int i = 0; i < N; i++)
				{
					System.out.print(Arrays.toString(sea[i])+"\t");
					for(int j=0;j<N;j++)
						System.out.print(smell[i][j][0]+" ");
					System.out.println();
				}
				System.out.println();*/
				if (shark_num == 1) {
					break;
				}
				if (time > 1000) {
					time = -1;
					break;
				}
				for (int i = 1; i <= M; i++) {
					sea[shark[i][0]][shark[i][1]] = 0; // 현재위치에서 지운다
				}

				// 상어들을 이동시킨다.
				move: for (int i = 1; i <= M; i++) {
					int s_i = shark[i][0];
					int s_j = shark[i][1];

					// 만약 쫓겨났으면 pass
					if (dir[i] == -1)
						continue;

					for (int j = 1; j < 5; j++) {
						int next_i = s_i + di[move[i][dir[i]][j]];
						int next_j = s_j + dj[move[i][dir[i]][j]];
						// 우선 냄새가 없는 칸이 있는지 탐색한다.
						// 칸이 존재하고
						if (0 <= next_i && next_i < N && 0 <= next_j && next_j < N) {
							// 냄새가 없으면
							if (smell[next_i][next_j][0] == 0) {
								// 나보다 큰 상어가 없을경우
								if (sea[next_i][next_j] == 0) {
									//System.out.println("shark " + i + " move " + move[i][dir[i]][j] + "(1)");
									sea[next_i][next_j] = i;// 이동한다

									shark[i][0] = next_i; // 위치 변경
									shark[i][1] = next_j;

									dir[i] = move[i][dir[i]][j]; // 보는 방향 변경
									continue move; // 다음으로 넘어간다.
								} else // 그 자리에 나보다 큰 상어가 있으면
								{
									// 쫓겨난다
									dir[i] = -1;
									shark_num--; // 상어의 숫자는 줄어든다.
									continue move;
								}
							}
						}
					}
					// 자신의 냄새가 있는 곳으로 이동
					for (int j = 1; j < 5; j++) {
						int next_i = s_i + di[move[i][dir[i]][j]];
						int next_j = s_j + dj[move[i][dir[i]][j]];
						// 우선 냄새가 없는 칸이 있는지 탐색한다.
						// 칸이 존재하고
						if (0 <= next_i && next_i < N && 0 <= next_j && next_j < N) {
							// 본인의 냄새가 있는경우
							if (smell[next_i][next_j][0] == i) {
								//System.out.println("shark " + i + " move " + move[i][dir[i]][j] + "(2)");
								// 본인 냄새가 나는 바다에 다른 상어가 있을 수 없다
								sea[next_i][next_j] = i; // 이동한다

								shark[i][0] = next_i; // 위치 변경
								shark[i][1] = next_j;

								dir[i] = dir[i] = move[i][dir[i]][j];
								; // 보는 방향 변경
								continue move; // 다음으로 넘어간다.
							}
						}
					}
				}

				// 냄새가 하나씩 빠진다.
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (sea[i][j] == 0) {
							if (smell[i][j][0] != 0) {
								smell[i][j][1]--;
								if (smell[i][j][1] <= 0) {
									smell[i][j][0] = 0;
								}
							}
						} else {
							smell[i][j] = new int[] { sea[i][j], k }; // 냄새를 남긴다.
						}
					}
				}
				/*
				 * 상어는 자신의 위치에 냄새를 뿌린다 1초마다 이동하고 냄새는 k초 후에 사라진다.
				 * 
				 * 1. 아무 냄새가 없는 방으로 이동한다 2. 자신의 냄새가 있는 칸으로 이동한다. 3. 한 칸에 여러마리의 상어가 있으면 한마리 빼고
				 * 쫓겨난다.
				 */

				time++; // 시간은 늘어난다.
			}

			System.out.println(time);
		}
	}

}
