import java.util.*;
import java.io.*;

public class Solution {

	static double max;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_d4_1865.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		for(int test_case=1;test_case<=T;test_case++)
		{
			System.out.print("#"+test_case+" ");
			int N = Integer.parseInt(bf.readLine());
			double[][] work = new double[N][N];
			for(int i=0;i<N;i++)
			{
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for(int j=0;j<N;j++)
				{
					work[i][j] = Double.parseDouble(st.nextToken())/100;
				}
			}
			int[] B = new int[N];
			max = 0;
//			for(int i=0;i<N;i++)
//			{
//				System.out.println(Arrays.toString(work[i]));
//			}
			Search(work,0,N,1,0);
			System.out.println(String.format("%.6f", max*100));
		}
	}
	static void Search(double[][] work, int p, int n, double score,int chk)
	{
		if(p==n)
		{
			max = Double.max(score, max);
			return;
		}
		
		for(int i=0;i<n;i++)
		{
			if(work[p][i]==0) continue;
			if((chk&(1<<i))!=0) continue;
			
			if(score*work[p][i]>=max)
				Search(work,p+1,n,score*work[p][i],chk|(1<<i));
		}
	}
}
