import java.io.*;
import java.util.*;

/*
 * 문제이해가 늦어 삽질한 문제
 * 크거나 같은경우를 모두 확인했어야 했는데 같은경우만 확인하여 틀렸었다.
 */

public class Main {
	static int max;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		max = -1;
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int num = Integer.parseInt(st.nextToken());
		int need = Integer.parseInt(st.nextToken());
		int[] arr = new int[num];
		for(int i=0;i<num;i++)
		{
			arr[i] = Integer.parseInt(bf.readLine());
		}
		
		cut(arr, 1,Integer.MAX_VALUE,need);
		System.out.println(max);
	}

	static void cut(int[] arr, int low, int high, int need) {
		if(low <= 0 || high <= 0) return; // 오버플로, 언더플로 처리
		if(low>high) return; // 종료조건
		long mid = ((long)low+(long)high)/2; // 먼저 더할경우 overflow 가능성 발생
		long sum = 0; // 자른 개수
		for(int i=0;i<arr.length;i++)
		{
			sum += arr[i]/(int)mid;// mid가 클수록 sum의 값은 작다
		}
		if(sum<need) // sum 이 need보다 크다 = mid가 작다
		{
			cut(arr,low,(int)mid-1,need);
		}
		else
		{
			if(max<mid)
				max = (int)mid;
			cut(arr,(int)mid+1,high,need);
		}
	}
}
