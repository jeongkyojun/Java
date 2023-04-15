import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(bf.readLine());
		
		for(int tc=1;tc<=t;tc++) {
			
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int[] U = new int[n];
			for(int i=0;i<n;i++)
			{
				U[i] = i;
			}
			boolean key = true;
			for(int i=0;i<m;i++) {
				st = new StringTokenizer(bf.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if(union(a,b,U))
				{
					System.out.println(i+1);
					key = false;
					break;
				}
			}
			if(key)
				System.out.println(0);
		}
	}

	static int find_union(int i, int[] U) {
		if(U[i]==i) return i;
		return U[i] = find_union(U[i],U);
	}
	
	static boolean union(int a, int b, int[] U) {
		int u_a = find_union(a,U);
		int u_b = find_union(b,U);
		if(u_a==u_b) return true;
		U[u_a] = u_b;
		return false;
	}
	
}
