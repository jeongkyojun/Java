import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_11066.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		for(int tc=1;tc<=T;tc++)
		{
			int N = Integer.parseInt(bf.readLine());
			int[][] mat = new int[N][N];
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int[] vec = new int[N];
			for(int i=0;i<N;i++)
			{
				vec[i] = Integer.parseInt(st.nextToken());
				if(i>=1)
					vec[i]+=vec[i-1];
			}
			System.out.println(Arrays.toString(vec));
			for(int d=1;d<=N-1;d++) // 행렬간 간격
			{
				for(int i=0;i+d<N;i++) // 행
				{	
					int j=i+d; // 열
					int add;
					if(i==0) add = vec[j];
					else add = vec[j]-vec[i-1];
					int min = Integer.MAX_VALUE; // 값
					// mat[i][j] = minimum(mat[i][k]+mat[k+1][j]);
					for(int k=i;k<=j-1;k++)
					{
						if(min > mat[i][k]+mat[k+1][j])
						{
							min = mat[i][k]+mat[k+1][j];
						}
					}
					mat[i][j] = add+min;
				}
			}
			
			for(int i=0;i<N;i++)
				System.out.println(Arrays.toString(mat[i]));
			System.out.println(mat[0][N-1]);
		}
		
	}

}
