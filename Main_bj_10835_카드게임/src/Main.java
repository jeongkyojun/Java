import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_10835.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		for(int test_case=1;test_case<=T;test_case++)
		{
			int N = Integer.parseInt(bf.readLine());
			int[] A = new int[N];
			int[] B = new int[N];
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for(int i=0;i<N;i++)
			{
				A[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(bf.readLine());
			for(int i=0;i<N;i++)
			{
				B[i] = Integer.parseInt(st.nextToken());
			}
			
			int end = 0;
			int[][] DP = new int[N+1][N+1];
			boolean[][] chk = new boolean[N+1][N+1];
			chk[0][0] = true;
			for(int i=0;i<N;i++)
			{
				for(int j=0;j<N;j++)
				{
					if(chk[i][j])// 카드가 도달할 수 있는경우인지를 확인
					{
						if(A[i]>B[j]) // 왼쪽 카드가 더 큰경우
						{
							chk[i][j+1] = true;
							DP[i][j+1] = Integer.max(DP[i][j+1], DP[i][j]+B[j]); 	// 오른쪽 카드를 버리면서 점수 합산
							if(j+1==N)
								end = Integer.max(DP[i][j+1], end);
						}
						chk[i+1][j] = true;
						DP[i+1][j] = Integer.max(DP[i+1][j], DP[i][j]);				// 왼쪽만 버리는 경우
						chk[i+1][j+1] = true;
						DP[i+1][j+1] = Integer.max(DP[i+1][j+1], DP[i][j]); 		// 오른쪽과 왼쪽 둘 다 버리는 경우
						if(j+1==N||i+1==N)
							end = Integer.max(DP[i][j], end);
					}
				}
			}
			
			for(int i=0;i<N+1;i++)
				System.out.println(Arrays.toString(DP[i]));
			System.out.println(end);
		}
	}

}
