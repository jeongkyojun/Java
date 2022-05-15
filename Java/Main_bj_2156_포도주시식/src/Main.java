import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_2156.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(bf.readLine());
		int[] wine = new int[n];
		int[][] DP = new int[n+1][3];
		int max=-1;
		wine[0] = Integer.parseInt(bf.readLine());
		DP[0][0] = wine[0];
		for(int i=1;i<n;i++)
		{
			wine[i] = Integer.parseInt(bf.readLine());
			for(int j=1;j<2;j++)
			{
				// 연속으로 마신경우 더한다
				// DP[i][0] -> DP[i][1]
				// DP[i][1] -> DP[i][2]
				// DP[i][2] -> 3잔연속 마실 수 없다.
				DP[i][j] = DP[i-1][j-1] + wine[i];
				max = Integer.max(DP[i][j],max);
			}
			if(i-2>=0)
				DP[i][0] = Integer.max(Integer.max(DP[i-2][0], DP[i-2][1]), DP[i-2][2])+wine[i];
			else DP[i][0] = wine[i];
			max = Integer.max(DP[i][0],max);
		}
		System.out.println(max);
		for(int i=0;i<=n;i++)
			System.out.println(Arrays.toString(DP[i]));
	}

}
