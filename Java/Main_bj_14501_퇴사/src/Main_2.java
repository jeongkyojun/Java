import java.io.*;
import java.util.*;

public class Main_2 {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_14501.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		for(int test_case=1;test_case<=T;test_case++)
		{
			System.out.print("#"+test_case+" ");
			
			int N = Integer.parseInt(bf.readLine());
			int[] res = new int[N+1];
			int[] work = new int[N+1];
			for(int i=0;i<N;i++)
			{
				StringTokenizer st = new StringTokenizer(bf.readLine());
				int d = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				if(i>0) // 상담 안할때
					res[i] = res[i]>res[i-1]?res[i]:res[i-1];
				if(i+d<=N) // 상담 할때
					res[i+d] = res[i]+p>res[i+d]?res[i]+p:res[i+d];
				//System.out.println(Arrays.toString(res));
			}
			res[N] = res[N]>res[N-1]?res[N]:res[N-1];
			System.out.println(res[N]);
		}
		
	}
}
