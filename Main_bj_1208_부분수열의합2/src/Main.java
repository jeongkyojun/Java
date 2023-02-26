import java.io.*;
import java.util.*;

아직 푸는중

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(bf.readLine());
		for(int tc=1;tc<=t;tc++)
		{
			System.out.print("#"+tc+" ");
			
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int n = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int[] arr = new int[n];
			int[] sum = new int[n+1];
			int p = 0;
			st = new StringTokenizer(bf.readLine());
			for(int i=0;i<n;i++)
			{
				arr[i] = Integer.parseInt(st.nextToken());
				sum[i+1]=sum[i]+arr[i];
				if(arr[i]<s) p++;
			}
			Arrays.sort(arr);
			System.out.println(Arrays.toString(arr)+ " , "+p);
			
			subset_A(arr,s,arr.length-1,p,0,0,sum[n]-sum[p]);	
		}//
	}

	// 배열, 체크값, 최대점, 1차 최대점, 현재점, 현재 값, 큰것 최댓값
	static void subset_A(int[] arr, int s, int n,int p, int now,int value, int max) {
		if(value+max<s) return; // 한쪽의 체크값이 다른쪽 체크값보다 일방적으로 큰경우 pass
		if(now>=p) return;
	}
	static void subset_B(int[] arr, int s, int n,int p, int now,int value, int min) {
		if(value+min>s) return;
		if(now>=n) return;
	}
	
}
