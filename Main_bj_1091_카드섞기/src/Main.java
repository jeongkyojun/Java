import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(bf.readLine());
		for(int tc=1;tc<=t;tc++)
		{
			System.out.print("#"+tc+" ");
			
			int n = Integer.parseInt(bf.readLine());
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int[] P = new int[n];
			int[] S = new int[n];
			
			for(int i=0;i<n;i++)
			{
				P[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(bf.readLine());
			for(int i=0;i<n;i++)
			{
				S[i] = Integer.parseInt(st.nextToken());
			}
			
			System.out.println(check_cycle(P,S,n));
		}
	}

	static int check_cycle(int[] P , int[] S , int n)
	{
		int res = -1;
		
		int[] now = new int[n];
		int correct = 0;
		for(int i=0;i<n;i++)
		{
			if(i%3==P[i])
			{
				correct++;
			}
			now[i] = S[i];
		}
		if(correct==n)
		{
			return 0;
		}
		int turn = 1;
		while(turn<2000000) // 무식하게 푸는방법 - 여기에 종료조건을 추가해주는것이 좋다.
		{
			correct = 0;
			for(int i=0;i<n;i++)
			{
				if(now[i]%3==P[i])
				{
					correct++;
				}
				now[i] = S[now[i]];
			}
			if(correct==n)
			{
				res = turn;
				break;
			}
			turn++;
		}
		return res;
	}
}
