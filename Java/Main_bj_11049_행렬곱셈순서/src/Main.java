import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_11049.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		for(int tc=1;tc<=T;tc++)
		{
			int N = Integer.parseInt(bf.readLine());
			int[][] mat = new int[N+1][N+1];
			StringTokenizer st = new StringTokenizer("");
			for(int i=0;i<N;i++)
			{
				st = new StringTokenizer(bf.readLine());
				mat[0][i] = Integer.parseInt(st.nextToken());
				mat[0][i+1] = Integer.parseInt(st.nextToken());
			}
			// mat[i][j] = mat[0][i-1]xmat[0][j] 꼴의 행렬이다.
			// i번째 행렬부터 j번째 행렬까지의 곱의 최솟값
			// 1~3 -> (1,2)3 || 1(2,3)
			// -> mat[1][3] = mat[1][2]+mat[3][3] + 행렬의 곱
			//				= mat[1][1]+mat[2][3] + 행렬의 곱
			
			// 1번 2번 3번 4번 -> 1-3 &4 , 1-2 & 3-4 , 1 & 2-4
			// [i][j] : [i][k] * [k][j] 
			/*
			 * 1,1 		2,2 	3,3		4,4
			 * 1,2 		2,3 	3,4
			 * 1,3		2,4
			 * 1,4
			 */
			for(int d=1;d<=N-1;d++) // 행렬간 간격
			{
				for(int i=1;i+d<=N;i++) // 행
				{	
					int j=i+d; // 열
					int min = Integer.MAX_VALUE; // 값
					// mat[i][j] = minimum(mat[i][k]+mat[k+1][j]+ mat[0][i-1]*mat[0][k]*mat[0][j] );
					for(int k=i;k<=j-1;k++)
					{
						if(min > mat[i][k]+mat[k+1][j]+(mat[0][i-1]*mat[0][k]*mat[0][j]))
						{
							min = mat[i][k]+mat[k+1][j]+(mat[0][i-1]*mat[0][k]*mat[0][j]);
						}
					}
					mat[i][j] = min;
				}
			}
			// 5x3x2 = 30 (5x2) x (2x6)
			// 3x2x6 = 36 (3x6)
			// 30 + 5x2x6
			// 5x3x6 + 36
			System.out.println();
			for(int i=0;i<=N;i++)
			{
				System.out.println(Arrays.toString(mat[i]));
			}
			System.out.println();
			System.out.println(mat[1][N]);
		}
	}

}
