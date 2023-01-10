import java.util.*;
import java.io.*;
import java.math.*;

풀이방식에 문제가 있어 구상전까지 보류

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int n = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		boolean[] chk = new boolean[n];
		for(int i=0;i<n;i++)
		{
			arr[i] = Integer.parseInt(bf.readLine());
		}
		Arrays.sort(arr);
		System.out.println(check(arr,0,arr.length-1,c,chk));
	}
	static int check(int[] arr, int low, int high, int c, boolean[] chk) {
		int sub = -1;
		int min = arr[high];
		int point = 0;
		/*
		 * 각 지점의 1/n 위치에 가장 가까운 값을 구하는 것을 목적으로 한다.
		 */
		int before = arr[0];
		for(int i=1;i<c-1;i++)
		{
			int find = (arr[low]+arr[high])/c*i; // 찾아야되는 지점의 좌표를 선택한다.
			int l = low;
			int h = high;
			while(l<=h) {
				int m = l+h/2;
				if(Math.abs(find-arr[m])<min && !chk[point])
				{
					min = Math.abs(find-arr[m]);
					point = m;
				}
				if(arr[m]<find) // 찾는 값보다 작은경우 앞쪽을 찾는다
					l = m+1;
				else if(arr[m]>find) // 크면 뒷쪽을 찾는다.
					h = m-1;
				else 
					break;
			}
			chk[point] = true;

		}
		return sub;
	}
	/*
	 1 2 8 4 9
	 1 2 4 8 9
	 */
	
}
