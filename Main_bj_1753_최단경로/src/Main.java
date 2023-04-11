import java.util.*;
import java.io.*;

class node implements Comparable<node> {
	int v;
	long w;

	node(int v, long w) {
		this.v = v;
		this.w = w;
	}

	@Override
	public int compareTo(node o) {
		// TODO Auto-generated method stub
		return Long.compare(w, o.w);
	}
	
	@Override
	public String toString(){
        return "["+v+","+w+"]";
    }
}

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(bf.readLine());
		int n = Integer.parseInt(st.nextToken());// 정점의 개수
		int m = Integer.parseInt(st.nextToken());// 간선의 개수

		int start = Integer.parseInt(bf.readLine())-1; // 시작점
		LinkedList<node>[] mat = new LinkedList[n];
		long[] vnear = new long[n];
		for (int i = 0; i < n; i++) {
			mat[i] = new LinkedList<node>();
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(bf.readLine());
			int u = Integer.parseInt(st.nextToken())-1;
			int v = Integer.parseInt(st.nextToken())-1;
			long w = Long.parseLong(st.nextToken());
			mat[u].add(new node(v, w)); // 단방향 제한
			//mat[v].add(new node(u, w)); // 양방향
		}

		PriorityQueue<node> pq = new PriorityQueue<node>();
		
		vnear = dijkstra(start, n, pq, mat);
		for(int i=0;i<n;i++) {
			if(vnear[i]==-1)
				System.out.println("INF");
			else
				System.out.println(vnear[i]);
		}
	}
	
	static long[] dijkstra(int a, int size, PriorityQueue<node> pq, LinkedList<node>[] nodes) {
		long[] vnear = new long[size];
		boolean[] chk = new boolean[size];
		// 다익스트라 값 초기화
		for(int i=0;i<size;i++){
			vnear[i] = -1;
		}
		
		pq.offer(new node(a,0));
		vnear[a] = 0; // 최단거리 표시
		while(!pq.isEmpty())
		{
			node tmp = pq.poll(); // 가장 가까운 거리를 고른다.
			System.out.println(tmp);
			if(chk[tmp.v]) continue; // 이미 방문한 곳은 pass
			System.out.println(" :: "+tmp);
			chk[tmp.v] = true; // 방문처리	
			for(node n : nodes[tmp.v]) // tmp와 연결된 node 모두 탐색
			{
				// tmp를 통해 n으로 이동하는 거리가 현재 지정된 n의 최단거리보다 짧을 경우
				if(!chk[n.v]&&(vnear[n.v]==-1 ||(vnear[n.v] > vnear[tmp.v] + n.w)))
				{
					vnear[n.v] = vnear[tmp.v]+n.w;
					pq.offer(new node(n.v, vnear[n.v])); // 갱신 후 재입력
				}
			}
		}
		return vnear;
	}
}
