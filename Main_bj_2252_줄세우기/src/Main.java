import java.util.*;
import java.io.*;

class node implements Comparable<node>{
	int in;
	int out;
	int num;
	node(int in, int out,int num){
		this.in = in;
		this.out = out;
		this.num = num;
	}
	@Override
	public int compareTo(node o) {
		// TODO Auto-generated method stub
		if(in==o.in) {
			return out-o.out;
		}
		return in-o.in;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "["+in+" , "+out+" :: "+num+"]";
	}
}
public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(bf.readLine());
		for(int tc=1;tc<=t;tc++) {
			
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			int[] team = new int[n];
			ArrayList<Integer>[] lst = new ArrayList[n];
			for(int i=0;i<n;i++) {
				lst[i] = new ArrayList<Integer>();
			}
			
			// 각 노드의 진입차수와 진출차수, 연결 상태를 구한다.
			for(int i=0;i<m;i++) {
				st = new StringTokenizer(bf.readLine());
				int pre = Integer.parseInt(st.nextToken());
				int post = Integer.parseInt(st.nextToken());
				team[post-1]++;
				lst[pre-1].add(post-1);
			}
			
			/*
			 * 위상정렬
			 * 모든 노드의 부모노드의 개수를 구한다.
			 * 부모노드의 개수가 0인 노드를 모두 큐에 넣는다.
			 * 큐에서 하나씩 노드를 꺼낸다.
			 * 꺼낸 노드와 연결된 노드의 부모노드의 개수를 1씩 제외한다.
			 * 만약 노드의 부모노드의 개수가 0이라면 큐에 넣는다.
			 * 이를 반복한다.
			 */
			int[] res = topology_sort(n,team,lst);
			for(int i=0;i<n;i++)
			{
				System.out.print((res[i]+1)+" ");
			}
			System.out.println();
		}
	}

	static int[] topology_sort(int n, int[] team, ArrayList<Integer>[] lst) {
		int[] res = new int[n];
		
		Queue<Integer> q = new LinkedList<Integer>();
		for(int i=0;i<n;i++) {
			if(team[i]==0)
				q.offer(i);
		}
		int cnt = 0;
		while(!q.isEmpty()) {
			int tmp = q.poll(); // 노드를 하나 꺼낸다.
			res[cnt++] = tmp;
			for(int i=0; i<lst[tmp].size();i++) {				
				team[lst[tmp].get(i)]--;
				if(team[lst[tmp].get(i)]==0) {
					q.offer(lst[tmp].get(i));
				}
			}
		}
		return res;
	}
	
}
