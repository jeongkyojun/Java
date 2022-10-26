import java.util.*;
import java.io.*;

class pair implements Comparable<pair>
{
	int v;
	int w;
	
	public pair(int v, int w) {
		super();
		this.v = v;
		this.w = w;
	}

	@Override
	public int compareTo(pair o) {
		// TODO Auto-generated method stub
		return w-o.w;
	}	
}

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_10282.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		for(int test_case=1;test_case<=T;test_case++)
		{
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			ArrayList<pair>[] lst = new ArrayList[n];
			int[] vnear = new int[n];
			
			for(int i=0;i<n;i++)
			{
				lst[i] = new ArrayList<pair>();
			}
			for(int i=0;i<d;i++)
			{
				st = new StringTokenizer(bf.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				lst[u].add(new pair(v,w));
			}
		}
	}

}
