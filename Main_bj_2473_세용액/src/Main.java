import java.util.*;
import java.io.*;

public class Main {
	static int min;
	static int bet;
	static int max;
	static long sub;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bf.readLine());
		for (int tc = 1; tc <= t; tc++) {
			System.out.println("#" + tc + " ");

			int n = Integer.parseInt(bf.readLine());
			long[] arr = new long[n];
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr); // 정렬
			sub = Long.MAX_VALUE;
			int[] res = new int[] { -1,-1,-1 };
			subset(arr,0,2,0,new int[2],res);
			System.out.println(arr[res[0]] + " " + arr[res[1]]+" "+arr[res[2]]); // 출력
		}
	}

	static void subset(long[] arr, int p, int n,int s, int[] select, int[] res) {
		if(p==n) {
			min = -1;
			bet = -1;
			max = -1;
//			System.out.print(Arrays.toString(select)+" :: ");
			long find = arr[select[0]]+arr[select[1]];
			binary(arr, 0, select[0] - 1, -1 * find, 0);
			binary(arr, select[0] + 1, select[1]-1, -1 * find, 1);			
			binary(arr, select[1] + 1, arr.length - 1, -1 * find, 2);
//			System.out.println(min+" , "+bet+" , "+max);
			if(max!=-1)
			{
				if(Math.abs(find+arr[max])<sub) {
				 	res[0] = select[0];
					res[1] = select[1];
					res[2] = max;
					sub = Math.abs(find+arr[max]);
				}
			}
			if(bet!=-1)
			{
				if(Math.abs(find+arr[bet])<sub) {
					res[0] = select[0];
					res[1] = bet;
					res[2] = select[1];
					sub = Math.abs(find+arr[bet]);
				}
			}
			if(min!=-1)
			{
				if(Math.abs(find+arr[min])<sub) {
					res[0] = min;
					res[1] = select[0];
					res[2] = select[1];
					sub = Math.abs(find+arr[min]);
				}
			}
//			System.out.println(Arrays.toString(res));
			return;
		}
		for(int i=s;i<arr.length;i++) {
			select[p] = i;
			subset(arr,p+1,n,i+1,select,res);
		}
	}
	
	static void binary(long[] arr, int low, int high, long n, int key) {
		if (low > high)
			return;
		int mid = (low + high) / 2;
		if (key==2) {
			if (max == -1 || Math.abs(arr[max] - n) > Math.abs(arr[mid] - n))
				max = mid;
		}else if(key==1){
			if (bet == -1 || Math.abs(arr[bet] - n) > Math.abs(arr[mid] - n))
				bet = mid;
		} else {
			if (min == -1 || Math.abs(arr[min] - n) > Math.abs(arr[mid] - n))
				min = mid;
		}
		if (n < arr[mid]) {
			// System.out.println("n : "+n);
			binary(arr, low, mid - 1, n, key);
		} else if (n > arr[mid]) {
			// System.out.println("n :: "+n);
			binary(arr, mid + 1, high, n, key);
		} else
			return;
	}
}
