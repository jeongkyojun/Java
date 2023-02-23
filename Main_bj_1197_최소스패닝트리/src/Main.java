import java.util.*;
import java.io.*;

class node implements Comparable<node>{
	int s;
	int e;
	int v;
	node(int s, int e, int v){
		this.s = s;
		this.e = e;
		this.v = v;
	}
	@Override
	public int compareTo(node o) {
		// TODO Auto-generated method stub
		return v-o.v;
	}
}

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] U = new int[N];
		for(int i=0;i<N;i++)
		{
			U[i] = i;
		}
		PriorityQueue<node> pq = new PriorityQueue<node>();
		for(int i=0;i<M;i++)
		{
			st = new StringTokenizer(bf.readLine());
			int s = Integer.parseInt(st.nextToken())-1;
			int e = Integer.parseInt(st.nextToken())-1;
			int v = Integer.parseInt(st.nextToken());
			pq.offer(new node(s,e,v));
		}
		int cnt = 0;
		int res = 0;
		while(!pq.isEmpty() && cnt<N) {
			node tmp = pq.poll();
			if(!union(U,tmp.s,tmp.e)) {
				res+=tmp.v;
				cnt++;
			}
		}
		System.out.println(res);
	}

	static int find_union(int[] U, int i)
	{
		if(U[i]==i) return i;
		return U[i] = find_union(U,U[i]);
	}
	
	static boolean union(int[] U, int a, int b) {
		int u_a = find_union(U,a);
		int u_b = find_union(U,b);
		if(u_a == u_b) return true;
		U[u_a] = u_b;
		return false;
	}
}
