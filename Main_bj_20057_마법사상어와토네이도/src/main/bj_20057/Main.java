package main.bj_20057;

import java.util.*;
import java.io.*;

public class Main {

	static int[] di = new int[] { 0, 1, 0, -1, 0 }; // 좌 하 우 상
	static int[] dj = new int[] { -1, 0, 1, 0, 0 };

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_20057.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine()); ////////////
		for(int TC = 1; TC<=T;TC++)//////////////////////////
		{////////////////////////////////////////////////////
			System.out.print("#"+TC+" ");////////////////////
			
			int N = Integer.parseInt(bf.readLine());
			int[][] map = new int[N][N]; // 모래가 남아있는 정도
			
			// 차례대로 보는 방향 기준 {정면, 측면 1, 측면 2, 후면, 제자리}를 의미
			int[][] dir = new int[][] { //[보는 방향][보는 방향 기준 블록 위치]
				{0,1,3,2,4}, 	// 좌측인경우 좌 하 상 우 0
				{1,2,0,3,4},	// 하단인경우 하 우 좌 상 0
				{2,3,1,0,4}, 	// 우측인 경우 우 상 하 좌 0
				{3,2,0,1,4} 	// 위로 갈경우 상 우 좌 하 0
			};
			int[][] carry = new int[][] { // [경우의수][0 : 움직임1, 1 : 움직임 2, 2 : 나눠야되는 것, 3 : 곱해야되는것]
				{0,0,20,1},// 정면 + 정면 = 5% ->(0, 0)
				{0,1,10,1},{0,2,10,1},// 정면 + 측면 = 10% -> (0, 1) & (0, 2)
				{1,1,50,1},{2,2,50,1},// 측면 + 측면 = 2% -> (1,1) & (2,2)
				{1,4,100,7},{2,4,100,7},// 측면 + 0 = 7% -> (1,5) & (2,5)
				{1,3,100,1},{2,3,100,1}// 측면 + 후면 = 1% -> (1,3) & (2,3)	
			};
			// 맵에 모래 배치
			for(int i = 0; i<N;i++)
			{
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for(int j=0;j<N;j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			// 5 ->(2,2) 7->(3,3)
			int[] start = new int[] {N/2,N/2};
			int next_i = start[0];
			int next_j = start[1];
			int set = 1;
			int d = 0;
			int res = 0;
			while(next_i!=0||next_j!=0)
			{
				// 범위 안에 속할 때
				if(start[0]-set<=next_i+di[d%4]&&next_i+di[d%4]<=start[0]+set&&
						start[1]-set<=next_j+dj[d%4]&&next_j+dj[d%4]<=start[1]+set)
				{
					next_i+=di[d%4];
					next_j+=dj[d%4];
					int sand = map[next_i][next_j];
					map[next_i][next_j] = 0; // 모래를 옮긴다.
					
					int sum = 0;
					for(int x=0;x<carry.length;x++)
					{
						int move_sand = sand*carry[x][3]/carry[x][2];
						sum+=move_sand;
						int move_i = next_i+di[dir[d%4][carry[x][0]]]+di[dir[d%4][carry[x][1]]];
						int move_j = next_j+dj[dir[d%4][carry[x][0]]]+dj[dir[d%4][carry[x][1]]];
						if(0<=move_i&&move_i<N&&0<=move_j&&move_j<N)
						{
							map[move_i][move_j] += move_sand;
						}
						else
						{
							res+=move_sand;
						}
					}
					int move_sand = sand-sum;
					// 나머지 : 현재 블록 기준 바로 앞 = 정면
					int move_i = next_i+di[dir[d%4][0]];
					int move_j = next_j+dj[dir[d%4][0]];
					// 정면이 격자 안에 있는경우 map에 값을 넣고, 격자 밖에 있으면 res에 더한다.
					if(0<=move_i&&move_i<N&&0<=move_j&&move_j<N)
					{
						map[move_i][move_j] += move_sand;
					}
					else
					{
						res+=move_sand;
					}
				}
				else
				{
					d++;
					if(d%4==0)
						set++;
				}
				
				/*System.out.println("set : "+set+" , dir : "+d);
				for(int i=0;i<N;i++)
				{
					System.out.println(Arrays.toString(map[i]));
				}
				System.out.println();*/
			}
			System.out.println(res);
		}///////////////////////////////////////////////////
	}
}
