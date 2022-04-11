import java.util.*;
import java.awt.Window;
import java.io.*;
import java.math.BigInteger;

class node implements Comparable<node>{
	int v;
	long w;
	node(int v, long w)
	{
		this.v = v;
		this.w = w;
	}
	@Override
	public int compareTo(node o) {
		return Long.compare(w, o.w);
	}
}

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_1504.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		boolean[] chk = new boolean[N+1];
		long[] vnear = new long[N+1];	
		
		LinkedList<node>[] nodes = new LinkedList[N+1];
		
		for(int i=1;i<=N;i++)
		{
			nodes[i] = new LinkedList<node>();
		}
		
		// 값 입력
		for(int i=0;i<M;i++)
		{
			st = new StringTokenizer(bf.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			long w = Long.parseLong(st.nextToken());
			nodes[u].add(new node(v,w));
			nodes[v].add(new node(u,w));
		}
		
		// a와 b 지정
		st = new StringTokenizer(bf.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		PriorityQueue<node> pq =  new PriorityQueue<node>();
		
		//  1부터 시작하는 다익스트라
		vnear = dijkstra(1, N+1, pq, nodes);
		System.out.println(Arrays.toString(vnear));
		
		long Ra = vnear[a]; // 1->a
		long Rb = vnear[b]; // 1->b
		
		// a->b를 확인
		vnear = dijkstra(a, N+1, pq, nodes);
		System.out.println(Arrays.toString(vnear));

		if(Ra!=-1)
		{
			if(vnear[b]==-1)
				Ra = -1;
			else
				Ra+=vnear[b];
		}
		if(Rb!=-1)
		{
			if(vnear[b]==-1)
				Rb = -1;
			else
				Rb+=vnear[b];
		}
		
		vnear = dijkstra(N, N+1, pq, nodes);
		System.out.println(Arrays.toString(vnear));
		// 값 더하기
		
		// 끝 값
		if(Ra!=-1)
		{
			if(vnear[b]==-1)
				Ra = -1;
			else
				Ra+=vnear[b];
		}
		if(Rb!=-1)
		{
			if(vnear[a]==-1)
				Rb = -1;
			else
				Rb+=vnear[a];
		}
		
		//System.out.println(Ra.toString()+" "+Rb.toString());
		// 출력
		if(Ra==-1)
			System.out.println(Rb);
		else if(Rb==-1)
			System.out.println(Ra);
		else
		{
			System.out.println(Long.min(Ra, Rb));
		}
	}
	
	static long[] dijkstra(int a, int size, PriorityQueue<node> pq, LinkedList<node>[] nodes) {
		long[] vnear = new long[size];
		boolean[] chk = new boolean[size];
		// 다익스트라 값 초기화
		for(int i=1;i<size;i++){
			vnear[i] = -1;
			chk[i] = false; // 방문처리 해제
		}
		// pq 재정의
		pq.clear();
		pq.offer(new node(a,0)); // N부터 시작
		while(!pq.isEmpty())
		{
			node tmp = pq.poll();
			if(chk[tmp.v]) continue; // 이미 방문한 곳은 pass
			vnear[tmp.v] = tmp.w; // 최단거리 표시
			chk[tmp.v] = true; // 방문처리	
			for(node n : nodes[tmp.v])
			{
				if(vnear[n.v]==-1 ||(vnear[tmp.v]!=-1&& vnear[n.v] > vnear[tmp.v] + n.w))
				{
					vnear[n.v] = vnear[tmp.v]+n.w;
					pq.offer(new node(n.v, vnear[n.v]));
				}
			}
		}
		return vnear;
	}
}
