import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_2631.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		for(int test_case=1;test_case<=T;test_case++)
		{
			System.out.print("#"+test_case+" ");
			int N = Integer.parseInt(bf.readLine());
			int[] mat = new int[N];
			int cnt = 0;
			for(int i=0;i<N;i++)
			{
				int n = Integer.parseInt(bf.readLine());
				boolean find = true;
				for(int j=0;j<cnt;j++)
				{
					if(mat[j]>=n)
					{
						find = false;
						mat[j] = n;
						break;
					}
				}
				if(find)
				{
					mat[cnt++] = n;
				}
			}
			System.out.println(N-cnt);
		}
	}

}
