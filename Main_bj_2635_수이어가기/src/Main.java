import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_2635.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		for(int test=1;test<=T;test++)
		{
		int N = Integer.parseInt(bf.readLine());
		/*
		 * N일때 다음 숫자는 N/2를 넘어야 한다
		 * 2번째 숫자가 N/2+a라고 할때, 
		 * 3번째 숫자는 N/2-a이고,
		 * 4번째 숫자는 2a가 된다.
		 * 5번째 숫자는 N/2 - 3a 이므로
		 * a<N/6이어야 된다.
		 * 따라서 N/2 ~ N/2+N/6 사이의 숫자 중에서 구한다.
		 */
		int max = -1;
		int idx = 0;
		for(int i=N/2;i<=N/2+N/6+1;i++)
		{
			int n = line(N,i);
			if(max<n)
			{
				max = n; 
				idx = i;
			}
		}
		System.out.println(max);
		print(N,idx);
		}
	}
	
	static int line(int N, int i)
	{
		if(N<i) // N과 i까지는 출력
			return 2;
		return 1+line(i,N-i);
	}
	static void print(int N, int i)
	{
		if(N<0)
		{
			System.out.println();
			return;
		}
		System.out.print(N+" ");
		print(i,N-i);
	}
}
