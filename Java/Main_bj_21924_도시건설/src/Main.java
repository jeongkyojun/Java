import java.util.*;
import java.io.*;

class node implements Comparable<node>{
	int a,b,w;
	public node(int a,int b, int w)
	{
		this.a = a;
		this.b = b;
		this.w = w;
	}
	@Override
	public int compareTo(node o) {
		return Integer.compare(w,o.w);
	}
}
public class Main {

	static long res;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_21924.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		for(int test_case=1;test_case<=T;test_case++)
		{
			System.out.print("#"+test_case+" ");
			
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			long max = 0;
			int[] U = new int[N];
			for(int i=0;i<N;i++)
			{
				U[i] = i;
			}
			res = 0;
			PriorityQueue<node> pq = new PriorityQueue<node>();
			for(int i=0;i<M;i++)
			{
				st = new StringTokenizer(bf.readLine());
				int a = Integer.parseInt(st.nextToken())-1;
				int b = Integer.parseInt(st.nextToken())-1;
				int w = Integer.parseInt(st.nextToken());
				max+=w;
				pq.offer(new node(a,b,w));
			}
			
			int cnt = 0;
			while(!pq.isEmpty())
			{
				node tmp = pq.poll();
				if(union(U,tmp.a,tmp.b,tmp.w))
					cnt++;
			}
			if(cnt==N-1)
				System.out.println(max-res);
			else
				System.out.println(-1);
		}
	}
	
	static int findUnion(int[] U, int i)
	{
		if(U[i]==i) return i;
		return U[i] = findUnion(U,U[i]);
	}
	static boolean union(int[] U,int a, int b,int w)
	{
		int Aunion = findUnion(U,a);
		int Bunion = findUnion(U,b);
		System.out.println(a+"->"+Aunion+" , "+b+"->"+Bunion);
		if(Aunion == Bunion) return false;
		U[Bunion] = Aunion;
		res+=w;
		return true;
	}
}
