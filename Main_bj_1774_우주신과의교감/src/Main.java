import java.util.*;
import java.io.*;

class way implements Comparable<way>{
	double len;
	int s,e;
	way(int s, int e, double len){
		this.s = s;
		this.e = e;
		this.len = len;
	}
	@Override
	public int compareTo(way o) {
		// TODO Auto-generated method stub
		return (int)(len-o.len);
	}
}
public class Main {

	// 부동소수점 문제 발생 : 정확한 소수점을 보여주는 자료형을 찾아야 된다.
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int n = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		long[][] pos = new long[n][2];
		int[] U = new int[n];
		int line = 0;
		double res = 0.00;
		PriorityQueue<way> pq = new PriorityQueue<way>();
		for(int i=0;i<n;i++) {
			U[i] = i;
			st = new StringTokenizer(bf.readLine());
			pos[i][0] = Integer.parseInt(st.nextToken());
			pos[i][1] = Integer.parseInt(st.nextToken());
			for(int j=0;j<i;j++)
			{
				long dist = (pos[j][0] - pos[i][0])*(pos[j][0] - pos[i][0])+(pos[j][1]-pos[i][1])*(pos[j][1]-pos[i][1]);
				double len = Math.sqrt(dist);
				pq.offer(new way(j,i,len));
			}
		}
		// 합치는 과정 수행
		for(int i=0;i<t;i++) {
			st = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			if(!union(U,a,b))
			{
				line++;
			}
		}
		while(line<n-1&&!pq.isEmpty()) {
			way tmp = pq.poll();
			if(!union(U,tmp.s,tmp.e))
			{
				System.out.println(tmp.s+" - "+tmp.e+" : "+tmp.len);
				line++;
				res+=tmp.len;
			}
		}
		if(res*1000%10>=5)
		    System.out.printf("%.2f",res+0.01);
        else System.out.printf("%.2f",res);
	}
	
	static int find_union(int[] U, int i) {
		if(U[i]==i) return i;
		else return U[i] = find_union(U, U[i]);
	}
	static boolean union(int[] U, int a, int b) {
		int Union_a = find_union(U,a);
		int Union_b = find_union(U,b);
		
		if(Union_a==Union_b) return true;
		U[Union_b] = Union_a;
		return false;
	}
}
