import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_17070.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		for(int test_case=1;test_case<=T;test_case++)
		{
			int N = Integer.parseInt(bf.readLine());
			int[][][] mat = new int[N][N][3];
			int[][] map = new int[N][N];
			for(int i=0;i<N;i++)
			{
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for(int j=0;j<N;j++)
				{
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 초기값 설정
			mat[0][1][0] = 1;
			
			for(int i=0;i<N;i++)
			{
				for(int j=0;j<N;j++)
				{
					if(map[i][j]==1) continue;
					for(int k=0;k<3;k++)
					{
						// 0은 가로, 1은 대각선, 2는 세로
						switch(k)
						{
						case 0:
							// 옆 확인, 0또는 1의 값을 구해 더한다.
							if(j>0 && map[i][j-1]!=1)
							{
								mat[i][j][k] += mat[i][j-1][0]+mat[i][j-1][1];
							}
							break;
						case 1:
							// 대각선인경우 위 아래도 확인
							if(i>0&&j>0 && map[i-1][j]!=1 && map[i][j-1]!=1 && map[i-1][j-1]!=1)
								mat[i][j][k] += mat[i-1][j-1][0]+mat[i-1][j-1][1]+mat[i-1][j-1][2];
							break;
						case 2:
							if(i>0 && map[i-1][j]!=1)
							{
								mat[i][j][k] += mat[i-1][j][2]+mat[i-1][j][1];
							}
							break;
						}
					}
				}
				
			}
			System.out.println(mat[N-1][N-1][0]+mat[N-1][N-1][1]+mat[N-1][N-1][2]);
		}
	}

}
