import java.util.*;
import java.io.*;

class node implements Comparable<node>{
	int u;
	int v;
	int w;
	
	node(int u,int v,int w){
		this.u = u;
		this.v = v;
		this.w = w;
	}

	@Override
	public int compareTo(node o) {
		// TODO Auto-generated method stub
		return w-o.w;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return u+" , "+v+" , "+w;
	}
}

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		PriorityQueue<node> pq = new PriorityQueue<node>();
		int[] U = new int[n];
		
		for(int i=0;i<n;i++) {
			U[i] = i;
		}
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(bf.readLine());
			int u = Integer.parseInt(st.nextToken())-1;
			int v = Integer.parseInt(st.nextToken())-1;
			int w = Integer.parseInt(st.nextToken());
			pq.offer(new node(u,v,w));
		}
		int res = 0;
		int sum = 0;
		while(!pq.isEmpty()&& res<n-2){
			node tmp = pq.poll();
			if(!union(tmp.u,tmp.v,U)) {
				System.out.println(tmp.toString());
				res++;
				sum+=tmp.w;
			}
		}
		System.out.println(sum);
	}
	
	static int find_Union(int i, int[] U) {
		if(U[i]==i) return i;
		return U[i] = find_Union(U[i],U);
	}
	
	static boolean union(int a, int b, int[] U) {
		int u_a = find_Union(a,U);
		int u_b = find_Union(b,U);
		if(u_a==u_b) return true;
		U[u_a] = u_b;
		return false;
	}
}
