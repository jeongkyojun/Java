import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_1516.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		for(int test_case=1;test_case<=T;test_case++)
		{
			int N = Integer.parseInt(bf.readLine());
			int[][] mat = new int[N][N];
			int[] time = new int[N];
			int[] res = new int[N];
			for(int i=0;i<N;i++)
			{
				StringTokenizer st = new StringTokenizer(bf.readLine());
				time[i] = Integer.parseInt(st.nextToken());
				for(int j=0;j<N;j++)
				{
					mat[i][j] = Integer.parseInt(st.nextToken());
					if(mat[i][j]==-1)
						break;
					mat[i][j]--;
				}
			}
			
			for(int i=0;i<N;i++)
			{
				System.out.println(SetTime(mat,res,time,i));
			}
		}
	}
	
	static int SetTime(int[][] mat, int[] res, int[] time, int i)
	{
		int max = 0;
		int t = time[i]; // 현재 시간
		
		if(res[i]!=0) return res[i];
		
		for(int x=0;x<mat[i].length;x++)
		{
			if(mat[i][x]==-1)
				break;
			max = Integer.max(SetTime(mat,res,time,mat[i][x]),max);
		}
		
		return res[i] = t+max;
	}
}
