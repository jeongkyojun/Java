import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	
		int t = Integer.parseInt(bf.readLine());
		for(int tc = 1;tc<=t;tc++) {
			System.out.println("#"+tc+" ");
			
			int n = Integer.parseInt(bf.readLine());
			boolean[] prime = new boolean[n+1];
			long[] sum = new long[n+1];
			int p_num = 0;
			for(int i=2;i*i<=n;i++) { 
				// 소수만 찾아서 소수의 배수만 지운다.
				if(!prime[i]) {
					for(int j=i*i;j<=n;j+=i) {
						prime[j] = true;
					}
				}
			}
			int res = 0;
			int bef = 0;
			for(int i=2;i<=n;i++)
			{
				if(!prime[i]) {
					sum[++p_num] +=sum[p_num-1];
					sum[p_num]+=i;
					//System.out.println(sum[p_num]);
					if(sum[p_num]>=n) {
						for(int j=bef;j<=p_num;j++) {							
							if(sum[p_num]-sum[j]==n) {
								//System.out.println(sum[p_num]+" , "+sum[j]);
								res++;
							}
							if(sum[p_num]-sum[j]<=n) {
								if(j!=0)
									bef = j-1;
								break;
							}
						}
					}
				}
			}
			System.out.println(res);
		}
	}

}
