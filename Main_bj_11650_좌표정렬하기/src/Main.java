import java.util.*;
import java.io.*;

class Point implements Comparable<Point>{
	int x;
	int y;
	
	Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int compareTo(Point o) {
		// TODO Auto-generated method stub
		if(x==o.x)
			return y-o.y;
		return x-o.x;
	}
}

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(bf.readLine());
		PriorityQueue<Point> pq = new PriorityQueue<Point>();
		for(int i=0;i<n;i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			pq.offer(new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		
		for(int i=0;i<n;i++) {
			Point tmp = pq.poll();
			System.out.println(tmp.x+" "+tmp.y);
		}
	}

}
