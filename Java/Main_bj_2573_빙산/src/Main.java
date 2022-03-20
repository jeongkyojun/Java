import java.util.*;
import java.io.*;

public class Main {

	static int[] di = new int[] { -1, 0, 1, 0 }; // 상, 우, 하, 좌
	static int[] dj = new int[] { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2573.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] mat = new int[N][M];
		
		int i_min = N;
		int j_min = M;
		int i_max = -1;
		int j_max = -1;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < M; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
				if (mat[i][j] != 0) {
					i_min = i_min > i ? i : i_min;
					j_min = j_min > j ? j : j_min;
					i_max = i_max < i ? i : i_max;
					j_max = j_max < j ? j : j_max;
				}
			}
		}
		
		int year = 0;
		y : for (year = 0; year < 10000; year++) {
			
			int[][] tmp = new int[N][M];
			boolean[][] find = new boolean[N][M];
			
			for (int i = i_min; i <= i_max; i++) {
				for (int j = j_min; j <= j_max; j++) {
					int cnt = 0;
					for (int d = 0; d < 4; d++) {
						if(i+di[d]>=0 && i+di[d]<N && j+dj[d]>=0 && j+dj[d]<M)
						{
							if(mat[i+di[d]][j+dj[d]]==0)
							{
								cnt++;
							}
						}
					}
					tmp[i][j] = mat[i][j]-cnt;
					if(tmp[i][j]<0)
						tmp[i][j] = 0;
				}
			}

			int res = 0;
			boolean noIceBerg = true;
			// 탐색
			for (int i = i_min; i <= i_max; i++) {
				for (int j = j_min; j <= j_max; j++) {
					if(tmp[i][j]!=0 && !find[i][j]) // 빙하이고(!=0), 탐색한적 없으면(false) search수행
					{
						noIceBerg = false;
						search(tmp,find,i,j);
						res++;
					}
					if(res>1)
					{
						System.out.println(year+1);
						break y;
					}	
					
				}
			}
			if(noIceBerg)
			{
				System.out.println(0);
				break;
			}
			mat = tmp;
		}
	}
	
	static void search(int[][] mat, boolean[][] find, int i,int j)
	{
		find[i][j] = true;
		for(int d=0;d<4;d++)
		{
			// 칸이 존재하고, 빙하이면서, 탐색한적 없으면
			if(i+di[d]>=0 && i+di[d]<mat.length && 
					j+dj[d]>=0 && j+dj[d]<mat[i].length &&
					mat[i+di[d]][j+dj[d]] !=0 &&!find[i+di[d]][j+dj[d]])
			{
				search(mat,find,i+di[d],j+dj[d]); // 재귀호출
			}
		}
	}
}
