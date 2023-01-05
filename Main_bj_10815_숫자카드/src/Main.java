import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int num = Integer.parseInt(bf.readLine());
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int[] arr = new int[num];
		for(int i=0;i<num;i++)
		{
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		int f = Integer.parseInt(bf.readLine());
		st = new StringTokenizer(bf.readLine());
		for(int i=0;i<f;i++)
		{
			int n = Integer.parseInt(st.nextToken());
			System.out.print(search(n,0,num-1, arr)+" ");
		}
	}
	
	static int search(int num, int low, int high, int[] arr) {
		while(low<=high) {
			int mid = (low+high)/2;
			if(num==arr[mid]) return 1;
			else if(arr[mid]<num) low = mid+1;
			else high = mid-1;
		}
		return 0;
	}
}
