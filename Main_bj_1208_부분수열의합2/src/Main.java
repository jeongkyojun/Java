import java.io.*;
import java.util.*;

// simple is best

public class Main {

	static long res;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(bf.readLine());
		for(int tc=1;tc<=t;tc++)
		{
			System.out.print("#"+tc+" ");
			
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int n = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int[] mat = new int[8000001];
			st = new StringTokenizer(bf.readLine());
			for(int i=0;i<n;i++)
			{
				int num = Integer.parseInt(st.nextToken());
				System.out.println(num);
				// num이 음수인경우 -100000 까지의 값이므로 j+num>=0이 되어야 한다. -> j>=-num
				if(num<0)
				{
					for(int j=-num;j<=8000000;j++)
					{
						if(mat[j]==0) continue;
						mat[j+num]+=mat[j];
						System.out.println((j+num)+" -> "+mat[j+num]);
					}
				}
				// num이 양수인경우 100000까지의 값이므로 j+num<8000000 이어야 한다. j<800000-num
				else
				{
					for(int j=8000000-num;j>=0;j--)
					{
						if(mat[j]==0) continue;
						mat[j+num]+=mat[j];
						System.out.println((j+num)+" -> "+mat[j+num]);
					}
				}
				mat[4000000+num]++; 
			}
			System.out.println(mat[s+4000000]);
		}//
	}
	
}
