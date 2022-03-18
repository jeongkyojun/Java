import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_15684.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		for(int test_case=1;test_case<=T;test_case++)
		{
			//세로선 개수 N, 가로선 개수 M
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken()); // 세로선 개수
			int M = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken()); // 놓을수 있는 위치의 개수
			
			int[][] mat = new int[N][H];
			boolean[][] ladder = new boolean[N][H];
			
			for(int i=0;i<N;i++)
			{
				mat[i][0] = i+1;
			}
			for(int i=0;i<M;i++)
			{
				st = new StringTokenizer(bf.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				ladder[a][b] = true;
			}
			
			for(int j=1;j<H;j++)
			{
				for(int i=0;i<N-1;i++)
				{
					if(ladder[i][j])
					{
						mat[i+1][j] = mat[i][j-1];
						mat[i][j] = mat[i+1][j-1];
						i++;
					}
					else
					{
						mat[i][j] = mat[i][j-1];
					}
				}
			}
			System.out.println(Arrays.toString(mat[H-1]));
		}

	}

}
