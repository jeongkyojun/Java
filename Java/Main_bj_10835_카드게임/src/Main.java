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
			Queue<int[]> cards = new LinkedList<int[]>();
			cards.offer(new int[] {0,0});
			while(!cards.isEmpty())
			{
				int[] tmp = cards.poll();
				//System.out.println(Arrays.toString(tmp));
				if(A[tmp[0]]>B[tmp[1]])
				{
					// 값이 큰 경우 갱신하고 DP에 넣는다.
					if(DP[tmp[0]][tmp[1]+1]<DP[tmp[0]][tmp[1]]+B[tmp[1]])
					{
						DP[tmp[0]][tmp[1]+1] = DP[tmp[0]][tmp[1]]+B[tmp[1]];
						if(tmp[1]+1<N)
							cards.offer(new int[] {tmp[0],tmp[1]+1});
						else
							end = Integer.max(end, DP[tmp[0]][tmp[1]+1]);
					}
				}
				if(DP[tmp[0]][tmp[1]]==0||DP[tmp[0]+1][tmp[1]+1]<DP[tmp[0]][tmp[1]])
				{
					DP[tmp[0]+1][tmp[1]+1] = DP[tmp[0]][tmp[1]];
					if(tmp[0]+1<N && tmp[1]+1<N)
						cards.offer(new int[] {tmp[0]+1,tmp[1]+1});
					else
						end = Integer.max(end, DP[tmp[0]][tmp[1]]);
				}
				if(DP[tmp[0]][tmp[1]]==0||DP[tmp[0]+1][tmp[1]]<DP[tmp[0]][tmp[1]])
				{
					DP[tmp[0]+1][tmp[1]] = DP[tmp[0]][tmp[1]];
					if(tmp[0]+1<N)
						cards.offer(new int[] {tmp[0]+1,tmp[1]});
					else
						end = Integer.max(end, DP[tmp[0]][tmp[1]]);
				}
				/*for(int i=0;i<=N;i++)
				{
					System.out.println(Arrays.toString(DP[i]));
				}
				System.out.println();*/
			}		
			System.out.println(end);
		}
	}

}
