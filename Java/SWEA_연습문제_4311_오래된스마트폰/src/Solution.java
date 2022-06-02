import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_sw_4311.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		
		for(int test_case=1;test_case<=T;test_case++)
		{
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			int O = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[] nums = new int[N];
			boolean[] cals = new boolean[4];
			st = new StringTokenizer(bf.readLine());
			for(int i=0;i<N;i++)
				nums[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(bf.readLine());
			for(int i=0;i<O;i++)
			{
				int o = Integer.parseInt(st.nextToken());
				cals[o] = true;
			}
		}
	}

}
