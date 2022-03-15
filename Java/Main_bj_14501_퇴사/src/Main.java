import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_14501.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		for(int test_case=1;test_case<=T;test_case++)
		{
			System.out.print("#"+test_case+" ");
			
			int N = Integer.parseInt(bf.readLine());
			int[][] work = new int[N][2];
			int max_d = -1;
			int max = -1;
			for(int i=0;i<N;i++)
			{
				StringTokenizer st = new StringTokenizer(bf.readLine());
				work[i][0] = Integer.parseInt(st.nextToken());
				work[i][1] = Integer.parseInt(st.nextToken());
				if(max_d<work[i][0])
					max_d = work[i][0];
			}
			int[] cost = new int[max_d+1];
			
			for(int i=0;i<N;i++)
			{
				// 값 갱신
				cost[work[i][0]] = cost[work[i][0]]>cost[0]+work[i][1]?cost[work[i][0]]:cost[0]+work[i][1];
				int tmp=cost[0];
				for(int j=0;j<max_d;j++)
				{
					cost[j] = cost[j+1];
				}
				cost[max_d] = 0;
				cost[0] = cost[0]>tmp?cost[0]:tmp;	
				max = max>cost[0]?max:cost[0];
			}
			System.out.println(max);
		}
		
	}

}
