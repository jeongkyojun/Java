import java.util.*;
import java.io.*;


public class Main {

	static int[] di = new int[] {0,0,-1,-1,-1,0,1,1,1};//좌, 좌상, 상, 우상, 우, 우하, 하, 좌하
	static int[] dj = new int[] {0,-1,-1,0,1,1,1,0,-1};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_21610.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		for(int test_case=1;test_case<=T;test_case++)
		{
			
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken()); // 배열 크기
			int M = Integer.parseInt(st.nextToken());
			
			int[][] mat = new int[N+1][N+1];
			int[][] cp = new int[2501][2];
			int cp_cnt = 0;
			for(int i=1;i<=N;i++)
			{
				st = new StringTokenizer(bf.readLine());
				for(int j=1;j<=N;j++)
				{
					mat[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0;i<M;i++)
			{
				st = new StringTokenizer(bf.readLine());
				int d = Integer.parseInt(st.nextToken()); // 방향
				int s = Integer.parseInt(st.nextToken()); // 이동하는 칸
				
				///// 여기서부터 코드
				// 0. (N,1) (N,2) (N-1,1) (N-1,2) 4방향으로 구름생성				
				cp[cp_cnt++] = new int[] {N,1};
				cp[cp_cnt++] = new int[] {N,2};
				cp[cp_cnt++] = new int[] {N-1,1};
				cp[cp_cnt++] = new int[] {N-1,2};
				
				
				// 1. 모든 구름이 d방향으로 s칸 이동
				// 2. 해당 지역에 물을 뿌린다 
				// 3. 구름이 모두 사라진다
				for(int a=0;a<cp_cnt;a++)
				{
					int c_i=cp[a][0];
					int c_j=cp[a][1];
					// 8방탐색을 이용해 이동 구현
					
					
					mat[c_i][c_j]++;
				}
				cp_cnt = 0;
				// 4. 물복사버그를 사용한다.
				
				
				// 5. 양이 2 이상인 칸에 구름이 생긴다.
				for(int a=1;a<=N;a++)
				{
					for(int b=1;b<=N;b++)
					{
						if(mat[a][b]>=2)
						{
							cp[cp_cnt++] = new int[] {a,b};
						}
					}
				}
				/////
			}
		}
		
	}

}
