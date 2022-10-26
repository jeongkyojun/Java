import java.util.*;
import java.io.*;

public class Main {

	static int[] di = new int[] {1,0,-1,0}; // 상 우 하 좌
	static int[] dj = new int[] {0,1,0,-1};
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("res/input_bj_10157.txt")));
		//BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		for(int test_case = 1;test_case <=T;test_case++)
		{
			System.out.println("#"+test_case+ " ");
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int c = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int[][] mat = new int[r][c];
			int num = Integer.parseInt(bf.readLine());
			int a = 0,b=0,cnt=1;
			int d = 0;
			int brk = 0;
			mat[a][b] = cnt;
			boolean f = false;
			while(cnt < num)
			{ 
				int n_a = a+di[d%4];
				int n_b = b+dj[d%4];
				if(0<=n_a&&n_a<mat.length&&0<=n_b&&n_b<mat[0].length && mat[n_a][n_b]==0)
				{
					mat[n_a][n_b] = ++cnt;
					a = n_a;
					b = n_b;
					brk = 0;
				}
				else
				{
					d++;
					if(brk++==4)
					{
						f = true;
						break;
					}
				}
			}
			/*for(int i=0;i<mat.length;i++)
			{
				System.out.println(Arrays.toString(mat[i]));
			}*/
			if(f)
				System.out.println("0");
			else
				System.out.println((b+1)+" "+(a+1));
		}
		
	}

}
