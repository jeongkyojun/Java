package main.bj_21608;

// 상어초등학교
import java.util.*;
import java.io.*;

public class Main {

	static int[] di = new int[] { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] dj = new int[] { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_21608.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());

		for (int test_case = 1; test_case <= T; test_case++) ////
		{ //////////////////////////////////////////////////
			System.out.print("#"+test_case+" ");
			
			int N = Integer.parseInt(bf.readLine());
			int NN = (int) Math.pow(N, 2);
			int[][][] mat = new int[N][N][4]; // 좌표 (a,b)에 인접한 학생의 수
			int[][][] like_list = new int[N][N][4]; // board[i][j] 가 좋아하는 학생의 목록
			int[][] sit = new int[N][N]; // 주변 자리 숫자
			int[][] board = new int[N][N]; // 번호
			int[][] like = new int[N][N]; // 짝꿍이 존재하는 숫자
			int[] n = new int[4]; // 현재 학생이 선호하는 짝꿍
			
			// 인접한 빈 공간 확인
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sit[i][j] = 4;
					if (i == 0)
						sit[i][j]--;
					if (j == 0)
						sit[i][j]--;
					if (i == N - 1)
						sit[i][j]--;
					if (j == N - 1)
						sit[i][j]--;
				}
			}
			int res = 0;
			for (int std = 0; std < NN; std++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				int r = Integer.parseInt(st.nextToken()); // 해당 학생의 번호
				/*
				 * 1. 선호하는짝꿍이 있는 자리 중 가장 여유있는 자리를 선택한다 
				 * 2. 없거나 같으면 비어있는칸이 가장 많은 자리를 선택한다 
				 * 3. 행이 가장 작고(i), 같으면 열이 작은 자리(j)를 선택한다
				 */
				for (int i = 0; i < 4; i++) {
					n[i] = Integer.parseInt(st.nextToken());
				}
				boolean isMate = false; // 인접한 행렬이 있는지를 확인
				int like_max = 0;
				int max = -1; // 빈공간중 최댓값을 확인
				int[] point = new int[2];
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if(board[i][j]!=0)continue; // 0이 아니다 = 학생이 앉아있다
						boolean find = false;
						int l = 0;
						
						for (int x = 0; x < 4; x++) // 자리가 존재하는지, 짝꿍이 몇명이나 있는지 확인
						{
							for (int y = 0; y < 4; y++) {
								if (mat[i][j][x] == n[y]) {
									find = true;
									l++;
								}
							}
						}
						
						if (find) // 찾은경우 가장 짝꿍 수가 큰 경우를 따짐
						{
							if (isMate) // 최초가 아닌경우 짝꿍 수를 확인
							{
								if (like_max < l) {
									like_max = l;
									max = sit[i][j];
									point[0] = i;
									point[1] = j;
								}
								else if(like_max==l) // 같은경우 max로 따진다.
								{
									if (max < sit[i][j]) {
										max = sit[i][j];
										point[0] = i;
										point[1] = j;
									}
								}
							} else // 최초인경우 그대로 덮어씀
							{
								isMate = true;
								max = sit[i][j];
								like_max = l;
								point[0] = i;
								point[1] = j;
							}
						} else // 아닌경우 지금까지 못찾은경우는 빈공간이 큰지만 확인하고, 찾았으면 그냥 pass
						{
							if (!isMate) {
								if (max < sit[i][j]) {
									max = sit[i][j];
									point[0] = i;
									point[1] = j;
								}
							}
						}
					}
				}

				// 이제 값을 씌운다
				board[point[0]][point[1]] = r; // 존재
				like[point[0]][point[1]] = like_max; // 짝꿍이 존재하는 수
				like_list[point[0]][point[1]] = Arrays.copyOf(n,n.length);
				// 1. 해당지점 상하좌우에 자리를 저장한다
				for (int i = 0; i < 4; i++) {
					// 범위가 정상범위에 있는경우
					if (0 <= point[0] + di[i] && point[0] + di[i] < N && 0 <= point[1] + dj[i] && point[1] + dj[i] < N) {
						// 네칸중에 0이 먼저 나오는경우
						for (int j = 0; j < 4; j++)
						{
							if (mat[point[0] + di[i]][point[1] + dj[i]][j] == 0)
							{
								mat[point[0] + di[i]][point[1] + dj[i]][j] = r;
								break; // 입력하고 종료
							}
						}
						// 해당 칸의 값이 현재값의 짝꿍값인 경우 like_max+1;
						for(int j=0;j<4;j++)
						{
							if(like_list[point[0]+di[i]][point[1]+dj[i]][j]==board[point[0]][point[1]])
							{
								like[point[0]+di[i]][point[1]+dj[i]]++;
							}
						}
						sit[point[0]+di[i]][point[1]+dj[i]]--;// 주변 인접자리를 1씩 뺀다.
					}
				}
				
				System.out.println();
				for(int i=0;i<N;i++)
				{
					System.out.println(Arrays.toString(board[i]));
				}
			}
			
			for(int i=0;i<N;i++)
			{
				for(int j=0;j<N;j++)
					res+=Math.pow(10, like[i][j])/10;
			}
			System.out.println(res);

		} // ///////////////////////////////////////////////

	}

}
