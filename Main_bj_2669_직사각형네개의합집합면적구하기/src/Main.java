
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_2669.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		boolean[][] mat = new boolean[101][101];
		int res = 0;
		for(int a=0;a<4;a++)
		{
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int i_str = Integer.parseInt(st.nextToken());
			int j_str = Integer.parseInt(st.nextToken());
			int i_end = Integer.parseInt(st.nextToken());
			int j_end = Integer.parseInt(st.nextToken());
			for(int i=i_str;i<i_end;i++)
			{
				for(int j=j_str;j<j_end;j++)
				{
					if(!mat[i][j])
					{
						mat[i][j] = true;
						res++;
					}
				}
			}
		}
		System.out.println(res);
	}

}
