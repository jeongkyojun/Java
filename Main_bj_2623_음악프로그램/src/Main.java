import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		List<Integer>[] lst = new ArrayList[n];
		int[] pre = new int[n];
		
		for(int i=0;i<n;i++) {
			lst[i] = new ArrayList<Integer>();
		}
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(bf.readLine());
			int num = Integer.parseInt(st.nextToken());		
			int before = -1;
			for(int j=0;j<num;j++) {
				int next = Integer.parseInt(st.nextToken())-1;
				if(before!=-1) {
					lst[before].add(next);
					pre[next]++;
				}
				before = next;
			}
		}
		
		Queue<Integer> q = new LinkedList<Integer>();
		Queue<Integer> res = new LinkedList<Integer>();
		for(int i=0;i<n;i++) {
			if(pre[i]==0) {
				q.offer(i);
			}
		}
		while(!q.isEmpty()) {
			int tmp = q.poll();
			res.offer(tmp+1);
			for(int i=0;i<lst[tmp].size();i++) {
				pre[lst[tmp].get(i)]--;
				if(pre[lst[tmp].get(i)]==0) {
					q.offer(lst[tmp].get(i));
				}
			}
		}
		if(res.size()!=n) {
			System.out.println(0);
		}
		else {
			while(!res.isEmpty()) {
				System.out.println(res.poll());
			}
		}
	}

}
