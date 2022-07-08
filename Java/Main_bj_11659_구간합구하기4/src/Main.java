import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_11659.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));	
		int T = Integer.parseInt(bf.readLine());
		for(int tc=1;tc<=T;tc++)
		{
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[] mat = new int[N+1];
			st = new StringTokenizer(bf.readLine());
			mat[1] = Integer.parseInt(st.nextToken());
			for(int i=2;i<=N;i++)
			{
				mat[i] = Integer.parseInt(st.nextToken())+mat[i-1];
			}
			//System.out.println(Arrays.toString(mat));
			for(int i=0;i<M;i++)
			{
				st = new StringTokenizer(bf.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				System.out.println(mat[b]-mat[a-1]);
			}
		}
	}
}
