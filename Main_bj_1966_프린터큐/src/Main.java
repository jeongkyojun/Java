import java.util.*;
import java.io.*;

class nums{
	int p;
	int n;
	
	nums(int p, int n){
		this.p = p;
		this.n = n;
	}
}

public class Main {
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(bf.readLine());
		for(int t = 1;t<=tc;t++)
		{
			StringTokenizer st = new StringTokenizer(bf.readLine());
			Queue<nums> q = new LinkedList<nums>();
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
			
			int n = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(bf.readLine());
			for(int i=0;i<n;i++)
			{
				int num = Integer.parseInt(st.nextToken());
				q.offer(new nums(num,i));
				pq.offer(num);
			}
			
			int chk = pq.poll();
			int cnt = 0;
			while(!q.isEmpty())
			{
				nums tmp = q.poll(); // 빼내고
				if(chk == tmp.p) { // 비교해서 맞으면 꺼낸다.
					cnt++; // 순서 증가
					if(tmp.n==p) { // 원하는 순서까지 맞으면
						System.out.println(cnt); // 출력
						break;
					}
					if(!pq.isEmpty())
						chk = pq.poll(); // 비교값 갱신
				}
				else {
					q.offer(tmp); // 아닐경우 다시 넣는다.
				}
			}
		}
	}

}
