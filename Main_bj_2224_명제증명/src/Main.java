import java.util.*;
import java.io.*;

class node implements Comparable<node>{
	char u;
	char v;
	node(char u, char v){
		this.u = u;
		this.v = v;
	}
	@Override
	public int compareTo(node o) {
		// TODO Auto-generated method stub
		if(u==o.u) return v-o.v;
		return u-o.u;
	}
}

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bf.readLine());
		boolean[][] mat = new boolean[52][52];
		PriorityQueue<node> pq = new PriorityQueue<node>();
		for(int i=0;i<n;i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			char head = st.nextToken().charAt(0);
			String body = st.nextToken();
			char tail = st.nextToken().charAt(0);
			if(head!=tail)
				pq.offer(new node(head,tail));
			if(head<='Z') head-=65;
			else head-=71;
			if(tail<='Z') tail-=65;
			else tail-=71;
			mat[head][tail] = true;
		}
		
		for(int i=0;i<52;i++) {
			for(int j=0;j<52;j++) {
				if(j==i) continue;
				if(mat[i][j]) continue;
				for(int k=0;k<52;k++) {
					if(k==i || k==j) continue;
					if(mat[i][k] && mat[k][j]) {
						char head = (char)i;
						if(i<26) head+=65;
						else head+=71;
						char tail = (char)j;
						if(j<26) tail+=65;
						else tail+=71;
						pq.offer(new node(head,tail));
					}
				}
			}
		}
		System.out.println(pq.size());
		while(!pq.isEmpty()) {	
			node tmp = pq.poll();
			System.out.println(tmp.u+" => "+tmp.v);
		}
	}

}
