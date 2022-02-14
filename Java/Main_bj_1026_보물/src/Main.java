
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("res/Input_bj_1026.txt")));
		//BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		for(int test_case = 1;test_case<=T;test_case++)
		{
			
		
		int L = Integer.parseInt(bf.readLine());
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int[] S = new int[L];
		int[] N = new int[L];
		for(int i =0; i<L;i++)
		{
			S[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(bf.readLine());
		for(int j=0;j<L;j++)
		{
			N[j] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(S);
		Arrays.sort(N);
		int sum = 0;
		for(int i=0;i<L;i++)
		{
			sum += (S[i] * N[L-i-1]);
		}
		
		System.out.print("#"+test_case+" ");
		System.out.println(sum);
		
		}
	}
	
}
