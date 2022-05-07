import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2253.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);
		// int T = Integer.parseInt(bf.readLine());
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			// StringTokenizer st = new StringTokenizer(bf.readLine());
			// int N = Integer.parseInt(st.nextToken());
			// int M = Integer.parseInt(st.nextToken());
			int N = sc.nextInt();
			int M = sc.nextInt();
			int[] max = new int[N + 1];
			int[] min = new int[N + 1];
			int[][] dp = new int[N + 1][N + 1];
			max[N] = -1;
			dp[2][1] = 1;
			max[2] = 1;
			min[2] = 1;
			max[1] = 0;
			for (int i = 0; i < M; i++) {
				// int n = Integer.parseInt(bf.readLine());
				int n = sc.nextInt();
				max[n] = -2;
			}
			// i는 돌다리의 번호, j는 거리, 값은 횟수 => dp : 해당 번호의 해당거리를 사용한 최소횟수를 의미
			// dp[i][j] => dp[i+j+1][j+1] = dp[i][j]+1
			// 			=> dp[i+j][j] = dp[i][j]+1
			//			=> dp[i+j-1][j-1] = dp[i][j]+1
			for (int i = 1; i < N; i++) {
				if (max[i] == -2)
					continue; // -1인경우 pass
				for (int j = 0; j <= max[i]; j++) {
					if (dp[i][j]!=0) // 방문한 적이 있을때
					{
						if(i+j+1<=N && max[i+j+1]!=-2) // 다리가 존재하고, 작은 다리가 아닐때
						{
							if(dp[i+j+1][j+1]==0) dp[i+j+1][j+1] = dp[i][j]+1; // 없으면 덮어쓴다
							else dp[i+j+1][j+1] = Integer.min(dp[i+j+1][j+1],dp[i][j]+1); //있으면 작은값을 넣는다
							max[i+j+1] = Integer.max(max[i+j+1], j+1); // 탐색범위 재구성
							if(min[i+j+1]==0) min[i+j+1] = dp[i+j+1][j+1]; // 최솟값 갱신 - 없으면 덮어쓴다
							else min[i+j+1] = Integer.min(dp[i+j+1][j+1], min[i+j+1]); // 있으면 작은값을 넣는다.
						}
						if(i+j<=N && max[i+j]!=-2) // 다리가 존재하고, 작은 다리가 아닐때
						{
							if(dp[i+j][j]==0) dp[i+j][j] = dp[i][j]+1;
							else dp[i+j][j] = Integer.min(dp[i+j][j],dp[i][j]+1); 
							max[i+j] = Integer.max(max[i+j], j); // 탐색범위 재구성
							if(min[i+j]==0) min[i+j] = dp[i+j][j];
							else min[i+j] = Integer.min(dp[i+j][j], min[i+j]);
						}
						if(j>1&& i+j-1<=N && max[i+j-1]!=-2) // 다리가 존재하고, 작은 다리가 아닐때
						{
							if(dp[i+j-1][j-1]==0) dp[i+j-1][j-1] = dp[i][j]+1;
							else dp[i+j-1][j-1] = Integer.min(dp[i+j-1][j-1],dp[i][j]+1); 
							max[i+j-1] = Integer.max(max[i+j-1], j-1); // 탐색범위 재구성
							if(min[i+j-1]==0) min[i+j-1] = dp[i+j-1][j-1];
							else min[i+j-1] = Integer.min(dp[i+j-1][j-1], min[i+j-1]);
						}
					}
				}
			}
			System.out.println(Arrays.toString(max));
			System.out.println(Arrays.toString(min));
			System.out.println(max[N]==-1?-1:min[N]);
		}
	}
}
