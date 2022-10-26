import java.util.*;
import java.io.*;

public class Main {

	static int res;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_15684.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		for(int test_case=1;test_case<=T;test_case++)
		{
			// 행과 열을, 탐색해서 둘 수 있는 위치를 true라고 할 때,
			// 값을 subset을 이용해서 3회까지의 경우의수를 구한다.
			// mat에는 각 depth마다 위치하는 수
			// 선을 그을때, 해당 mat의 두 값을 구해 최종 값에서 위치를 바꿔준다.
			
			//세로선 개수 N, 가로선 개수 M
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken()); // 세로선 개수
			int M = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken()); // 놓을수 있는 위치의 개수
			
			System.out.println("N : "+N+" , M : "+M+" , H : "+H);
			int[][] mat = new int[H+1][N];
			boolean[][] ladder = new boolean[H][N];
			boolean[][] ladder_set = new boolean[H][N];
			boolean[][] chk = new boolean[N][N];
			int[] ladder_pos = new int[N];
			
			// 사다리 위치 설정
			for(int i=0;i<N;i++)
			{
				mat[0][i] = i+1;
			}
			//System.out.println(Arrays.toString(mat[0]));
			
			for(int i=0;i<M;i++)
			{
				st = new StringTokenizer(bf.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				ladder[r-1][c-1] = true;
				
			}
			
			for(int i=0;i<H;i++)
			{
				for(int j=0;j<N;j++)
				{
					if(ladder[i][j])
					{
						ladder_set[i][j] = true;
						ladder_set[i][j+1] = true;
					}
					if(j==N-1)
						ladder_set[i][j] = true;
					
					if(ladder[i][j])
					{
						mat[i+1][j] = mat[i][j+1];
						mat[i+1][j+1] = mat[i][j];
						j++;
					}
					else
					{
						mat[i+1][j] = mat[i][j];
					}		
				}
				//System.out.println(Arrays.toString(mat[i]));
				//System.out.println(Arrays.toString(ladder_set[i]));
			}
			for(int i=0;i<N;i++)
			{
				ladder_pos[mat[H][i]-1] = i;
			}
			//System.out.println("position");
			//System.out.println(Arrays.toString(ladder_pos));
			boolean find = true;
			res = -1;
			for(int i=0;i<N;i++)
			{
				if(ladder_pos[i] != i)
				{
					find = false;
					break;
				}
			}
			if(find)
				System.out.println("0");
			else
			{
				ladder_subset(mat, ladder_pos, ladder_set,0,0,0,chk);
				System.out.println(res);
			}
		}

	}
	static void ladder_subset(int[][] mat, int[] ladder_pos, boolean[][] ladder_set , int cnt,int i, int j,boolean[][] chk)
	{
		if(res!=-1&&res<cnt) // 이미 값이 정해져 있고, 더 적은 값이 정답이면 pass
			return;
		
		if(cnt==3)
			return;
		/*
		 * 사다리를 둘 수 있는 위치를 찾고, 해당 위치의 두 수를 구한뒤 이미 바꾼적 있는지 체크,
		 * 체크했었으면 pass, 아니면 두 수를 바꾼뒤 검증하고 넘긴다.
		 */
		if(!ladder_set[i][j]) // 사다리를 놓을 수 있는 위치
		{
			int a = mat[i][j];
			int b = mat[i][j+1];
			if(chk[a-1][b-1])
			{
				System.out.println("chk"+a+" , "+b);
				chk[a-1][b-1] = true;
				chk[b-1][a-1] = true;
				/*
				// tmp 생성
				int[] ladder_tmp = new int[ladder_pos.length];
				for(int n=0;n<ladder_pos.length;n++)
				{
					ladder_tmp[n] = ladder_pos[n];
				}
				ladder_tmp[a-1] = ladder_pos[b-1];
				ladder_tmp[b-1] = ladder_pos[a-1];
				*/
				if(j==mat[i].length-1 && i<mat.length) // j가 끝까지 가면 다음 i위치로
					ladder_subset(mat,ladder_pos,ladder_set ,cnt+1,i+1,0,chk);
				else if(j<mat[i].length)
					ladder_subset(mat,ladder_pos,ladder_set ,cnt+1,i,j+1,chk);
				
				chk[a-1][b-1] = false;
				chk[b-1][a-1] = false;
			}
		}
		// 다음 위치로 이동
		if(j==mat[i].length-1 && i<mat.length) // j가 끝까지 가면 다음 i위치로
			ladder_subset(mat,ladder_pos,ladder_set ,cnt,i+1,0,chk);
		else if(j<mat[i].length)
			ladder_subset(mat,ladder_pos,ladder_set ,cnt,i,j+1,chk);
	}

}
