import java.util.*;
import java.io.*;

public class Main {
	static int min;
	static int max;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(bf.readLine());
		for (int tc = 1; tc <= t; tc++) {
			System.out.print("#"+tc+" ");
			
			int n = Integer.parseInt(bf.readLine());
			int[] arr = new int[n];
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr);
			System.out.println(Arrays.toString(arr));
			int[] res = new int[] { -1, -1 };
			int sub = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				min = -1;
				max = -1;
				binary(arr, 0, i-1, -1 * arr[i], false);
				binary(arr,i+1,arr.length-1,-1*arr[i],true);
				//System.out.println(min + " , " + i + " , " + max);
				if (min == -1) {
					if (max != -1 && Math.abs(arr[max] + arr[i]) < Math.abs(sub)) {
						res[0] = i;
						res[1] = max;
						sub = arr[max] + arr[i];
					}
				} else if (max == -1) {
					if (Math.abs(arr[min] + arr[i]) < Math.abs(sub)) {
						res[0] = min;
						res[1] = i;
						sub = arr[min] + arr[i];
					}
				} else {
					int sum = arr[min] + arr[i];
					int a = min;
					int b = i;
					if (Math.abs(arr[max] + arr[i]) < Math.abs(arr[min] + arr[i])) {
						sum = arr[max] + arr[i];
						a = i;
						b = max;
					}
					if (Math.abs(sum) < Math.abs(sub)) {
						sub = sum;
						res[0] = a;
						res[1] = b;
					}
				}
				//System.out.println(Arrays.toString(res));
			}
			System.out.println(arr[res[0]] + " " + arr[res[1]]);
		}
	}

	static void binary(int[] arr, int low, int high, int n, boolean key) {
		if (low > high)
			return;
		int mid = (low + high) / 2;
		if (key)
		{
			if(max==-1||Math.abs(arr[max]-n)>Math.abs(arr[mid]-n))
				max = mid;
		}
		else
		{
			if(min==-1||Math.abs(arr[min]-n)>Math.abs(arr[mid]-n))
				min = mid;
		}
		if (n < arr[mid])
		{
			//System.out.println("n : "+n);
			binary(arr, low, mid - 1, n, key);
		}
		else if(n>arr[mid])
		{
			//System.out.println("n :: "+n);
			binary(arr, mid + 1, high, n, key);
		}
		else return;
	}
}
