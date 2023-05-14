import java.util.*;
import java.io.*;

// 메모리 기준으로는 틀리는데 cost 기준으로는 맞는다. 점화식 원리는 비슷한데 어떤부분이 다른걸까?

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] memory = new int[n];
		int[] cost = new int[n];
		int[] dp = new int[10001]; // 100*100 + 1	
		st = new StringTokenizer(bf.readLine());
		for(int i=0;i<n;i++) {
			memory[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(bf.readLine());
		int C = 0;
		for(int i=0;i<n;i++) {
			cost[i] = Integer.parseInt(st.nextToken());
			C+=cost[i];
		}
		
		int min = C;
		for(int i=0;i<n;i++) {
			for(int j=10000;j>=0;j--) {
				if(dp[j]!=0) {
					if(dp[j+cost[i]]<dp[j]+memory[i]) {
						dp[j+cost[i]] = dp[j]+memory[i];
						if(dp[j+cost[i]]>=m && min>j+cost[i]) {
							min = j+cost[i];
						}
					}
				}
			}
			if(dp[cost[i]]<memory[i]) {
				dp[cost[i]] = memory[i];
				if(dp[cost[i]]>=m && min>cost[i]) {
					min = cost[i];
				}
			}
		}
		System.out.println(min);
	}
}

//for(int i=0;i<=20000000;i++) {
//dp[i] = -1;
//}
//
//for(int i=0;i<n;i++) {
//for(int j=m-1;j>0;j--) {
//	if(dp[j]!=-1) {
//		if(dp[j+memory[i]]==-1 || dp[j+memory[i]]>dp[j]+cost[i]) {
//			dp[j+memory[i]] = dp[j]+cost[i];
//			if(j+memory[i]>=m && dp[j]+cost[i]<min) {
//				min = dp[j]+cost[i];
//			}
//		}
//	}
//}
//if(dp[memory[i]]==-1||dp[memory[i]]>cost[i]) {
//	dp[memory[i]] = cost[i];
//	if(memory[i]>=m && cost[i]<min) {
//		min = cost[i];
//	}
//}
//}
