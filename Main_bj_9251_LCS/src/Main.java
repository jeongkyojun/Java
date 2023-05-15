import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_9251.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {

			String A = bf.readLine();
			String B = bf.readLine();
			int[][] mat = new int[A.length()][B.length()]; // i는 A j는 B이다
			int max = 0;
			for(int i=0;i<A.length();i++) 
			{
				boolean Add = false;
				for(int j=0;j<B.length();j++)
				{
					if(i-1>=0)
						mat[i][j] = mat[i-1][j]; // mat[i][j] = A의 i지점과 B의 j지점
					if(j-1>=0)
						mat[i][j] = Integer.max(mat[i][j], mat[i][j-1]);
					// i와 j-1를 비교한것과 i-1과 j를 비교한 것중 큰 값을 가져온다.
					if(A.charAt(i)==B.charAt(j))
					{
						if(i-1>=0 && j-1>=0)
							mat[i][j] = mat[i-1][j-1]+1;
						else
							mat[i][j] = 1;
					}
					if(max<mat[i][j])
						max = mat[i][j];
				}
			}
			for(int i=0;i<mat.length;i++)
			{
				System.out.println(Arrays.toString(mat[i]));
			}
			System.out.println(max);
		}
	}

}
